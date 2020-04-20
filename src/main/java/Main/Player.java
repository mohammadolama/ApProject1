package Main;


import Enums.*;
import Heros.*;

import java.io.*;
import java.util.*;

import Main.*;
import org.codehaus.jackson.annotate.JsonAnyGetter;

import static Main.Deck.DefaultDeck;
import static Main.Deck.DefultAvailableCardsManager;

public class Player implements Serializable {
    private String username;
    private String password;
    private String id;
    private int level;
    private int exp;
    private long money = 1000;
    private Boolean NewToGame = true;
    private ArrayList<Carts> PlayerCarts;
    private ArrayList<Heroes> PlayerHeroes;
    private Deck selectedDeck;

    private HashMap<String, Deck> allDecks;


    public Player() throws IOException {
    }

    public Player(String username, String password) {
        try {
            this.username = username;
            this.password = password;
            this.level = 0;
            this.exp = 0;
            this.setPlayerCarts(DefultAvailableCardsManager());
            this.setSelectedDeck(DefaultDeck());
            allDecks = new HashMap<String, Deck>();
            allDecks.put(getSelectedDeck().getName(), getSelectedDeck());
            this.setId(System.nanoTime() + "");
            String st = String.format("resources\\players\\%s-%s.log", username, this.getId());
            PrintWriter vm = new PrintWriter(st);
            Date date = new Date();
            vm.write("Created in : " + date.toString() + "\n" + "Password : " + password + "\n" + "User : " + username + "\n" + "**********************" + "\n");
            vm.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Heroes> getPlayerHeroes() {
        return PlayerHeroes;
    }

    public void setPlayerHeroes(ArrayList<Heroes> playerHeroes) {
        PlayerHeroes = playerHeroes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Carts> getPlayerCarts() {
        return PlayerCarts;
    }

    public void setPlayerCarts(ArrayList<Carts> playerCarts) {
        PlayerCarts = playerCarts;
    }


    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Boolean getNewToGame() {
        return NewToGame;
    }

    public void setNewToGame(Boolean newToGame) {
        NewToGame = newToGame;
    }

    public Deck getSelectedDeck() {
        return selectedDeck;
    }

    public void setSelectedDeck(Deck selectedDeck) {
        this.selectedDeck = selectedDeck;
    }


    public HashMap<String, Deck> getAllDecks() {
        return allDecks;
    }

    public void setAllDecks(HashMap<String, Deck> allDecks) {
        this.allDecks = allDecks;
    }

    //    void SetFirstHero(Player player) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        String hero = null;
//        boolean flag = true;
//        if (player.getNewToGame() == true) {
//            while (flag) {
//                System.out.println(ConsoleColors.YELLOW_BOLD + "Please select your Hero" + ConsoleColors.RESET);
//                System.out.println(ConsoleColors.CYAN_BOLD + "Mage" + ConsoleColors.RED_BOLD_BRIGHT + "     Rogue" + ConsoleColors.GREEN_BOLD + "       Warlock" + ConsoleColors.RESET);
//                hero = sc.next().toLowerCase();
//                switch (hero) {
//                    case "mage":
//                        ArrayList<Carts> ar=player.getPlayerCarts();
//                        ar.add(Carts.polymorph);
//                        player.setPlayerCarts(ar);
//                        player.setSelectedHero(JsonReaders.HeroJsonReader(player,"mage"));flag=false;
//                        break;
//                    case "rogue":
//                        ArrayList<Carts> ar1=player.getPlayerCarts();
//                        ar1.add(Carts.friendlysmith);
//                        player.setPlayerCarts(ar1);
//                        player.setSelectedHero(JsonReaders.HeroJsonReader(player,"rogue"));flag=false;
//                        break;
//                    case "warlock":
//                        ArrayList<Carts> ar2=player.getPlayerCarts();
//                        ar2.add(Carts.dreadscale);
//                        player.setPlayerCarts(ar2);
//                        player.setSelectedHero(JsonReaders.HeroJsonReader(player,"warlock"));flag=false;
//                        break;
//                    default:System.out.println(ConsoleColors.RED_BOLD + "invalid input" + ConsoleColors.RESET);
//                }
//            }
//            System.out.println("\n"+String.format("You choose %s as your hero. You can change that anytime later.", hero.toUpperCase())+"\n"+"\n");
//            LOGGER.playerlog(player, String.format("Select : %s as first selected hero", hero.toUpperCase()));
//            player.setNewToGame(false);
//            ArrayList<Heroes> ar1=getPlayerHeroes();
//            if (getPlayerHeroes()==null)  ar1=new ArrayList<Heroes>();
//            ar1.add(Heroes.valueOf(hero));
//            player.setPlayerHeroes(ar1);
//            JsonBuilders.PlayerJsonBuilder(player.getUsername(), player);
//        }
//    }
}