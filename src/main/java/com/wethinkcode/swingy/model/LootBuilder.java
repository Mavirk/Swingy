package com.wethinkcode.swingy.model;

import java.util.Random;

public class LootBuilder {


    private Random lootRandom = new Random();
    
    private Artifact buildArmour(int modifier, int quality){
        Artifact armour = new Artifact("Armour", getArmourName(quality), 0, modifier, 0);
        return armour;
    }
    private Artifact buildWeapon(int modifier, int quality){
        Artifact weapon = new Artifact("Weapon", getWeaponName(quality),  0, 0, modifier);
        return weapon;
    }
  
    private Artifact buildHelm(int modifier, int quality){
        Artifact helm = new Artifact("Helm", getHelmName(quality), modifier, 0, 0);
        return helm;
    }

    public Artifact getRandomArtifact(int modifier, int quality){
        int lootType = lootRandom.nextInt(3);
        Artifact artifact = null;
        switch (lootType){
            case 0:
                artifact = buildArmour(modifier, quality);
                break;
            case 1:
                artifact =  buildWeapon(modifier, quality);
                break;
            case 2:
                artifact = buildHelm(modifier, quality);
                break;
        }
        System.out.println("Artifact--- Name : "  + artifact.name + " Type : " + artifact.type);
        return artifact;
    }

    private String getArmourName(int quality){
        String[] armours = new String[]{"Leather", "ChainMail", "Hellhound Skin", "Chimera Scales"}; 
        return armours[quality];
    }

    private String getWeaponName(int quality){
        String[] weapons = new String[]{"Rusty Dagger", "Black Cleaver", "Flaming Axe", "Chimera Blade"};
        return weapons[quality];
    }

    private String getHelmName(int quality){
        String[] helms = new String[]{"Wood", "Iron", "Hellhound Skin", "Chimera Scales"};
        return helms[quality];
    }

}