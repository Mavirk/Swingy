package com.wethinkcode.swingy.controller;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import com.wethinkcode.swingy.model.Artifact;
import com.wethinkcode.swingy.model.Hero;
import com.wethinkcode.swingy.model.LoaderSaver;
import com.wethinkcode.swingy.model.Model;
import com.wethinkcode.swingy.view.Console;
import com.wethinkcode.swingy.view.Swingy;
import com.wethinkcode.swingy.view.View;

public class Controller {
	public enum state {
		startGame, main, newGame, saveGame, loadGame, getSavedGames, loadGameMenu, characterName, characterCreation,
		navigation, fightMenu, lootMenu, battle, fightOutcome, win, death, loot, invalid
	}

	public String resourcesPath = "resources/saved/";
	public String currentGame = "";
	public String input = null;
	private String playerName;
	public state currentState = state.startGame;
	public state prevState = null;
	public Model model = new Model();
	public View swingView;
	public LoaderSaver loaderSaver;
	public InputChecker inputChecker;

	public boolean exit = false;
	public boolean buttonClicked = false;
	public boolean console = true;
	private boolean debug = false;

	public Controller(String viewType, String runMode) {
		if (viewType.equals("console"))
			swingView = new Console(this);
		else if (viewType.equals("swingy")) {
			setupSwing();
		}
		if (runMode.equals("-d"))
			debug = true;
		loaderSaver = new LoaderSaver();
		inputChecker = new InputChecker();
	}

	public void runGame(){
		startGame();
		if(console){
			while(!exit){
				debugPrint("current state : " + currentState);
				debugPrint("getting User input");
				getConsoleInput();
				debugPrint("completed state : " + prevState);
			}
		}
	}

	private void setupSwing(){
		swingView = new Swingy(this);
		console = false;
		swingView.getButton().addActionListener(e -> swingyClicked());
	}
	
	public void swingyClicked() {
		this.input = null;
		this.input = swingView.getInput().getText();
		swingView.getInput().setText("");
		debugPrint(input);
		switchStates();
	}

	public void getConsoleInput() {
		this.input = null;
		Scanner consoleInput = new Scanner(System.in);
		this.input = consoleInput.next();
		switchStates();
	}

	public void startGame() {
		switchStates();
	}

	public void mainMenu() {
		currentState = state.main;
		swingView.main();
		// getConsoleInput();
	}

	public void continueGame() {
		loadGame(currentGame);
	}

	public void newGame() {
		currentState = state.newGame;
		model.world = model.generateWorld(0);
		switchStates();
	}

	public void saveGame() {
		currentGame = loaderSaver.saveGame(model);
		debugPrint(currentGame);
		swingView.save();
	}

	public void loadGame(String gameName) {
		currentState = state.loadGame;
		model = loaderSaver.loadGame(resourcesPath + gameName );
		model.world = model.generateWorld(model.hero.getLevel());
		model.hero.setX(model.world.playerX);
		model.hero.setY(model.world.playerY);
		model.hero.setWorld(model.world);
		swingView.load();
		switchStates();
	}

	public void loadGameMenu() {
		currentState = state.loadGameMenu;
		File[] saved = loaderSaver.getSavedGameList(resourcesPath);
		swingView.clear();
		if (saved.length > 0){
			Model temp;
			for (File file : saved){
				temp = loaderSaver.loadGame(file.getPath());
				swingView.printGameStats(temp);
			}
			// swingView.showSavedGames(saved);
			// getConsoleInput();
		}else {
			swingView.printLine("No Saved Games");
			swingView.printSpacer();
			mainMenu();
		}
	}

	public void characterName() {
		currentState = state.characterName;
		swingView.clear();
		swingView.characterName();
		// getConsoleInput();
	}

	public void characterCreationMenu() {
		currentState = state.characterCreation;
		swingView.clear();
		swingView.characterCreation();
		// getConsoleInput();
	}

	public void navigationMenu() {
		currentState = state.navigation;
		swingView.clear();
		swingView.printHeroStats(model.hero);
		swingView.printMap(model.hero.getX(), model.hero.getY(), model.world.size);
		swingView.navigation();
		// getConsoleInput();
	}
	

	public void fightMenu() {
		currentState = state.fightMenu;
		swingView.clear();
		swingView.fightMenu(model.hero, model.hero.getCurrentTile().getEnemy());
		// getConsoleInput();
	}

	public void lootMenu() {
		currentState = state.lootMenu;
		swingView.lootMenu(model.hero.getCurrentTile().getEnemy().getArtifact());
	}

	public void deathMenu() {
		currentState = state.death;
		swingView.death();
		exit();
	}

	public void win() {
		currentState = state.win;
		swingView.printLine("You escaped the map well done");
		swingView.printLine("continue");
		swingView.printLine("exit");
		// getConsoleInput();
	}

	public void exit() {
		System.exit(0);
	}

