package com.wethinkcode.swingy.view;

import java.io.File;

import com.wethinkcode.swingy.controller.Controller;
import com.wethinkcode.swingy.model.*;

public class Console implements View {
	private String input;
	private final Controller controller;

	public Console(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void printLine(String message) {
		System.out.println(message);
	}

	@Override
	public void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();	
	}

	@Override
	public void printSpacer() {
		printLine("-----------------------");
	}

	@Override
	public void main() {
		printLine("mainMenu");
		printLine("new");
		printLine("load");
	}

	@Override
	public void printMap(int playerX, int playerY, int size) {
		String ANSI_RED = "\u001B[31m";
		String ANSI_RESET = "\u001B[0m";

		printLine(" Map Size : " + size + "x" + size);
		for (int j = size - 1; j >= 0; j--) {
			for (int i = 0; i < size; i++) {
				if (i == playerX && j == playerY) {
					System.out.print(ANSI_RED + "|.|"+ ANSI_RESET);
				} else {
					System.out.print("|.|");
					// System.out.print(coordinates[i][j].getSymbol());
				}
			}
			printLine("");
		}
	}

	@Override
	public void navigation() {
		printLine("navigatioMenu");
		printLine("north");
		printLine("east");
		printLine("south");
		printLine("west");
	}

	@Override
	public void characterName() {
		printLine("Whats your name ?");
	}

	@Override
	public void characterCreation() {
		printLine("What is your class");
		printLine("archer");
		printLine("knight");
		printLine("paladin");
		printLine("wizard");
	}

	@Override
	public void battle(Hero h, Enemy e) {
		printLine(h.getName() + " attacked " + e.getName() + " with " + h.getAtk() + " damage");
		printLine(e.getName() + " lost " + (h.getAtk() - e.getDef()) + " hp");
		printLine(e.getName() + " attacked " + h.getName() + " with " + e.getAtk() + " damage");
		printLine(h.getName() + " lost " + (e.getAtk() - h.getDef()) + " hp");

		printLine(h.getName() + " has " + h.getHp() + "hp left");
		printLine(e.getName() + " has " + e.getHp() + "hp left");
	}

	@Override
	public void fightMenu(Hero h, Enemy e) {
		printLine("fightMenu");
		printLine(h.getName() + " Are you brave enough to fight the " + e.getName() + "?");
		// TODO PRINT HEALTH , atack and def
		printLine("fight");
		printLine("run");
	}

	@Override
	public void fightOutcome(Hero h, Enemy e) {
		if (e.getHp() <= 0)
			printLine("The enemy " + e.getName() + " is dead");
		else if (h.getHp() <= 0) {
			printLine(h.getName() + " has died");
		}
		// print HEALTH ATTACK DEF
	}

	@Override
	public void loot(Artifact artifact) {
		printLine("lootMenu");
		printLine("You found an ancient" + artifact.type);
		printLine("its name seems to be " + artifact.name);
		printLine("Take it ???");
		printLine("take");
		printLine("leave");
	}

	@Override
	public void win() {
		printLine("You got to the edge of the map without dying");
		printLine("menu");
		printLine("exit");
	}

	@Override
	public void death() {
		printLine("deathMenu");
		printLine("You were slayed by your enemies");
		printLine("menu");
		printLine("exit");
	}

	@Override
	public void save() {
		System.out.print("Game Saved");
	}

	@Override
	public void showSavedGames(File[] savedGames) {
		printSpacer();
		printLine("SAVED GAMES:");
		for (File f : savedGames) printLine(f.getName());
	}

	@Override
	public void load() {
		printLine("loadGame from file");
	}

	@Override
	public void invalid(String input) {
		printLine("'" + input + "' <-you cant say that Sir they wont know what you mean");
	}

}
