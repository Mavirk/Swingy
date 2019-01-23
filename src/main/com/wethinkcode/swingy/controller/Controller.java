package src.main.com.wethinkcode.swingy.controller;

import src.main.com.wethinkcode.swingy.model.*;
import src.main.com.wethinkcode.swingy.view.*;

import java.util.Scanner;

public class Controller {
	public enum state{
		main,
		newGame,
		loadGame,
		characterName,
		characterCreation,
		navigation,
		fightMenu,
		lootMenu,
		battle,
		fightOutcome,
		win,
		death,
		loot,
		invalid
	}
	public String resourcesPath = "../../resources";
	public String input = null;
	private String playerName;
	public state currentState = state.main;
	public Model model = new Model();
	public View swingView;


	public boolean exit = false;
	public boolean buttonClicked = false;
	public boolean console = true;
	
	public Controller(String viewType){
		if (viewType.equals("console"))swingView = new Console(this);
		else if (viewType.equals("swingy")){
			swingView = new Swingy(this);
			console = false;
		}
	}

	public void swingyClicked(String input) {
		this.input = input;
		buttonClicked = true;
	}

	public void getConsoleInput(){
		if (console) {
			Scanner consoleInput = new Scanner(System.in);
			this.input = consoleInput.next();
		}
		while(!buttonClicked){
			doNothing();
		}
		System.out.println("button clicked");
		buttonClicked = false;
		switchStates();
	}

	public void switchStates(){
		switch(currentState){
			case main:
				if(input.equals("new"))newGame();
				else if(input.equals("load"))loadGame();
				break;
			case newGame:
				characterName();
			case loadGame:
				navigationMenu();
			case characterName:
				playerName = input;
				characterCreationMenu();
				break;
			case characterCreation:
				createCharacter(playerName, input);
				navigationMenu();
				break;
			case navigation:
				exit = model.hero.moveDirection(input);
				if(exit && model.hero.getHp() > 0) win();
				while(model.hero.getCurrentTile().isEnemy && model.hero.getHp() > 0) fightMenu();
				navigationMenu();
				break;
			case fightMenu:
				if (input.equals("fight")) fight();
				else if (input.equals("run")) run();
				fightOutcome();
				break;
			case lootMenu:
				if(input.equals("take")) model.hero.putArtifact(model.hero.getCurrentTile().getEnemy().artifact);
				else if(input.equals("leave"));
				else swingView.invalid(input);
				break;
			case battle:
				break;
			case fightOutcome:
				if (model.hero.getCurrentTile().getEnemy().getHp() <= 0){
					lootMenu();
					model.hero.getCurrentTile().killEnemy();
				}else if (model.hero.getHp() <= 0) deathMenu();
				break;
			case win:
				saveGame();
				if(input.equals("continue"))mainMenu();
				else if(input.equals("exit"))exit();
				break;
			case death:
				if(input.equals("main"))mainMenu();
				else if(input.equals("exit"))exit();
				break;
			case invalid:
				break;
		}
	}

	public void mainMenu() {
		currentState = state.main;
		swingView.main();
		getConsoleInput();
	}

	public void newGame(){
		currentState = state.newGame;
		model.world = model.generateWorld(0);
		characterName();
	}
	public void saveGame(){
		swingView.save();
	}

	public void loadGame(){
		currentState = state.loadGame;
		swingView.load();
		getConsoleInput();
	}

	public void characterName() {
		currentState = state.characterName;
		swingView.characterName();
		getConsoleInput();
	}

	public void characterCreationMenu() {
		currentState = state.characterCreation;
		swingView.characterCreation();
		getConsoleInput();
	}

	public void navigationMenu() {
		currentState = state.navigation;

		swingView.printMap(model.hero.getX(), model.hero.getY(), model.world.size);
		swingView.navigation();
		getConsoleInput();
	}

	public void fightMenu(){
		currentState = state.fightMenu;
		swingView.fightMenu(model.hero, model.hero.getCurrentTile().getEnemy());
		getConsoleInput();
	}


	public void lootMenu() {
		currentState = state.lootMenu;
		if (model.hero.getCurrentTile().getEnemy().artifact != null){
			swingView.loot(model.hero.getCurrentTile().getEnemy().artifact);
			getConsoleInput();
		}
		else {
			swingView.printLine("No loot found in the corpse");
			navigationMenu();
		}
	}

	public void deathMenu() {
		currentState = state.death;
		swingView.death();
		getConsoleInput();
	}

	public void win(){
		currentState = state.win;
		swingView.printLine("You escaped the map well done");
		swingView.printLine("continue");
		swingView.printLine("exit");
		getConsoleInput();
	}

	public void exit(){
		System.exit(0);
	}
	public void fight() {
		model.hero.attackEnemy(model.hero.getCurrentTile().getEnemy());
		swingView.battle(model.hero, model.hero.getCurrentTile().getEnemy());
	}

	public void fightOutcome(){
		currentState = state.fightOutcome;
		swingView.fightOutcome(model.hero, model.hero.getCurrentTile().getEnemy());
		if (model.hero.getHp() <= 0) swingView.death();
		getConsoleInput();
	}

	public void run(){
		model.hero.runAway();
		swingView.printLine("hero ran away...COWARD");
		navigationMenu();
	}

	public void createCharacter(String name, String occupation){
		switch(occupation) {
			case "archer":
				model.hero = new Hero(name, occupation, 0, 1, 100, 10, 30, model.world);
				break;
			case "knight":
				model.hero = new Hero(name, occupation, 0, 1, 100, 20, 20, model.world);
				break;
			case "paladin":
				model.hero = new Hero(name, occupation, 0, 1, 200, 30, 10, model.world);
				break;
			case "wizard":
				model.hero = new Hero(name, occupation, 0, 1, 1000, 1000, 1000, model.world);
				break;
			default:
				swingView.invalid(input);
		}
	}
	public void doNothing(){
		assert true;
	}

}
