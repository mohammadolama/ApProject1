package Main;

import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class LogInSignUp {
    static String user;
    static String password;
    static File file = new File("Main.Player.txt");

    static String DuplicateUserChecker() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your User : " + ConsoleColors.RESET);
            user = sc.next();
            Scanner sc2 = new Scanner(file);
            while (sc2.hasNext()) {
                String st56 = sc2.nextLine();
                if (st56.equals("User : " + user)) {
                    System.out.println(ConsoleColors.RED_BOLD + "User already Exists ." + ConsoleColors.RESET);
                    flag = false;
                }
            }
            if (flag == false)
                flag = true;
            else
                flag = false;
        }
        return user;
    }

    static boolean UserFinder() throws FileNotFoundException {
        file = new File("Main.Player.txt");
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(file);
        boolean flag = false;
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your User : " + ConsoleColors.RESET);
        user = sc.next();
        while (sc2.hasNext()) {
            String st56 = sc2.nextLine();
            if (st56.equals("User : " + user)) {
                System.out.println("account found .");
                flag = true;
            }
        }
        if (flag) {
            return true;
        } else {
            System.out.printf(ConsoleColors.RED_BOLD + "Account Not Found . Try Again ." + ConsoleColors.RESET);
            return UserFinder();
        }
    }

    static boolean PassChecker() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(file);
        boolean flag = false;
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your Password" + ConsoleColors.RESET);
        String pass = sc.next();
        while (sc2.hasNext()) {
            String st56 = sc2.nextLine();
            if (st56.equals("User : " + user) && sc2.nextLine().equals("Password : " + pass)) {
                flag = true;
            }
        }
        if (flag) {
            System.out.println("\n" + ConsoleColors.PURPLE_BOLD + "Login Successfull ." + ConsoleColors.RESET + "\n");
            return true;
        } else {
            System.out.println(ConsoleColors.RED_BOLD + "Wrong password . Try Again " + ConsoleColors.RESET);
            return PassChecker();
        }
    }

    static void DeleteAccount(Player player) throws IOException, InterruptedException {
        Scanner sc2 = new Scanner(file);
        File temp = new File("user\\temp.txt");
        PrintWriter pw1 = new PrintWriter(temp);
        while (sc2.hasNext()) {
            String st56 = sc2.nextLine();
            if (st56.equals("User : " + player.getUsername()) == false) {
                pw1.write(st56 + "\n");
            } else if (st56.equals("User : " + player.getUsername())) {
                continue;
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
        String st = String.format("players\\%s-%s.log", player.getUsername(), player.getId());
        temp = new File("players\\temp.log");
        file = new File(st);
        FileReader fileReader = new FileReader(file);
        FileWriter fileWriter = new FileWriter(temp, true);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String string = new String();
        while ((string = bufferedReader.readLine()) != null) {
            bufferedWriter.write(string + "\n");
            if (string.equals("User : " + player.getUsername())) {
                Date date = new Date();
                bufferedWriter.write("Deleted at : " + date.toString() + "\n");
            }
        }
        fileReader.close();
//        fileWriter.close();
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
        System.out.println(ConsoleColors.PURPLE_BOLD + "Your account has been Deleted ." + ConsoleColors.RESET);
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
    }



   public static boolean DuplicateUserChecker(String user) throws FileNotFoundException {
        boolean flag = true;
            Scanner sc2 = new Scanner(file);
            while (sc2.hasNext()) {
                String st56 = sc2.nextLine();
                if (st56.equals("User : " + user)) {
                    return false;
                }
            }
        return true;
    }


   public static boolean UserFinder(String user) throws FileNotFoundException {
        file = new File("Main.Player.txt");
        Scanner sc2 = new Scanner(file);
        boolean flag = false;
        while (sc2.hasNext()) {
            String st56 = sc2.nextLine();
            if (st56.equals("User : " + user)) {
                flag = true;
            }
        }
        if (flag) {
            return true;
        } else {
            return false;
        }
    }

   public static boolean PassChecker(String username , String password) throws FileNotFoundException {

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
        } else {
            return false;
        }
    }










   public static String create(String user , String pass) throws IOException {
        if (DuplicateUserChecker(user)){
            FileWriter fileWriter = new FileWriter("Main.Player.txt", true);
            PrintWriter pw = new PrintWriter(fileWriter);
            pw.write("User : " + user + "\n");
            pw.write("Password : " + pass + "\n");
            pw.write("**********************" + "\n" + "\n");
            Player player = new Player(user, password);
            JsonBuilders.PlayerJsonBuilder(user, player);
            JsonBuilders.NewPlayerHeroBuilder(player);
            pw.close();
            return "ok";
        }
        else {
            return "user already exist";
        }
    }
   public static String check(String user , String password) throws IOException {
        if (UserFinder(user)){
            if (PassChecker(user,password)){
                Player player = JsonReaders.PlayerJsonReader(user);
                System.out.println(player);
                Gamestate.setPlayer(player);
                LOGGER.playerlog(player, "Sign_in");
                return "ok";
            }
            else {
                return "wrong password";
            }
        }
        else {
            return "user not found";
        }
    }






    public static void LogInSignUp() throws IOException, InterruptedException, ParseException {
        System.out.println("\n" + ConsoleColors.CYAN_BOLD_BRIGHT + " Pay attention : except the Log in/sign up  area , the whole program Inputs are not CaseSenitive." +
                "\n But the program is sensitive to wrong inputs ." +
                "\n when you Enter each menu in the game, use the keywords shown in \"USER-MANUAL GUIDE\" in that menu ." +
                ConsoleColors.GREEN_BOLD + "\n ENJOY" + ConsoleColors.RESET + "\n" + "\n");
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println(ConsoleColors.YELLOW_BOLD + "Already have an account ? (y/n) " + ConsoleColors.RESET);
            String st = sc.next();
            if (st.equalsIgnoreCase("n")) {
                FileWriter fileWriter = new FileWriter("Main.Player.txt", true);
                PrintWriter pw = new PrintWriter(fileWriter);
                pw.write("User : " + DuplicateUserChecker() + "\n");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your Password" + ConsoleColors.RESET);
                password = sc.next();
                pw.write("Password : " + password + "\n");
                pw.write("**********************" + "\n" + "\n");
                System.out.println("Your account has been created .");
                Player player = new Player(user, password);
                JsonBuilders.PlayerJsonBuilder(user, player);
                JsonBuilders.NewPlayerHeroBuilder(player);
                pw.close();
            } else if (st.equalsIgnoreCase("y")) {
                Scanner sc2 = new Scanner(file);
                if (UserFinder()) {
                    if (PassChecker()) {
                        System.out.println("\n" + "\n" + ConsoleColors.RED_BOLD + "L" + ConsoleColors.YELLOW_BOLD + " O" + ConsoleColors.PURPLE_BOLD + " A" + ConsoleColors.GREEN_BOLD + " D" + ConsoleColors.WHITE_BOLD + " I" + ConsoleColors.RED_BOLD_BRIGHT + " N" + ConsoleColors.CYAN_BOLD + " G" + ConsoleColors.RESET + " . . ." + "\n" + "\n");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "Welcome to HEARTHSTONE(" + ConsoleColors.RED_BOLD + "TA" + ConsoleColors.PURPLE_BOLD_BRIGHT + " Edition) ." + ConsoleColors.RESET + "\n" + "\n");
                        TimeUnit.SECONDS.sleep(1);
                        Player player = JsonReaders.PlayerJsonReader(user);
                        LOGGER.playerlog(player, "Sign_in");
                        Menu.Menu(player);
                        System.out.println("See you Later");
                    }
                }
            }
        }
    }
}