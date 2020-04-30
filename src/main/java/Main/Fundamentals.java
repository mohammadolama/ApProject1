package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fundamentals {
    public static void MkDirs() {
        try {
            File file = new File("resources\\players");
            if (!file.isDirectory()) file.mkdir();
            file = new File("resources\\Jsons\\players");
            if (!file.isDirectory()) file.mkdir();
            file = new File("resources\\Main.Player.txt");
            if (!file.exists()) {
                FileWriter fileWriter = new FileWriter("resources\\Main.Player.txt");
                fileWriter.close();
            }
            file = new File("resources\\user");
            if (!file.isDirectory()) file.mkdir();
            file = new File("resources\\user\\temp.txt");
            if (!file.exists()) {
                FileWriter fileWriter = new FileWriter("resources\\user\\temp.txt");
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}