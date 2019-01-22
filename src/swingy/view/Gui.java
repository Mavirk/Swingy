package trash.view;

import swingy.model.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Swingy extends View {

	
    public void printConsole(String message){}
    public void printMap(int playerX, int playerY, int size){}
    public void main(){
        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(b);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
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
