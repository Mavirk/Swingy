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
    private Artifact artifact = null;

    //    constructer
    public Enemy(String name, int health, int defence, int attack, int experience) {
        this.name = name;
        hp = health;
        def = defence;
        atk = attack;
        xp = experience;
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

    public Artifact getArtifact(){
        return artifact;
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

    public void setArtifact(Artifact artifact){
        this.artifact = artifact;
    }

}
