package com.wethinkcode.swingy.view;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.wethinkcode.swingy.model.*;

public interface View {
	void clear();
	void printLine(String line);
	void printSpacer();
	void printMap(int playerX, int playerY, int size);
	void main();
	void navigation();
	void characterName ();
	void characterCreation();
	void battle(Hero h, Enemy e);
	void fightMenu(Hero h, Enemy e);
	void fightOutcome(Hero h, Enemy e);
	void lootMenu(Artifact artifact);
	void win();
	void death();
	void load();
	void save();
	void invalid(String input);
	// void showSavedGames(File[] savedGames);
	void printGameStats(Model game);
	void printHeroStats(Hero hero);
	JButton getButton();
	JTextField getInput();
	void printLevelUp(Hero hero);
}
