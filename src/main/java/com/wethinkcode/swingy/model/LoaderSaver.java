package com.wethinkcode.swingy.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class LoaderSaver {
    public Model loadGame(String gameString){
        Model model = null;
        try {
           FileInputStream fileIn = new FileInputStream(gameString);
           ObjectInputStream in = new ObjectInputStream(fileIn);
           model = (Model) in.readObject();
           in.close();
           fileIn.close();
        } catch (IOException i) {
           i.printStackTrace();
           return model;
        } catch (ClassNotFoundException c) {
           System.out.println("Model class not found");
           c.printStackTrace();
           return model;
        }
        return model;
    }

    public String saveGame(Model model) {
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
         return filePath;
    }

    public File[] getSavedGameList(String pathToSavedGames){
        File folder = new File(pathToSavedGames);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }
}