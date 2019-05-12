package com.wethinkcode.swingy.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class World implements Serializable{
    private static final long serialVersionUID = 1L;
    public Tile[][] coordinates;
    public List<Tile> tiles = new ArrayList<>();
    public int size;
    public int playerX;
    public int playerY;

    public World(int area){
        size = area;
        playerX = (size - 1) / 2;
        playerY = (size - 1) / 2;
        coordinates = new Tile[size][size];
        for(int x = 0 ; x < size; x++){
            for(int y = 0 ; y < size; y++){
                coordinates[x][y] = new Tile(x, y, size);
                tiles.add(coordinates[x][y]);
            }
        }
    }



    public Tile getTile(int x, int y){
        Tile t = coordinates[x][y];
        return t;
    }
//    public void registerHero(Hero hero){
//        this.hero = hero;
//    }
}
