package src.main.com.wethinkcode.swingy;
import src.main.com.wethinkcode.swingy.controller.Controller;

public class Main {
    //    Main Method
    public static void main(String[] args) {
        try {
            Controller controller = new Controller(args[0]);
            controller.mainMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
