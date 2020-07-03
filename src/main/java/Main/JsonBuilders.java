package Main;

import Model.Cards.*;
import Model.Heros.Hero;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.*;

public class JsonBuilders {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    }

    public static void PlayerJsonBuilder(String username, Player player) {
        try {
            String path = String.format("resources/Jsons/Players/%s", username);
            File file = new File(path);
            file.mkdir();
            path = path + "/player.json";
            FileWriter fileWriter = new FileWriter(path);
            objectMapper.writeValue(fileWriter, player);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void NewPlayerHeroBuilder(Player player) {
        String path = String.format("resources/Jsons/Players/%s", player.getUsername());
        try {
            String st = path + "/mage.json";
            FileWriter fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("mage"));
            fileWriter.close();
            st = path + "/rogue.json";
            fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("rogue"));
            fileWriter.close();
            st = path + "/warlock.json";
            fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("warlock"));
            fileWriter.close();
            st = path + "/priest.json";
            fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("priest"));
            fileWriter.close();
            st = path + "/hunter.json";
            fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("hunter"));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void HeroBuilder(Player player, Hero hero) {
        String st = String.format("resources/Jsons/Players/%s/%s.json", player.getUsername(), hero.getName().toLowerCase());
        try {
            FileWriter fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, hero);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void HeroBuildeeer(Hero hero) {
        String st = String.format("resources/Jsons/%s.json", hero.getName().toLowerCase());
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, hero);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void WeaponBuilder(Weapon weapon) {
        String st = String.format("resources/Jsons/Cards/%s.json", weapon.getName().toLowerCase());
        try {
            FileWriter fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, weapon);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SpellBuilder(Spell spell) {
        String st = String.format("resources/Jsons/Cards/%s.json", spell.getName().toLowerCase());
        try {
            FileWriter fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, spell);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void minionBuilder(Minion minions) {
        String st = String.format("resources/Jsons/Cards/%s.json", minions.getName().toLowerCase());
        try {
            FileWriter fileWriter = new FileWriter(st);
            objectMapper.writeValue(fileWriter, minions);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
