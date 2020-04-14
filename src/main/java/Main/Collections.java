package Main;
import Enums.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Collections {
    private static Player player123;
    static Scanner sc = new Scanner(System.in);

    static void Collections(Player player) throws IOException {
        player123 = player;
        System.out.println("\n"+ConsoleColors.CYAN_BOLD + "**    Collections     **" + ConsoleColors.RESET+"\n");
        while (true) {
            System.out.println(ConsoleColors.YELLOW_BOLD +
                    "\n ALLPCards  :  Show your ALL purchased cards (from every class)" +
                    "\n PCards     :  Show your purchased cards (neutrals + current hero class cards) " +
                    "\n Dcards     :  Show the cards in your current hero's deck ." +
                    "\n Add        :  Show all cards you can add to your deck , then select one of them ."+
                    "\n Remove     :  Show all cards you can remove from your deck , then select one of them "+
                    "\n If you want information about cards, enter \"Info\" ." +
                    "\n Enter \"back\" to return to Menu ." + ConsoleColors.RESET);
            String st = sc.next().toLowerCase();
            if (st.equalsIgnoreCase("Pcards")) {
                LOGGER.playerlog(player,"List : All current class cards");
                System.out.println(dv()+"\n");
            } else if (st.equalsIgnoreCase("ALLPCARDS")) {
                LOGGER.playerlog(player,"List : All purchased cards");
                System.out.println(player123.getPlayerCarts()+"\n");
            } else if (st.equalsIgnoreCase("Dcards"))
            {
                LOGGER.playerlog(player,"List : deck cards");
                System.out.println(player123.getSelectedDeck().getHero().getDcarts()+"\n");
            } else if (st.equalsIgnoreCase("info")) {
                System.out.println();
                Info.Info(player123);
            }else if(st.equalsIgnoreCase("add")){
                System.out.println();
                Add();
            }else if(st.equalsIgnoreCase("remove")) {
                System.out.println();
                Remove();
            } else if (st.equalsIgnoreCase("back")) {
                LOGGER.playerlog(player,"Navigate : Main Menu ");
                return;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid input" + ConsoleColors.RESET);
            }
        }
    }

    private static void Remove() throws IOException {
        while (true) {
            boolean flag = false;
         if (player123.getSelectedDeck().getHero().getDcarts().size() == 0) {
                System.out.println(ConsoleColors.RED_BOLD + "Your deck is empty. Nothing to remove." + ConsoleColors.RESET);
                return;
            }
            System.out.println(ConsoleColors.YELLOW_BOLD + "Which card do you want to remove from your deck ? Enter \"back\" to return." + ConsoleColors.RESET);
            System.out.println(player123.getSelectedDeck().getHero().getDcarts());
            String st = sc.next().toLowerCase();
            if (st.equals("back")) {
                JsonBuilders.PlayerJsonBuilder(player123.getUsername(), player123);
                return;
            } else {
                ArrayList<Carts> ar = player123.getSelectedDeck().getHero().getDcarts();
                for (Carts cartss : ar) {
                    if (st.equals(cartss.toString())) {
                        if (player123.getSelectedDeck().getHero().getDcarts().size() >= 9) {
                            if (Info.WantToDo(st)) {
                                ar.remove(Carts.valueOf(st));
                                player123.getSelectedDeck().getHero().UpdateAddPurchasedCards(Carts.valueOf(st));
                                System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + String.format("%s" + "is removed from your deck.", st) + ConsoleColors.RESET+"\n");
                                LOGGER.playerlog(player123,String.format("Remove : %s removed from deck.",st));
                                JsonBuilders.HeroBuilder(player123,player123.getSelectedDeck().getHero());
                            }
                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "Action not possible. Minimum number of cards in your deck should be 8." + ConsoleColors.RESET+"\n");
                        }
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    System.out.println(ConsoleColors.RED_BOLD + "invalid input ." + ConsoleColors.RESET+"\n");
                }
                player123.getSelectedDeck().getHero().setDcarts(ar);
            }
        }
    }

    private static void Add() throws IOException {
        while (true) {
            if (player123.getSelectedDeck().getHero().getPcarts().size() == 0) {
                System.out.println(ConsoleColors.RED_BOLD + "No cards to be added to your deck. All your cards are in your deck ." + ConsoleColors.RESET+"\n");
                return;
            }
            System.out.println(ConsoleColors.YELLOW_BOLD + "Which card do you want to add to your deck ? type \"back\" to return ." + ConsoleColors.RESET+"\n");
            System.out.println(player123.getSelectedDeck().getHero().getPcarts());
            String st = sc.next().toLowerCase();
            boolean flag = false;
            if (st.equals("back")) {
                JsonBuilders.PlayerJsonBuilder(player123.getUsername(), player123);
                return;
            }
            for (Carts cartss : player123.getSelectedDeck().getHero().getPcarts()) {
                if (st.equals(cartss.toString().toLowerCase())) {
                    if (player123.getSelectedDeck().getHero().getDcarts().size() < 15) {
                        if (Info.WantToDo(st)) {
                            player123.getSelectedDeck().getHero().UpdateAddDeckCards(Carts.valueOf(st));
                            player123.getSelectedDeck().getHero().UpdateRemovePurchasedCards(Carts.valueOf(st));
                            System.out.println(String.format(ConsoleColors.PURPLE_BOLD_BRIGHT + " %s is added to your deck .", st) + ConsoleColors.RESET+"\n");
                            LOGGER.playerlog(player123,String.format("ADD : %s added to deck",st));
                            JsonBuilders.HeroBuilder(player123,player123.getSelectedDeck().getHero());
                        }
                        flag = true;
                        break;
                    } else {
                        System.out.println("\n"+ConsoleColors.RED_BOLD + "Sorry but your deck is full." + ConsoleColors.RESET);
                    }
                }
            }
            if (flag == false) {
                System.out.println(ConsoleColors.RED_BOLD + "invalid input ." + ConsoleColors.RESET+"\n");
            }
        }
    }

    private static ArrayList<Carts> dv() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (Carts cartss : player123.getSelectedDeck().getHero().getDcarts()) {
            ar.add(cartss);
        }
        for (Carts cartss : player123.getSelectedDeck().getHero().getPcarts()) {
            ar.add(cartss);
        }
        return ar;
    }

}
