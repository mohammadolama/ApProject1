package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fundamentals {
    static void MkDirs() {
        try {
            File file = new File("players");
            if (file.isDirectory() == false) file.mkdir();
            file = new File("Jsons\\players");
            if (file.isDirectory() == false) file.mkdir();
            file = new File("Main.Player.txt");
            file = new File("Jsons\\Weapons");
            if (file.isDirectory() == false) file.mkdir();
            if (file.exists() == false) {
                FileWriter fileWriter = new FileWriter("Main.Player.txt");
                fileWriter.close();
            }
            file = new File("user");
            if (file.isDirectory() == false) file.mkdir();
            file = new File("user\\temp.txt");
            if (file.exists() == false) {
                FileWriter fileWriter = new FileWriter("user\\temp.txt");
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}