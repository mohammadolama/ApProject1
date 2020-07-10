package Main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LOGGER {
    public static void playerlog(Player player, String string) {
        System.out.println(player.toString() + "\t" + player.getPlayerID());
        String st = String.format("resources/players/%s-%s.log", player.getUsername(), player.getPlayerID());
        Calendar calendar = Calendar.getInstance();
        try {
            FileWriter fileWriter = new FileWriter(st, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Locale locale = new Locale("English", "England");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", locale);
            bufferedWriter.write(formatter.format(calendar.getTime()) + "  " + string + "\n");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}