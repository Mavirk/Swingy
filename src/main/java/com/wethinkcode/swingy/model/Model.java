package com.wethinkcode.swingy.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model implements Serializable{
    private static final long serialVersionUID = 1L;
    public Hero hero;
    public World world;

    public void saveGame(){
        System.out.println("this is the model.saveGame() function");

    }
    public void loadGame(){
        System.out.println("this is the model.loadGame() function");

    }

    private void executeScript(List<String> currentScript){
        for (String i : currentScript){
            if (i.equals("#")) break;
            System.out.println(i);
        }
    }

    private List<String> scanFile(String filename) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        List<String> lines = new ArrayList<String>();
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
        return lines;
    }

    public World generateWorld(int lvl) {
        int oddCounter = 1;
        System.out.println("lvl ::::::" + lvl);
        for (int i = 0; i <= lvl ; i++){
            oddCounter = oddCounter + 2;            
        }
        world = new World(oddCounter);
        return world;
    }

    public World getWorld() {
        return world;
    }
}
