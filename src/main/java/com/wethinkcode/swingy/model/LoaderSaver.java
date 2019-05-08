package com.wethinkcode.swingy.model;

import com.wethinkcode.swingy.model.Model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LoaderSaver {
    public void loadGame(String gameString){
        System.out.println("Loading game : ");
        System.out.println(gameString.split("/")[0]);
        System.out.println("/");
        System.out.println(gameString.split("/")[1]);
    }

    public void saveGame(Model model) {
        String gameName = model.hero.getName() + "-" + model.hero.getOccupation();
        gameName = gameName.replace("/", "");
        String filePath = "resources/saved/" + gameName + ".ser";
        System.out.println("Saving game to file: " + filePath);
        try {
            File gameFile = new File(filePath);
            gameFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(filePath, false);
            ObjectOutput out = new ObjectOutputStream(fileOut);
            out.writeObject(model);
            out.close();
            fileOut.close();
            System.out.printf("Game is saved in: " + filePath);
         } catch (IOException i) {
            i.printStackTrace();
         }
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