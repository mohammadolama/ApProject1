package Server.Controller.MainLogic;

import Server.Model.Log.Log;
import Server.Model.Player;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LOGGER {
    public static void playerlog(Player player, String string) {
        Calendar calendar = Calendar.getInstance();
        Locale locale = new Locale("English", "England");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", locale);
        String date = formatter.format(calendar.getTime());
        Log log = new Log(date, player, string);
        DataBaseManagment.saveLog(log);
    }
}