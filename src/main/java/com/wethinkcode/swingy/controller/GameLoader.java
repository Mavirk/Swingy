package com.wethinkcode.swingy.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLoader {
    public String[] readLines(String filename) {
        String line;
        BufferedReader abc = null;
        try {
            abc = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        List<String> lines = new ArrayList<String>();
        try {
            while ((line = abc.readLine()) != null) {
                lines.add(line);
            }
            abc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String[] data = lines.toArray(new String[]{});
        return data;
    }
    public void getSavedGameList(String pathToSavedGames){
        File folder = new File(pathToSavedGames);
        File[] listOfFiles = folder.listFiles();
    }
    public void loadGame(){}
    public void saveGame(){}
}