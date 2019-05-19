package com.wethinkcode.swingy;
import com.wethinkcode.swingy.controller.Controller;

public class Main {
    //    Main Method
    public static void main(String[] args) {
        String runMode;
        if (args.length == 2) runMode = args[1];
        else runMode = "";
        try {
        Controller controller = new Controller(args[0], runMode);
            controller.startGame();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 
// TODO
// 
//1.    make wepons armour and helm modify attack armour and health
//2.    add 3 more weapons armour and helms with differnt modifiers
//3.    add experience modifiers to enemies
//4.    add leveling accoding to experience from killing enemies
//5.    add level up fucntion to hero that modifies stats
//6.    make battles automatic essencially loop the fight with printouts at the end 
//7.    make running away a 50% chance else force the fight
//8.    load all the saved games from file to display the stats and name and class of all of them 
//9.    change map size calcultions to the one specified in the pdf
//10.   finish swing implentation
//11.   add javax validation to input for character creation  