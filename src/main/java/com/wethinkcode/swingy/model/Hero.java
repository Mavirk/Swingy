package com.wethinkcode.swingy.model;

import java.io.Serializable;
import java.util.Random;

public class Hero implements Serializable{
    private static final long serialVersionUID = 1L;
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
    private Artifact noitem = new Artifact("Nothing", "Nothing", 0, 0, 0);
    private Artifact helm = noitem;
    private Artifact armour = noitem;
    private Artifact weapon = noitem;
    private Random fightDice = new Random();


//    constructer
    public Hero(String name, String occupation, int xp, int level, int health, int defence, int attack, World map){
        this.name = name;
        this.occupation = occupation;
        this.xp = xp;
        lvl = level;
        hp = health;
        def = defence;
        atk = attack;
        world = map;
        xCoor = map.playerX;
        yCoor = map.playerY;
        currentTile = world.getTile(xCoor, yCoor);

    }
//    functions
    public void attackEnemy(Enemy enemy){
        int missChance = fightDice.nextInt(100);
        int critChance = fightDice.nextInt(100);
        int enemyAttack = enemy.getAtk() - def;
        int heroAttack = getAtk() - enemy.getDef();
        if(heroAttack > 0 && missChance > 5){
            if (critChance < 2 )enemy.setHp(enemy.getHp() - (heroAttack * 2));
            else enemy.setHp(enemy.getHp() - heroAttack );
        }
        if(enemyAttack > 0 && missChance < 95){
            if (critChance > 97 )setHp(getHp() - (enemyAttack * 2));
            else setHp(getHp() - enemyAttack);
        }
        if (hp > 0 && enemy.getHp() <= 0){
            xp = xp + enemy.getXp();
        }
    }

    public void killEnemy(){
        world.getTile(xCoor, yCoor).killEnemy();
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
        System.out.println("X: " + xCoor + " Y: " + yCoor);
        System.out.println("WorldSize: " + world.size);

        if (yCoor >= 0 && xCoor >= 0 && xCoor < world.size && yCoor < world.size){
            currentTile = world.getTile(xCoor, yCoor);
            return false;
        }else{
            System.out.println("you have exited the world");
            return true;
        }
    }

    public void putArtifact(Artifact item){
        System.out.println("you pick up a");
        System.out.println(item.type);
        System.out.println("called");
        System.out.println(item.name);
        switch (item.name){
            case "Helm":
                if(helm != null)setHp(hp - helm.getModifiers()[0]);
                helm = item;
                break;
            case "Armour":
                if(armour != null)setDef(def - armour.getModifiers()[1]);
                armour = item;
                break;
            case "Weapon":
                if(weapon != null)setAtk(atk - weapon.getModifiers()[2]);
                weapon = item;
                break;
        }
        modifyValues(item);
    }

    public void modifyValues(Artifact item){
        int[] modifiers = item.getModifiers();
        
        setHp(hp + modifiers[0]);
        setDef(def + modifiers[1]);
        setAtk(atk + modifiers[2]);
    }
    
    public void lookTile(Tile tile){
        // System.out.println("you looked at the tile");
    }

    public boolean levelUp(){
        System.out.println("my current level is : " + lvl);
        int nextLvl = lvl + 1;
        double powerValue = nextLvl - 1;
        Double powerResult = Math.pow(powerValue, 2);
        int xpNeeded = nextLvl * 1000 + powerResult.intValue() * 450;
        System.out.println("I have this much xp " + xp);
        System.out.println("I need this much" + xpNeeded);
        if (xp >= xpNeeded){
            setLevel(lvl + 1);
            int modAtk = (getLevel() * 5);
            int modDef = (getLevel() * 2);
            int modHp = (getLevel() * getLevel() * 4);
            setAtk(atk + modAtk);
            setDef(def + modDef);
            setHp(hp + modHp);
            return true;
        }
        return false;
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
    public Artifact getItemArtifact(String itemName){
        Artifact item;
        switch(itemName){
            case "helm":
                item = helm;
                break;
            case "armour":
                item = armour;
                break;
            case "weapon":
                item = weapon;
                break;
            default:
                item = null;
        }
        return item;
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
    public void setWorld(World world){
        this.world = world;
    }
}
