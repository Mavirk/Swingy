package swingy.controller;

import swingy.model.Model;
import swingy.view.*;

import java.util.Scanner;

public class Controller {
    public String resourcesPath = "../../resources";
    public String input = null;

    public Model model = new Model();
    public View swingView;


    public boolean exit = false;
    public boolean console = true;

    public Controller(String viewType){
        swingView = new View(viewType);
    }

    public String getConsoleInput(){
        Scanner consoleInput = new Scanner(System.in);
        return consoleInput.next();
    }

    public String getSwingyInput() {return swingView.getInput();}

    public void newGame(){
        swingView.out("Generating New World ...");
        model.world = model.generateWorld(0);
        swingView.out("New World Generated ...");
        while(model.hero == null) model.hero = characterCreationMenu();
        swingView.out("Character Loaded into World ...");
        navigationMenu();
    }

    public void loadGame(){
        swingView.load();
        if(console) input = getConsoleInput();
    }

    public void mainMenu() {
        swingView.main();
        if(console) {
            input = getConsoleInput();
            switch (input) {
                case "new":
                    newGame();
                    break;
                case "load":
                    loadGame();
                    break;
            }
        }else{

        }
    }
    public void fightMenu(){
        swingView.fightMenu(model.hero, model.hero.getCurrentTile().getEnemy());
        if(console) input = getConsoleInput();
        else input = getSwingyInput();
        switch (input){
            case "fight":
                model.hero.attackEnemy(model.hero.getCurrentTile().getEnemy());
                swingView.battle(model.hero, model.hero.getCurrentTile().getEnemy());
                break;
            case "run":
                model.hero.runAway();
                swingView.printConsole("hero ran away...COWARD");
                navigationMenu();
                break;
            default:
                swingView.invalid();
        }
        swingView.fightOutcome(model.hero, model.hero.getCurrentTile().getEnemy());
        if (model.hero.getCurrentTile().getEnemy().getHp() <= 0){
            lootMenu();
            model.hero.getCurrentTile().killEnemy();
        }
    }

    public void navigationMenu() {
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

    public Hero characterCreationMenu() {
        Hero h = null;
        String name;
        String occupation;
        swingView.characterName();
        if(console) input = getConsoleInput();
        name = input;
        swingView.characterCreation();
        if(console) input = getConsoleInput();
        occupation = input;
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
                swingView.invalid();
                return h;
        }
        System.out.println("character created");
        return h;
    }

    public boolean lootMenu() {
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
                    swingView.invalid();
            }
        }
        return lootMenu();
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
