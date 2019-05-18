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
