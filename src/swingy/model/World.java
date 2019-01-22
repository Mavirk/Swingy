package swingy.model;


import java.util.ArrayList;
import java.util.List;

public class World {
    public Tile[][] coordinates;
    public List<Tile> tiles = new ArrayList<>();
    public int size;
    public int playerX;
    public int playerY;

    public World(int area){
        size = area;
        playerX = (size - 1) / 2;
        playerY = (size - 1) / 2;
        System.out.println(playerX + ", " + playerY);
        coordinates = new Tile[size][size];
        for(int x = 0 ; x < size; x++){
            for(int y = 0 ; y < size; y++){
                coordinates[x][y] = new Tile(x, y, size);
                tiles.add(coordinates[x][y]);
                System.out.println("test 1");
            }
        }
        System.out.println("world constructer completed");
    }



    public Tile getTile(int x, int y){
        Tile t = coordinates[x][y];
        return t;
    }
//    public void registerHero(Hero hero){
//        this.hero = hero;
//    }
}
