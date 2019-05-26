package com.wethinkcode.swingy.model;

import java.util.Random;

public class EnemyBuilder{

    // private Enemy buildEnemy(){}
    private static Random lootRandom = new Random();
    private static LootBuilder lootBuilder = new LootBuilder();

    public static Enemy buildMercenary(){
        int lootChance = lootRandom.nextInt(100);
        int modifier = lootRandom.nextInt(10) + 5;
        Enemy mercenary = new Enemy("Mercenary", 100, 10, 15, 1000);
        if(lootChance < 100)
            mercenary.setArtifact(lootBuilder.getRandomArtifact(modifier, 0));
        return mercenary;
    }

    public static Enemy buildBlackKnight(){
        int lootChance = lootRandom.nextInt(100);
        int modifier = lootRandom.nextInt(20) + 15;
        Enemy blackKnight = new Enemy("Black Knight", 150, 25, 25, 200);
        if(lootChance < 50)
            blackKnight.setArtifact(lootBuilder.getRandomArtifact(modifier, 1));
        return blackKnight;
    }


    public static Enemy buildHellHound(){
        int lootChance = lootRandom.nextInt(100);
        int modifier = lootRandom.nextInt(20) + 25;
        Enemy hellhound = new Enemy("HellHound", 300, 30, 40, 400);
        if(lootChance < 50)
            hellhound.setArtifact(lootBuilder.getRandomArtifact(modifier, 2));
        return hellhound;
    }

    public static Enemy buildChimera(){
        int lootChance = lootRandom.nextInt(100);
        int modifier = lootRandom.nextInt(200) + 50;
        Enemy chimera = new Enemy("Chimera", 700, 45, 60, 1000);
        if(lootChance < 40)
            chimera.setArtifact(lootBuilder.getRandomArtifact(modifier, 3));
        return chimera;
    }
}