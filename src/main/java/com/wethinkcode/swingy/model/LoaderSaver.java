package com.wethinkcode.swingy.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoaderSaver {
    public void loadGame(String gameString){
        System.out.println("Loading game : ");
        System.out.println(gameString.split("/")[0]);
        System.out.println("/");
        System.out.println(gameString.split("/")[1]);
        fileReader(gameString)
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
        } catch (FileNotFoundException fnf | IOException e) {
            e.printStackTrace();
        }
            
        abc.close();

        // If you want to convert to a String[]
        String[] data = lines.toArray(new String[]{});
        return new String[0];
    }
    private void fileWriter(String fileName ,Model model){}
}