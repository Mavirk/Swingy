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
    public boolean isEnemy = false;
    private int danger = 0;

    public Tile(int xC, int yC, int size) {
        int mapRadius = size / 2;
        int i = rand.nextInt(100);
        danger = Math.abs(mapRadius - Math.max(xC, yC));
        x = xC;
        y = yC;
        if(i < 50 && danger > 0){ 
            generateEnemy();
            isEnemy = true;
        }
    }

    private void generateEnemy() {
        enemy = EnemyBuilder.buildMercenary();

        if (danger >= 3 && danger < 6)
            enemy = EnemyBuilder.buildBlackKnight();
        else if (danger >= 6 && danger < 9)
            enemy = EnemyBuilder.buildHellHound();
        else if (danger >= 9) 
            enemy = EnemyBuilder.buildChimera();
    }

    public void killEnemy(){
        this.enemy = null;
        this.isEnemy = false;
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
