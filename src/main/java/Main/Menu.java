package Main;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
    static String username;
    static String password;

    static void Menu(Player player) throws InterruptedException, IOException, ParseException {
        username = player.getUsername();
        password = player.getPassword();
        Scanner sc = new Scanner(System.in);

        if (player.getNewToGame()) {
            player.SetFirstHero(player);
        }



        while (true) {
            player.getSelectedHero().UpdateDeck(); // build card objects from json and put in hero arraylist

            System.out.println(ConsoleColors.CYAN_BOLD + "**      Menu        **" + ConsoleColors.RESET);
            System.out.println("\n" +
                    "start       : start the game" +
                    "\n" + "Store       : enter store" +
                    "\n" + "Collections : enter Card Management area " +
                    "\n" + "Info        : show your player's detail " +
                    "\n" + "Delete      : delete your account" +
                    "\n" + "Hero      : change your current hero" +
                    "\n" + "Quit        : quit the game ( return to login/signup area)" +
                    "\n" + "Exit        : exit the game"
            );
            String st = sc.next().toLowerCase();
             if (st.equals("delete")) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "enter your password : " + ConsoleColors.RESET);
                String st1 = sc.next();
                if (st1.equals(password)) {
                    LogInSignUp.DeleteAccount(player);
                    LOGGER.playerlog(player,"Sign out");
                    return;
                }
            }
            switch (st) {
                case "exit":
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Are you Sure ? (y/n)" + ConsoleColors.RESET);
                    switch (sc.next().toLowerCase()) {
                        case "y":
                            System.exit(0);
                            break;
                    }
                    break;
                case "quit" :
                    System.out.println("\n");
                    LOGGER.playerlog(player,"Sign out ");
                    return;
                case "store":LOGGER.playerlog(player, "Navigate : Store");
                    Shop.Shop(player);break;
                case "hero":HeroChanger.HeroChanger(player);break;
                case "hesoyam" :player.setLevel(player.getLevel() + 1);
                    System.out.println("\n"+ConsoleColors.BLUE_BOLD + "**  LEVEL UP    **" + ConsoleColors.RESET+"\n");
                    JsonBuilders.PlayerJsonBuilder(player.getUsername(), player);break;
                case "aezakmi" : HeroChanger.HeroAdder(player);
                    System.out.println("\n"+ConsoleColors.BLUE_BOLD + "**  CHEAT ACTIVATED    **" + ConsoleColors.RESET+"\n");break;
                case"info" : Info.playerinfo(player);
                    TimeUnit.SECONDS.sleep(1);break;
                case "collections" : LOGGER.playerlog(player, "Navigate : Collections");
                    Collections.Collections(player);break;
                case "start":System.out.println(ConsoleColors.RED_BACKGROUND + "ACCESS DENIED ." + ConsoleColors.RESET);break;
                default:System.out.println(ConsoleColors.RED_BOLD + "Invalid" + ConsoleColors.RESET+"\n");
            }
        }
    }
}