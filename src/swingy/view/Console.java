package swingy.view;
import swingy.model.*;

import java.util.List;

public class Console extends View {
    public void printConsole(String message){
        System.out.println(message);
    }
    public void main() {
        System.out.println("mainMenu");
        System.out.println("new");
        System.out.println("load");
    }

    public void printMap(int playerX, int playerY, int size){
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";

        System.out.println(" Map Size : " + size + "x" + size);
        for(int j = size - 1; j >= 0; j--) {
            for(int i = 0; i < size; i++) {
                if (i == playerX && j == playerY) {
                    System.out.print(ANSI_RED + "|" + i + "," + j + "|" + ANSI_RESET);
                }else {
                    System.out.print("|" + i + "," + j + "|");
//                    System.out.print(coordinates[i][j].getSymbol());
                }
            }
            System.out.println();
        }
    }

    public void navigation() {
        System.out.println("navigatioMenu");
        System.out.println("north");
        System.out.println("east");
        System.out.println("south");
        System.out.println("west");
    }

    public void characterName(){
        System.out.println("Whats your name ?");
    }

    public void characterCreation() {
        System.out.println("What is your class");
        System.out.println("archer");
        System.out.println("knight");
        System.out.println("paladin");
        System.out.println("wizard");
    }


    public void battle(Hero h, Enemy e){
        System.out.println(h.getName()+" attacked "+e.getName()+" with "+h.getAtk()+" damage");
        System.out.println(e.getName()+" lost "+ (h.getAtk() - e.getDef()) + " hp");
        System.out.println(e.getName()+" attacked "+h.getName()+" with "+e.getAtk()+" damage");
        System.out.println(h.getName()+" lost "+(e.getAtk() - h.getDef()) + " hp");

        System.out.println(h.getName()+" has "+h.getHp()+"hp left");
        System.out.println(e.getName()+" has "+e.getHp()+"hp left");
    }

    public void fightMenu(Hero h, Enemy e) {
        System.out.println("fightMenu");
        System.out.println(h.getName() + " Are you brave enough to fight the " + e.getName() + "?");
        System.out.println("fight");
        System.out.println("run");
    }

    public void fightOutcome(Hero h, Enemy e){
        if(e.getHp() <= 0) System.out.println("The enemy "+e.getName()+" is dead");
        if(h.getHp() <= 0) System.out.println(h.getName()+" has died");
    }

    public void loot(Artifact artifact) {
        System.out.println("lootMenu");
        System.out.println("You found an ancient" + artifact.type);
        System.out.println("its name seems to be " + artifact.name);
        System.out.println("Take it ???");
        System.out.println("take");
        System.out.println("leave");
    }

    public void win() {
        System.out.println("You got to the edge of the map without dying");
        System.out.println("menu");
        System.out.println("exit");
    }

    public void death() {
        System.out.println("deathMenu");
        System.out.println("You were slayed by your enemies");
        System.out.println("menu");
        System.out.println("exit");
    }
    public void save(String filePath){
        System.out.print("saveGame");
        System.out.print("Would you like to save your game ??");
        System.out.print("yes");
        System.out.print("no");
    }
    public void load(List<String> file){
        System.out.print("loadGame");
        for(String s : file){
            System.out.println(s);
        }
    }
    public void invalid(){
        System.out.println("Invalid input please try again.");
    }

}
