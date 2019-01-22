package com.wethinkcode.swingy.controller;

import com.wethinkcode.swingy.model.*;
import com.wethinkcode.swingy.view.*;

import java.util.Scanner;

public class Controller {
	public enum state{
		main,
		navigation,
		characterName,
		characterCreation,
		battle,
		fightMenu,
		fightOutcome,
		loot,
		win,
		death,
		load,
		save,
		invalid
	}
	public String resourcesPath = "../../resources";
	public String input = null;
	private String playerName;
	public state currentState = state.main;
	public Model model = new Model();
	public View swingView;
	
	
	public boolean exit = false;
	public boolean console = true;
	
	public Controller(String viewType){
		if (viewType.equals("console")){swingView = new Console(this);}
		else if (viewType.equals("swingy")){swingView = new Swingy(this);}
		
	}

	public void switchStates(){
		switch(currentState){
			case main:
				if(input.equals("new")){newGame();}
				else if(input.equals("load")){loadGame();}
				break;
			case navigation:
				break;
			case characterName:
				playerName = input;
				break;
			case characterCreation:
				characterCreationMenu();
				model.hero = createCharacter(playerName, input);
				break;
			case battle:
				break;
			case fightMenu:
				break;
			case fightOutcome:
				break;
			case loot:
				break;
			case win:
				break;
			case death:
				if(input.equals("main")){mainMenu();}
				else if(input.equals("exit")){exit();}
				break;
			case load:
				break;
			case save:
				break;
			case invalid:
				break;
			default:
				swingView.invalid(input);
				break;
				
				
		}
	}
	public void swingyClicked(String input) {
		this.input = input;
		switchStates();
	}
	public void getConsoleInput(){
		Scanner consoleInput = new Scanner(System.in);
		this.input = consoleInput.next();
		switchStates();
	}
	
	
	public void newGame(){
		model.world = model.generateWorld(0);
	}
	public void characterCreationMenu() {
		currentState = state.characterName;
		swingView.characterName();
		if(console) getConsoleInput();
		currentState = state.characterCreation;
		swingView.characterCreation();
		if(console) getConsoleInput();
	}
	
	public void loadGame(){
		currentState = state.load;
		swingView.load();
		if(console) getConsoleInput();
	}
	
	public void mainMenu() {
		currentState = state.main;
		swingView.main();
		if(console) getConsoleInput();
	}
	
	public void fightMenu(){
		currentState = state.fightMenu;
		swingView.fightMenu(model.hero, model.hero.getCurrentTile().getEnemy());
		if(console) getConsoleInput();
		currentState = state.fightOutcome;
		swingView.fightMenu(model.hero, model.hero.getCurrentTile().getEnemy());
		swingView.fightOutcome(model.hero, model.hero.getCurrentTile().getEnemy());
		if (model.hero.getCurrentTile().getEnemy().getHp() <= 0){
			lootMenu();
			model.hero.getCurrentTile().killEnemy();
		}
	}
	
	public void navigationMenu() {
		currentState = state.navigation;
		if(exit && model.hero.getHp() > 0) swingView.win();
		else if (model.hero.getHp() <= 0) swingView.death();
		
		while(!exit) {
			swingView.printMap(model.hero.getX(), model.hero.getY(), model.world.size);
			swingView.navigation();
			if (console) input = getConsoleInput();
			exit = model.hero.moveDirection(input);
			
			while(model.hero.getCurrentTile().isEnemy && model.hero.getHp() > 0) {
				fightMenu();
			}
		}
	}
	
	
	public boolean lootMenu() {
		currentState = state.loot;
		if (model.hero.getCurrentTile().getEnemy().artifact != null){
			swingView.loot(model.hero.getCurrentTile().getEnemy().artifact);
			if(console) input = getConsoleInput();
			switch (input){
				case "take":
					model.hero.putArtifact(model.hero.getCurrentTile().getEnemy().artifact);
					return true;
				case "leave":
					return false;
				default:
					swingView.invalid(input);
			}
		}
		return lootMenu();
	}
	public void exit(){
		System.exit(0);
	}
	public void fight(){
		switch (input){
			case "fight":
				model.hero.attackEnemy(model.hero.getCurrentTile().getEnemy());
				swingView.battle(model.hero, model.hero.getCurrentTile().getEnemy());
				break;
			case "run":
				model.hero.runAway();
				swingView.printLine("hero ran away...COWARD");
				navigationMenu();
				break;
			default:
				swingView.invalid(input);
		}
	}
	public Hero createCharacter(String name, String occupation){
		Hero h;
		switch(occupation) {
			case "archer":
				h = new Hero(name, occupation, 0, 1, 100, 10, 30, model.world);
				break;
			case "knight":
				h = new Hero(name, occupation, 0, 1, 100, 20, 20, model.world);
				break;
			case "paladin":
				h = new Hero(name, occupation, 0, 1, 200, 30, 10, model.world);
				break;
			case "wizard":
				h = new Hero(name, occupation, 0, 1, 1000, 1000, 1000, model.world);
				break;
			default:
				swingView.invalid(input);
				return null;
		}
		return h;
	}	
//
//    public void deathMenu() {
//        swingView.load();
//        if(console) input = getConsoleInput();
//        switch (input){
//            case "main":
//                mainMenu();
//                break;
//            case "exit":
//                break;
//            default:
//                swingView.invalid();
//        }
//    }
//
//    public void saveGame(){
//        swingView.save();
//        if(console) input = getConsoleInput();
//        switch (input){
//            case "yes":
//                break;
//            case "no":
//                break;
//            default:
//                swingView.invalid();
//
//        }
//    }
//
//}
	
	
	
}
