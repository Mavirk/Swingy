package trash.view;

import avaj.app.model.Artifact;
import avaj.app.model.Enemy;
import avaj.app.model.Hero;

public class View {

}
   public void printConsole(String message){}
   public void printMap(int playerX, int playerY, int size){}
   public void main(){}
   public void navigation(){}
   public void characterName(){}
   public void characterCreation(){}
   public void battle(Hero h, Enemy e){}
   public void fightMenu(Hero h, Enemy e){}
   public void fightOutcome(Hero h, Enemy e){}
   public void loot(Artifact artifact){}
   public void death(){}
   public void load(){}
   public void save(){}
   public void invalid(){}
   public void win(){}
}

//        switch (viewType) {
//                case "gui":
//                swingView = new Gui();
//                console = false;
//                break;
//                case "cui":
//                swingView = new Console();
//                break;
//default:
        System.out.println("Usage Error :/nPlease use either 'cui' for (Console Mode) or gui for (Graphical Mode)");
        }
//System.out.println("dealing"+weapon.damage);
//System.out.println(enemy.getName()+"defended with"+enemy.getDef()+"")
