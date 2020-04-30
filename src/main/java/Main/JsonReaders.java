package Main;

import AllCards.*;
import Heros.Hero;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;

public class JsonReaders {
    static Player PlayerJsonReader(String username) {
        String path = String.format("resources\\Jsons\\Players\\%s\\player.json", username);
        Player player = null;
        try {
            FileReader fileReader = new FileReader(path);
            ObjectMapper objectMapper = new ObjectMapper();
            player = objectMapper.readValue(fileReader, Player.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

    static Hero NewPlayerHeroReader(String hero) {
        String path = String.format("resources\\Jsons\\Heros\\%s.json", hero.toLowerCase());
        Hero hero1 = null;
        try {
            FileReader fileReader = new FileReader(path);
            ObjectMapper objectMapper = new ObjectMapper();
            hero1 = objectMapper.readValue(fileReader, Hero.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hero1;
    }

    public static Hero HeroJsonReader(Player player, String hero) {
        String path = String.format("resources\\Jsons\\Players\\%s\\%s.json", player.getUsername(), hero.toLowerCase());
        Hero hero1 = null;
        try {
            FileReader fileReader = new FileReader(path);
            ObjectMapper objectMapper = new ObjectMapper();
            hero1 = objectMapper.readValue(fileReader, Hero.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hero1;
    }

    public static Minions MinionsReader(String minions) {
        String path = String.format("resources\\Jsons\\Cards\\%s.json", minions);
        Minions minion = null;
        try {
            FileReader fileReader = new FileReader(path);
            ObjectMapper objectMapper = new ObjectMapper();
            minion = objectMapper.readValue(fileReader, Minions.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return minion;
    }

    public static Spell SpellReader(String spell) {
        String path = String.format("resources\\Jsons\\Cards\\%s.json", spell);
        Spell spells = null;
        try {
            FileReader fileReader = new FileReader(path);
            ObjectMapper objectMapper = new ObjectMapper();
            spells = objectMapper.readValue(fileReader, Spell.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spells;
    }

    public static Weapon WeaponReader(String weapon) {
        String path = String.format("resources\\Jsons\\Cards\\%s.json", weapon);
        Weapon weapon1 = null;
        try {
            FileReader fileReader = new FileReader(path);
            ObjectMapper objectMapper = new ObjectMapper();
            weapon1 = objectMapper.readValue(fileReader, Weapon.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weapon1;
    }
}
