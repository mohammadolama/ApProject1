package Main;

import AllCards.*;
import Enums.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Info {
    static Player player123;
    static void Info(Player player) {
        player123=player;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(ConsoleColors.CYAN_BOLD + "**      INFO        **" + ConsoleColors.RESET+"\n");
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Minions : " + ConsoleColors.RESET);
            System.out.println(Arrays.toString(MinionCarts.values()));
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Spells : " + ConsoleColors.RESET);
            System.out.println(Arrays.toString(SpellCarts.values()));
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Weapons : " + ConsoleColors.RESET);
            System.out.println(Arrays.toString(WeaponCarts.values()));
            System.out.println("\n"+ConsoleColors.YELLOW_BOLD + "Choose your card to show its Info .Enter \"back\" to return . "+"\n");
            String st = sc.next().toLowerCase();
            if (st.equalsIgnoreCase("back")){
                return;
            }
            info(st.toLowerCase());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.playerlog(player ,e.toString());
            }
            System.out.println();
        }
    }

    static void info(String name) {
        for (MinionCarts minionCarts : MinionCarts.values()) {
            if (minionCarts.toString().equalsIgnoreCase(name)) {
                Minions minions = null;
                try {
                    minions = JsonReaders.MinionsReader(name.toLowerCase());
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.playerlog(player123,e.toString());
                }
                System.out.println(ConsoleColors.BLUE_BOLD + " Name        :   " + minions.getName() +
                        "\n Description :   " + minions.getDescription() +
                        "\n Price       :   " + minions.getPrice()+
                        "\n Hero class  :   " + minions.getHeroClass()+
                        "\n ManaCost    :   " + minions.getManaCost() +
                        "\n Attack      :   " + minions.getAttack() +
                        "\n Rarity      :   " + minions.getRarity()+
                        "\n HP          :   " + minions.getHealth() + ConsoleColors.RESET);
                return;
            }
        }
        for (SpellCarts spellCarts : SpellCarts.values()) {
            if (spellCarts.toString().equalsIgnoreCase(name)) {
                Spell spell = null;
                try {
                    spell = JsonReaders.SpellReader(name.toLowerCase());
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.playerlog(player123,e.toString());
                }
                System.out.println(ConsoleColors.BLUE_BOLD +
                        " Name        :   " + spell.getName() +
                        "\n Description :   " + spell.getDescription() +
                        "\n Price       :   " + spell.getPrice()+
                        "\n Hero class  :   " + spell.getHeroClass()+
                        "\n ManaCost    :   " + spell.getManaCost() +
                        "\n Rarity      :   " + spell.getRarity()+
                        "\n Usage Time  :   " + spell.getUsageTimes() +
                        ConsoleColors.RESET);
                return;
            }
        }
        for (WeaponCarts weaponCarts : WeaponCarts.values()) {
            if (weaponCarts.toString().equalsIgnoreCase(name)) {
                Weapon weapon = null;
                try {
                    weapon = JsonReaders.WeaponReader(name.toLowerCase());
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.playerlog(player123,e.toString());
                }
                System.out.println(ConsoleColors.BLUE_BOLD + " Name        :   " + weapon.getName() +
                        "\n Description :   " + weapon.getDescription() +
                        "\n Price       :   " + weapon.getPrice()+
                        "\n Hero class  :   " + weapon.getHeroClass()+
                        "\n ManaCost    :   " + weapon.getManaCost() +
                        "\n Attack      :   " + weapon.getAtt() +
                        "\n Rarity      :   " + weapon.getRarity()+
                        "\n Durability  :   " + weapon.getDurability() + ConsoleColors.RESET);
                return;
            }
        }
        System.out.println(ConsoleColors.RED_BOLD + "Invalid Input ." + ConsoleColors.RESET);
    }

    static void playerinfo(Player player){
        System.out.println(ConsoleColors.BLUE_BOLD +
                "\n Username        :   " + player.getUsername() +
                "\n Password        :   " + player.getPassword() +
                "\n Level           :   " + player.getLevel() +
                "\n AvailableHeros  :   " + player.getPlayerHeroes().toString()+
                "\n EXP             :   " + player.getExp()+ ConsoleColors.RESET);
    }

    static Boolean WantToDo(String string) {
        Scanner sc=new Scanner(System.in);
        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT+String.format("You choose %s",string)+ConsoleColors.RESET);
        info(string);
        while (true){
            System.out.println(ConsoleColors.YELLOW_BOLD+"Continue ? (y/n)"+ConsoleColors.RESET);
            String st=sc.next();
            switch (st){
                case "y":return true;
                case "n":return false;
                default:System.out.println(ConsoleColors.RED_BOLD+"INVALID INPUT"+"\n");
            }
        }
    }
}