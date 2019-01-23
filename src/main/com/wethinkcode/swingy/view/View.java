package src.main.com.wethinkcode.swingy.view;

import src.main.com.wethinkcode.swingy.model.*;

public interface View {
	 void printLine(String line);
	 void printMap(int playerX, int playerY, int size);
	 void main();
	 void navigation();
	 void characterName ();
	 void characterCreation();
	 void battle(Hero h, Enemy e);
	 void fightMenu(Hero h, Enemy e);
	 void fightOutcome(Hero h, Enemy e);
	 void loot(Artifact artifact);
	 void win();
	 void death();
	 void load();
	 void save();
	 void invalid(String input);
}
