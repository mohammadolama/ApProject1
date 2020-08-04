package Server.Controller.MainLogic;


import Server.Model.Player;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class LogInSignUp {
    private static File file = new File("resources/Model.Player.txt");

    public void DeleteAccount(Player player) {
        try {
            Scanner sc2 = new Scanner(file);
            File temp = new File("resources/user/temp.txt");
            PrintWriter pw1 = new PrintWriter(temp);
            while (sc2.hasNext()) {
                String st56 = sc2.nextLine();
                if (!st56.equals("User : " + player.getUsername())) {
                    pw1.write(st56 + "\n");
                }
            }
            pw1.close();
            sc2 = new Scanner(temp);
            pw1 = new PrintWriter(file);
            while (sc2.hasNext()) {
                String st56 = sc2.nextLine();
                pw1.write(st56 + "\n");
            }
            pw1.close();
            String st = String.format("resources/players/%s-%s.log", player.getUsername(), player.getPlayerID());
            temp = new File("resources/players/temp.log");
            file = new File(st);
            FileReader fileReader = new FileReader(file);
            FileWriter fileWriter = new FileWriter(temp);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                bufferedWriter.write(string + "\n");
                if (string.equals("User : " + player.getUsername())) {
                    Date date = new Date();
                    bufferedWriter.write("Deleted at : " + date.toString() + "\n");
                }
            }
            fileReader.close();
            bufferedReader.close();
            bufferedWriter.close();
            fileReader = new FileReader(temp);
            fileWriter = new FileWriter(st);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            while ((string = bufferedReader.readLine()) != null) {
                bufferedWriter.write(string + "\n");
            }
            bufferedReader.close();
            bufferedWriter.close();
            fileReader.close();
            fileWriter.close();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private boolean DuplicateUserChecker(String user) {
        try {
            Scanner sc2 = new Scanner(file);
            while (sc2.hasNext()) {
                String st56 = sc2.nextLine();
                if (st56.equals("User : " + user)) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }


    private boolean UserFinder(String user) {
        file = new File("resources/Model.Player.txt");
        Scanner sc2;
        try {
            sc2 = new Scanner(file);
            boolean flag = false;
            while (sc2.hasNext()) {
                String st56 = sc2.nextLine();
                if (st56.equals("User : " + user)) {
                    flag = true;
                }
            }
            if (flag) {
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean PassChecker(String username, String password) {
        try {

            Scanner sc2 = new Scanner(file);
            boolean flag = false;
            while (sc2.hasNext()) {
                String st56 = sc2.nextLine();
                if (st56.equals("User : " + username) && sc2.nextLine().equals("Password : " + password)) {
                    flag = true;
                }
            }
            if (flag) {
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String create(String user, String pass) {
        try {
            if (DuplicateUserChecker(user)) {
                FileWriter fileWriter = new FileWriter("resources/Model.Player.txt", true);
                PrintWriter pw = new PrintWriter(fileWriter);
                pw.write("User : " + user + "\n");
                pw.write("Password : " + pass + "\n");
                pw.write("**********************" + "\n");
                Player player = new Player(user, pass);
                JsonBuilders.PlayerJsonBuilder(user, player);
//                JsonBuilders.NewPlayerHeroBuilder(player);
                pw.close();
                return "ok";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user already exist";
    }

    public String check(String user, String password) {
        if (UserFinder(user)) {
            if (PassChecker(user, password)) {
                Player player = JsonReaders.PlayerJsonReader(user);
//                Gamestate.setPlayer(player);
                LOGGER.playerlog(player, "Sign_in");
                return "ok";
            } else {
                return "wrong password";
            }
        } else {
            return "user not found";
        }
    }


}