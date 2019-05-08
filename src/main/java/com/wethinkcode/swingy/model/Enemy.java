package com.wethinkcode.swingy.model;

import java.io.Serializable;
import java.util.Random;

public class Enemy implements Serializable{
    private static final long serialVersionUID = 1L;
    public Random rand = new Random();
    private String name;
    private int hp;
    private int def;
    private int atk;
    private int xp;
    public Artifact artifact = null;

    //    constructer
    public Enemy(String n, int h, int d, int a, int x) {
        int i = rand.nextInt(100);
        name = n;
        hp = h;
        def = d;
        atk = a;
        xp = x;
        if (i < 100) generateLoot();
    }

    public void generateLoot() {
        artifact = new Artifact(
                "hammer",
                "weapon",
                0,
                0,
                0,
                0,
                12
        );
    }

    //    getters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getDef() {
        return def;
    }

    public int getAtk() {
        return atk;
    }

    public int getXp() {
        return xp;
    }

    //    setters
    public void setName(String i) {
        name = i;
    }

    public void setHp(int i) {
        hp = i;
    }

    public void setDef(int i) {
        def = i;
    }

    public void setAtk(int i) {
        atk = i;
    }

}
//    public Artifact dropLoot(){
//        System.out.println("The "+name+" dropped a "+artifact.type);
//        if (artifact != null) {
//            return artifact;
//        }
//        return null;
//    }
