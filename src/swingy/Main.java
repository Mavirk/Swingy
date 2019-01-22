package swingy;
import swingy.controller.Controller;

public class Main {
    //    Main Method
    public static void main(String[] args) {
        try {
            Controller controller = new Controller(args[0]);
            controller.mainMenu();

        } catch (Exception e) {
            System.out.println("RUNTIME :: ERROR");
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
}