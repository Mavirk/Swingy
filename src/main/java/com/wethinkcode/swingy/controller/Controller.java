package com.wethinkcode.swingy.controller;

import com.wethinkcode.swingy.model.*;
import com.wethinkcode.swingy.view.*;
import java.util.Scanner;

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

	public Controller(String viewType) {
		if (viewType.equals("console"))
			swingView = new Console(this);
		else if (viewType.equals("swingy")) {
			swingView = new Swingy(this);
			console = false;
		}
		loaderSaver = new LoaderSaver();
		inputChecker = new InputChecker();
	}

	public void swingyClicked(String input) {
		this.input = input;
		buttonClicked = true;
	}

	public void getConsoleInput() {
		if (console) {
			Scanner consoleInput = new Scanner(System.in);
			this.input = consoleInput.next();
		} else
			while (!buttonClicked)
				assert true;
		buttonClicked = false;
		switchStates();
	}

	public void switchStates() {
		// swingView.printLine("current input: " + input);
		// swingView.printLine("current state: " + currentState);
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
		case loadGame:
			navigationMenu();
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
		case navigation:
			if (inputChecker.checkNavigationInput1(input)) {
				exit = model.hero.moveDirection(input);
				if (exit && model.hero.getHp() > 0)
					win();
				while (model.hero.getCurrentTile().isEnemy && model.hero.getHp() > 0)
					fightMenu();
				navigationMenu();
			} else
				invalidInput();
			break;
		case fightMenu:
			if (input.equals("fight"))
				fight();
			else if (input.equals("run"))
				run();
			else
				invalidInput();
			break;
		case lootMenu:
			if (input.equals("take")) {
				if (model.hero.getCurrentTile().getEnemy().artifact != null) {
					model.hero.putArtifact(model.hero.getCurrentTile().getEnemy().artifact);
					System.out.println("I have a new "
							+ model.hero.getItemArtifact(model.hero.getCurrentTile().getEnemy().artifact.type).name);
				} else
					System.out.println("there is nothiung even though there should be something");
				navigationMenu();
			} else if (input.equals("leave"))
				;
			else
				invalidInput();
			break;
		case battle:
			fightOutcome();
			break;
		case fightOutcome:
			if (model.hero.getCurrentTile().getEnemy().getHp() <= 0) {
				lootMenu();
				model.hero.getCurrentTile().killEnemy();
			} else if (model.hero.getHp() <= 0)
				deathMenu();
			break;
		case win:
			model.hero.levelUp();
			saveGame();
			if (input.equals("continue"))
				continueGame();
			else if (input.equals("exit"))
				exit();
			else
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
		default:
			print("default case insantiated Please review code");
		}
	}

	public void startGame() {
		switchStates();
	}

	public void mainMenu() {
		currentState = state.main;
		swingView.main();
		getConsoleInput();
	}

	public void continueGame() {
		loadGame(currentGame);
	}

	public void newGame() {
		currentState = state.newGame;
		model.world = model.generateWorld(0);
		characterName();
	}

	public void saveGame() {
		currentGame = loaderSaver.saveGame(model);
		print(currentGame);
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
		swingView.showSavedGames(loaderSaver.getSavedGameList(resourcesPath));
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
	

	public void fightMenu() {
		currentState = state.fightMenu;
		swingView.fightMenu(model.hero, model.hero.getCurrentTile().getEnemy());
		getConsoleInput();
	}

	public void lootMenu() {
		currentState = state.lootMenu;
		if (model.hero.getCurrentTile().getEnemy().artifact != null) {
			swingView.loot(model.hero.getCurrentTile().getEnemy().artifact);
			getConsoleInput();
		} else {
			swingView.printLine("No loot found in the corpse");
		}
	}

	public void deathMenu() {
		currentState = state.death;
		swingView.death();
		getConsoleInput();
	}

	public void win() {
		currentState = state.win;
		swingView.printLine("You escaped the map well done");
		swingView.printLine("continue");
		swingView.printLine("exit");
		getConsoleInput();
	}

	public void exit() {
		System.exit(0);
	}

	public void fight() {
		currentState = state.battle;
		model.hero.attackEnemy(model.hero.getCurrentTile().getEnemy());
		swingView.battle(model.hero, model.hero.getCurrentTile().getEnemy());
		switchStates();
	}

	public void fightOutcome() {
		currentState = state.fightOutcome;
		swingView.fightOutcome(model.hero, model.hero.getCurrentTile().getEnemy());
		if (model.hero.getHp() <= 0)
			characterDeath();
		else if (model.hero.getCurrentTile().getEnemy().getHp() > 0)
			fightMenu();
		else
			switchStates();
	}

	public void characterDeath() {
		currentState = state.death;
		swingView.death();
		return;
	}

	public void run() {
		model.hero.runAway();
		swingView.printLine("hero ran away...COWARD");
		navigationMenu();
	}

	public void createCharacter(String name, String occupation) {
		switch (occupation) {
		case "archer":
			model.hero = new Hero(name, occupation, 0, 0, 100, 10, 30, model.world);
			break;
		case "knight":
			model.hero = new Hero(name, occupation, 0, 0, 100, 20, 20, model.world);
			break;
		case "paladin":
			model.hero = new Hero(name, occupation, 0, 0, 200, 30, 10, model.world);
			break;
		case "wizard":
			model.hero = new Hero(name, occupation, 0, 0, 1000, 1000, 1000, model.world);
			break;
		default:
			invalidInput();
		}
	}

	public void doNothing() {
		assert true;
	}

	public void invalidInput() {
		swingView.printLine("Invalid Input :" + "'" + input + "'");
		print("current state: " + currentState);
		print("prev state: " + prevState);
		currentState = prevState;
		getConsoleInput();
	}

	public void print(String error) {
		System.out.println(error);
	}

}
