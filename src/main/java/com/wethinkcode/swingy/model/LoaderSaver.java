package com.wethinkcode.swingy.model;

import com.wethinkcode.swingy.model.Model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoaderSaver {
    public void loadGame(String gameString){
        System.out.println("Loading game : ");
        System.out.println(gameString.split("/")[0]);
        System.out.println("/");
        System.out.println(gameString.split("/")[1]);
        fileReader(gameString);
    }

    public void saveGame(Model model) {
        fileWriter(model.hero.getName() + "/" + model.hero.getClass(), model);
    }

    private String[] fileReader(String gameString) {
        String filename = gameString + ".txt";
        String line;
        List<String> lines = new ArrayList<String>();
        BufferedReader abc;
        try {
            abc = new BufferedReader(new FileReader(filename));
            while ((line = abc.readLine()) != null) {
                lines.add(line);
            }
        abc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            

        // If you want to convert to a String[]
        String[] data = lines.toArray(new String[]{});
        return data;
    }
    private void fileWriter(String fileName, Model model){}
    public File[] getSavedGameList(String pathToSavedGames){
        File folder = new File(pathToSavedGames);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }
}