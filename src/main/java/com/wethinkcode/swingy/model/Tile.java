package com.wethinkcode.swingy.model;

import java.io.Serializable;
import java.util.Random;

public class Tile implements Serializable{
    private static final long serialVersionUID = 1L;
    private Random rand = new Random();
    private int x;
    private int y;
    private String symbol = "@";
    private Enemy enemy = null;
    public boolean isEnemy = true;
    private int danger = 0;

    public Tile(int xC, int yC, int size) {
        int mapRadius = size / 2;
        int i = rand.nextInt(100);
        danger = Math.abs(mapRadius - Math.max(xC, yC));
        // System.out.println("danger : " + danger);
        x = xC;
        y = yC;
        if(i < 50 && danger > 0) enemy = generateEnemy();
        else isEnemy = false;
    }

    public Enemy generateEnemy() {
        String name = "Human";
        int hp = rand.nextInt(danger * 100) + ((danger * 100) - 100);;
        int def = rand.nextInt(danger * 10) + ((danger * 10) - 10);;
        int atk = rand.nextInt(danger * 10) + ((danger * 10) - 10);;
        int xp = rand.nextInt(danger * 100) + ((danger * 100) - 100);

        if (danger >= 3 && danger < 6) name = "Blackknight";
        else if (danger >= 6 && danger < 9) name = "Chimera";
        else if (danger >= 9) name = "HellHound";
        enemy = new Enemy(name, hp, def, atk, xp);
        // System.out.println("enemy generated on : " + this.x + ", " + this.y);
        return enemy;
    }

    public Enemy killEnemy(){
        if(enemy.getHp() <= 0) enemy = null;
        return enemy;
    }
    public Enemy getEnemy() {
        return enemy;
    }

    public String getSymbol(){
        return symbol;
    }
    public void setSymbol(String s) {
        symbol = s;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setX(int xC){
        x = xC;
    }
    public void setY(int yC){
        y = yC;
    }
}
