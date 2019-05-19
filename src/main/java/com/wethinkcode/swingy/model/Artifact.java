package com.wethinkcode.swingy.model;

import java.io.Serializable;

public class Artifact implements Serializable{
    private static final long serialVersionUID = 1L;
    public String name;
    public String type;
    public int damage = 10;
    public int[] modifiers;

    public Artifact(String artifactName, String artifactType, int xpMod, int levelMod, int hpMod, int defMod, int atkMod){
        name = artifactName;
        type = artifactType;
        modifiers = new int[]{xpMod, levelMod, hpMod, defMod, atkMod};
    }
    
    public int[] getModifiers() {
        return modifiers;
    }
}
