import java.util.Scanner;
import java.util.List;

class swingy {
    public static void main(String[] args) {
        String scriptPath = "/goinfre/rmurdoch/Documents/Swingy/script"; 
        String input;
        List<String> currentScene;
        System.out.println("Welcome To TextQuest (a *42 swingy* inspired production) \n");
        currentScene = scriptReader(scriptPath + "mainMenu.txt");
        for (String i : currentScene){
            System.out.println(i);
        }
    }
    public List<String>    scriptReader(String filename){
        Scanner sc = new Scanner(new File(filename));
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        return lines;
    }
}