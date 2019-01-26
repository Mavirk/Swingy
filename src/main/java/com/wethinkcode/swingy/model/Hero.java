package com.wethinkcode.swingy.model;


public class Hero {
    private String name;
    private String occupation;
    private int xp;
    private int lvl;
    private int hp;
    private int def;
    private int atk;
    private int xCoor;
    private int yCoor;
    private int oldX;
    private int oldY;
    private World world;
    private Tile currentTile = null;
    private int mapSize;
    private Artifact helm = null;
    private Artifact armour = null;
    private Artifact weapon = null;


//    constructer
    public Hero(String n, String o, int x, int l, int h, int d, int a, World map){
        name = n;
        occupation = o;
        xp = x;
        lvl = l;
        hp = h;
        def = d;
        atk = a;
        world = map;
        xCoor = map.playerX;
        yCoor = map.playerY;
        currentTile = world.getTile(xCoor, yCoor);

    }
//    functions
    public void attackEnemy(Enemy enemy){
        enemy.setHp(enemy.getHp() + enemy.getDef() - atk);
        setHp(hp + def - enemy.getAtk());
        if (hp > 0 && enemy.getHp() <= 0){
            xp = xp + enemy.getXp();
        }
        System.out.println("TEST");
    }
    public void runAway(){
        xCoor = oldX;
        yCoor = oldY;
    }
    public boolean moveDirection(String direction) {
        oldX = xCoor;
        oldY = yCoor;
        switch (direction) {
            case "north":
                yCoor = yCoor + 1;
                break;
            case "east":
                xCoor = xCoor + 1;
                break;
            case "south":
                yCoor = yCoor - 1;
                break;
            case "west":
                xCoor = xCoor - 1;
                break;

        }
        System.out.print("Player Moved From: " + oldX + ", " + oldY);
        System.out.println(" To: " + xCoor + ", " + yCoor);
        if (yCoor >= 0 && xCoor >= 0 && xCoor < world.size && yCoor < world.size){
            currentTile = world.getTile(xCoor, yCoor);
            return false;
        }else
            return true;
    }
    public void putArtifact(Artifact item){
        switch (item.type){
            case "helm":
                helm = item;
                break;
            case "armour":
                armour = item;
                break;
            case "weapon":
                weapon = item;
                break;
        }
    }
    public void lookTile(Tile tile){
        System.out.println("you looked at the tile");
    }

//    getters

    public Tile getCurrentTile() {
        return currentTile;
    }

    public String getName(){
        return name;
    }
    public String getOccupation(){
        return occupation;
    }
    public int getXp(){
        return xp;
    }
    public int getLevel(){
        return lvl;
    }
    public int getHp(){
        return hp;
    }
    public int getDef(){
        return def;
    }
    public int getAtk(){
        return atk;
    }
    public int getX(){
        return xCoor;
    }
    public int getY(){
        return yCoor;
    }

//    setters
    public void setName(String i){
       name = i;
    }
    public void setOccupation(String i){
        occupation = i;
    }
    public void setXp(int i){
        xp = i;
    }
    public void setLevel(int i){
        lvl = i;
    }
    public void setHp(int i){
        hp = i;
    }
    public void setDef(int i){
        def = i;
    }
    public void setAtk(int i){
        atk = i;
    }
    public void setX(int x){
        xCoor = x;
    }
    public void setY(int y){
        yCoor = y;
    }
}
