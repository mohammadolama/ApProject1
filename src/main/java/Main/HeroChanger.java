package Main;

import Enums.Carts;
import Enums.Heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HeroChanger {
    static Player player123;
    static String hero = null;

    static void HeroChanger(Player player) throws IOException {
        player123 = player;
        boolean flag = true;
        while (flag) {
            Scanner sc = new Scanner(System.in);
            System.out.println(ConsoleColors.YELLOW_BOLD + "You can choose one of the heroes below : " + ConsoleColors.RESET);
            System.out.println(player.getPlayerHeroes().toString());
            hero = sc.next().toLowerCase();
            if (hero.equalsIgnoreCase("mage") || hero.equalsIgnoreCase("rouge") || hero.equalsIgnoreCase("warlock")) {
                if (player.getPlayerHeroes().contains(Heroes.valueOf(hero))) {
                    JsonBuilders.HeroBuilder(player, player.getSelectedHero());
                    switch (hero) {
                        case "mage":
                            player.setSelectedHero(JsonReaders.HeroJsonReader(player, "mage"));
                            flag = false;
                            break;
                        case "rouge":
                            player.setSelectedHero(JsonReaders.HeroJsonReader(player, "rouge"));
                            flag = false;
                            break;
                        case "warlock":
                            player.setSelectedHero(JsonReaders.HeroJsonReader(player, "warlock"));
                            flag = false;
                            break;
                    }
                }
            }else {
                System.out.println("\n" + ConsoleColors.RED_BOLD + "invalid input" + ConsoleColors.RESET + "\n");
            }
        }
        System.out.println();
        System.out.println("\n" + String.format("You choose %s as your hero. You can change that anytime later.", hero.toUpperCase()) + "\n" + "\n");
        LOGGER.playerlog(player123, String.format("Select : %s as selected hero .", hero.toUpperCase()));
        JsonBuilders.PlayerJsonBuilder(player.getUsername(), player123);
        return;
    }

    static void HeroAdder(Player player) {
        ArrayList<Heroes> ar = player.getPlayerHeroes();
        if (ar.contains(Heroes.mage) == false)
            ar.add(Heroes.mage);
        if (ar.contains(Heroes.warlock) == false)
            ar.add(Heroes.warlock);
        if (ar.contains(Heroes.rouge) == false)
            ar.add(Heroes.rouge);
        player.setPlayerHeroes(ar);
        ArrayList<Carts> ar2 = player.getPlayerCarts();
        if (ar2.contains(Carts.dreadscale) == false)
            ar2.add(Carts.dreadscale);
        if (ar2.contains(Carts.polymorph) == false)
            ar2.add(Carts.polymorph);
        if (ar2.contains(Carts.friendlysmith) == false)
            ar2.add(Carts.friendlysmith);
        player.setPlayerCarts(ar2);
    }
}
