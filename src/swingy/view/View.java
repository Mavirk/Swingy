package swingy.view;
import javax.swing.*;

public class View {
    private JPanel panel1;
    private JTextPane outputPane;
    private JTextField inputBox;
    private JButton goButton;
    boolean swingy = false;
    public String[] info;
    public String[] options;

    public View(String viewType, String[] info, String[] options) {
        this.info = info;
        this.options = options;
        switch (viewType) {
            case "swingy":
                swingy = true;
                break;
            case "console":
                swingy = false;
                break;
        }
    }

    public void printLineArray(String[] lineArray) {
        for (String o : options) {
            if (swingy) {
                System.out.println("swingy output" + o);
            } else {
                System.out.println(o);
            }
        }
    }
}