	public void battle() {
		currentState = state.battle;
		boolean fight = true;
		while (fight){
			model.hero.attackEnemy(model.hero.getCurrentTile().getEnemy());
			swingView.battle(model.hero, model.hero.getCurrentTile().getEnemy());
			if(model.hero.getHp() <= 0)
				fight = false;
			if(model.hero.getCurrentTile().getEnemy().getHp() <= 0)
				fight = false;
		}
		fightOutcome();
	}

	public void fightOutcome() {
		currentState = state.fightOutcome;
		swingView.fightOutcome(model.hero, model.hero.getCurrentTile().getEnemy());
		if (model.hero.getHp() <= 0)
			characterDeath();
		else{
			if (model.hero.levelUp()){
				swingView.printLevelUp(model.hero);
			}
			switchStates();
		}
	}

	public void characterDeath() {
		currentState = state.death;
		swingView.death();
		return;
	}

	public void run() {
		Random rand = new Random(100);
		int runaway = rand.nextInt();
		if (runaway > 49){
			model.hero.runAway();
			swingView.printLine("hero ran away...COWARD");
			navigationMenu();
		}else battle();
	}

	public void createCharacter(String name, String occupation) {
		switch (occupation) {
			case "archer":
				model.hero = new Hero(name, occupation, 0, 0, 150, 20, 50, model.world);
				break;
			case "knight":
				model.hero = new Hero(name, occupation, 0, 0, 200, 25, 40, model.world);
				break;
			case "paladin":
				model.hero = new Hero(name, occupation, 0, 0, 300, 35, 25, model.world);
				break;
			case "wizard":
				model.hero = new Hero(name, occupation, 0, 0, 1000, 1000, 1000, model.world);
				break;
			default:
				invalidInput();
		}
	}

	// private boolean checkGameWin(){
	// 	int yCoor = model.hero.getY();
	// 	int xCoor = model.hero.getX();
	// 	int worldSize = model.world.size;
	// 	if (yCoor >= 0 && xCoor >= 0 && xCoor < worldSize && yCoor < worldSize){
	// 		return false;
	// 	}
	// 	return true;
	// }

	public void doNothing() {
		assert true;
	}

	public void invalidInput() {
		swingView.printLine("Invalid Input :" + "'" + input + "'");
		debugPrint("current state: " + currentState);
		debugPrint("prev state: " + prevState);
		currentState = prevState;
		// getConsoleInput();
	}

	public void print(String error) {
		System.out.println(error);
	}

	private void debugPrint(String debugMessage){
		if (debug == true)
			System.out.println("DEBUG: " + debugMessage);
	}

	public void switchStates() {
		prevState = currentState;
		switch (currentState) {

			case startGame:
				mainMenu();
				break;

			case main:
				if (input.equals("new"))
					newGame();
				else if (input.equals("load"))
					loadGameMenu();
				else
					invalidInput();
				break;
			
			case newGame:
				characterName();
				break;

			case loadGameMenu:
				if (inputChecker.checkLoadInput(input))
					loadGame(input);
				else
					invalidInput();
				break;		

			case characterName:
				playerName = input;
				characterCreationMenu();
				break;

			case characterCreation:
				createCharacter(playerName, input);
				saveGame();
				navigationMenu();
				break;

			case loadGame:
				navigationMenu();
				break;

			case navigation:
				if (inputChecker.checkNavigationInput(input)) {					
					if(model.hero.moveDirection(input))
						win();
					else if(model.hero.getCurrentTile().isEnemy){
						fightMenu();
					}else{
						navigationMenu();
					}

				} else
					invalidInput();

				break;

			case fightMenu:
				if (input.equals("fight"))
					battle();
				else if (input.equals("run"))
					run();
				else
					invalidInput();
				break;

			case battle:
				break;

			case fightOutcome:
				if (model.hero.getCurrentTile().getEnemy().getHp() <= 0 && 
					model.hero.getCurrentTile().getEnemy().getArtifact() != null) {
					lootMenu();
				} 
				else if (model.hero.getHp() <= 0)
					deathMenu();
				else{ 
					model.hero.killEnemy();
					navigationMenu();
				}
				break;

			case lootMenu:
				if (input.equals("take")) {
					Artifact tempArtifact;
					tempArtifact =  model.hero.getCurrentTile().getEnemy().getArtifact();
					model.hero.killEnemy();
					model.hero.putArtifact(tempArtifact);
					navigationMenu();
				} 
				else if (input.equals("leave"))
					navigationMenu();
				else if (!input.isEmpty()) 
					invalidInput();
				break;

			case death:
				if (input.equals("menu"))
					mainMenu();
				else if (input.equals("exit"))
					exit();
				else
					invalidInput();
				break;

			case win:
				saveGame();
				if (input.equals("continue"))
					continueGame();
				else if (input.equals("exit"))
					exit();
				else
					invalidInput();
				break;
				
			default:
				print("default case insantiated Please review code");
		}
	}

}
