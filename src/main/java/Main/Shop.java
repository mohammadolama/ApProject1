package Main;

import Enums.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Shop {
    private static Player player123;
    static Scanner sc = new Scanner(System.in);

    static void Shop(Player player) throws IOException, ParseException {
        player123 = player;
        while (true) {
            System.out.println(ConsoleColors.CYAN_BOLD + "**        STORE        **" + ConsoleColors.RESET+"\n"+"\n");
            System.out.println(ConsoleColors.YELLOW_BOLD + "Sell , Buy or Info ?Enter\'money\' to show your wallet. Enter \"back\" to return. " + ConsoleColors.RESET);
            String st = sc.next().toLowerCase();
            switch (st){
                case "back":
                    System.out.println("\n");
                    LOGGER.playerlog(player123,"Navigate : Main Menu");
                    JsonBuilders.PlayerJsonBuilder(player123.getUsername(), player123);
                    return;
                case "money":System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "You have " + player123.getMoney() + " coins ." + ConsoleColors.RESET);break;
                case "buy" : Buy();break;
                case "sell" : Sell();break;
                case "info" : Info.Info(player123);break;
                default:continue;
            }
        }
    }

    static void Buy() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN_BOLD + "**       BUY Menu        **" + ConsoleColors.RESET+"\n"+"\n");
        while (true) {
            System.out.println(ConsoleColors.YELLOW_BOLD + "Which card do you want to buy ? type \"back\" to return to SHOP" + ConsoleColors.RESET+"\n"+ BuyableCards());
            String st = sc.next().toLowerCase();
            boolean flag = false;
            if (st.equals("back")) {
                JsonBuilders.PlayerJsonBuilder(player123.getUsername(), player123);
                return;
            }
            for (Carts cartss : BuyableCards()) {
                if (st.equals(cartss.toString().toLowerCase())) {
                    if (player123.getMoney() > Price(st)) {
                        if (Info.WantToDo(st)) {
                            System.out.println();
                            player123.setMoney(player123.getMoney() - Price(st));
                            ArrayList<Carts> ar = player123.getPlayerCarts();
                            ar.add(Carts.valueOf(st));
                            player123.setPlayerCarts(ar);
                            Update.AddToPcards(st,player123);
                            player123.setSelectedHero(JsonReaders.HeroJsonReader(player123,player123.getSelectedHero().getName().toLowerCase()));
                            LOGGER.playerlog(player123,String.format("ADD : %s  is added to purchased cards .",st));
                            System.out.println(String.format(ConsoleColors.PURPLE_BOLD_BRIGHT + " %s is added to your Purchased cards list .", st) + ConsoleColors.RESET);
                        }
                    } else {
                        System.out.printf(ConsoleColors.RED_BOLD + "You don't have enough money.The price of the card is " + Price(st) + "but you have only " + player123.getMoney() + " coins." + ConsoleColors.RESET);
                    }
                    System.out.println();
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                System.out.println(ConsoleColors.RED_BOLD+ "invalid input ." + ConsoleColors.RESET+"\n");
            }
            JsonBuilders.PlayerJsonBuilder(player123.getUsername(), player123);
        }
    }

    static void Sell() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN_BOLD + "**       Sell Main.Menu        **" + ConsoleColors.RESET+"\n"+"\n");
        while (true) {
            boolean flag = false;
            System.out.println(ConsoleColors.YELLOW_BOLD + "Enter the name of the card yo want to Sell. Enter \"back\" to return to SHOP ." + ConsoleColors.RESET);
            System.out.println(Update.UpdateSellCards(player123).toString());
            String st = sc.next().toLowerCase();
            if (st.equals("back")) {
                JsonBuilders.PlayerJsonBuilder(player123.getUsername(), player123);
                return;
            } else {
                ArrayList<Carts> ar = Update.UpdateSellCards(player123);
                for (Carts cartss : ar) {
                    if (st.equals(cartss.toString())) {
                        if (Info.WantToDo(st)) {
                            player123.setMoney(player123.getMoney() + (Price(st) / 2));
                            ArrayList<Carts> ar2 = player123.getPlayerCarts();
                            ar2.remove(Carts.valueOf(st));
                            Update.RemoveFromPcards(player123,st);
                            LOGGER.playerlog(player123,String.format("ADD : %s  is removed from  purchased cards .",st));
                        }
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    System.out.println(ConsoleColors.RED_BOLD + "invalid input ." + ConsoleColors.RESET+"\n");
                }
            }
        }
    }




    private static ArrayList<Carts> BuyableCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (NeutralCarts cartss : NeutralCarts.values()) {
            int i = 0;
            for (Carts cartsss : player123.getPlayerCarts()) {
                if (cartss.toString().equalsIgnoreCase(cartsss.toString())) {
                    i++;
                }
            }
            if (i < 2) {
                ar.add(Carts.valueOf(cartss.toString()));
            }
        }
        for (SpecialCarts cartss : SpecialCarts.values()) {
            int i = 0;
            for (Carts cartsss : player123.getPlayerCarts()) {
                if (cartss.toString().equalsIgnoreCase(cartsss.toString())) {
                    i++;
                }
            }
            if (i < 2) {
                for (Carts cartsss : player123.getSelectedHero().getSPcarts()) {
                    if (cartss.toString().equalsIgnoreCase(cartsss.toString())) {
                        ar.add(cartsss);
                        break;
                    }
                }
            }
        }
        return ar;
    }



    private static long Price(String name) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(String.format("Jsons\\Cards\\%s.json", name));
        Object object = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) object;
        long n = (Long) jsonObject.get("price");
        return n;
    }
}
