package com.wethinkcode.swingy.controller;

import com.wethinkcode.swingy.model.LoaderSaver;
import java.io.File;

class InputChecker {
    LoaderSaver loaderSaver;
    public String resourcesPath = "resources/saved/";

    public boolean checkLoadInput(String gameString) {
        String tempString = "";
        File[] savedGames = loaderSaver.getSavedGameList(resourcesPath);
        for (File f : savedGames) {
            tempString = f.getName();
            if (tempString.equals(gameString))
                return true;
        }
        return false;
    }

    public boolean checkNavigationInput1(String input) {
        switch (input) {
        case "north":
            break;
        case "east":
            break;
        case "south":
            break;
        case "west":
            break;
        default:
            return false;
        }
        return true;
    }
}