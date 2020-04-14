package Main;

import AllCards.*;
import Heros.Hero;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;

public class JsonBuilders {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void PlayerJsonBuilder(String username, Player player) {
        try {
            String path = String.format("resources\\Jsons\\Players\\%s", username);
            File file = new File(path);
            file.mkdir();
            path = path + "\\player.json";
            FileWriter fileWriter = new FileWriter(path);
            objectMapper.writeValue(fileWriter, player);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void NewPlayerHeroBuilder(Player player) throws IOException {
        String path = String.format("resources\\Jsons\\Players\\%s", player.getUsername());
        String st = path + "\\mage.json";
        FileWriter fileWriter = new FileWriter(st);
        objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("mage"));
        fileWriter.close();
        st = path + "\\rogue.json";
        fileWriter = new FileWriter(st);
        objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("rogue"));
        fileWriter.close();
        st = path + "\\warlock.json";
        fileWriter = new FileWriter(st);
        objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("warlock"));
        fileWriter.close();
    }


    public static void HeroBuilder(Player player, Hero hero) throws IOException {
        String st = String.format("resources\\Jsons\\Players\\%s\\%s.json", player.getUsername(), hero.getName().toLowerCase());
        FileWriter fileWriter = new FileWriter(st);
        objectMapper.writeValue(fileWriter, hero);
        fileWriter.close();
    }

    public static void WeaponBuilder(Weapon weapon) throws IOException {
        String st = String.format("resources\\Jsons\\Cards\\%s.json", weapon.getName().toLowerCase());
        FileWriter fileWriter = new FileWriter(st);
        objectMapper.writeValue(fileWriter, weapon);
        fileWriter.close();
    }

    public static void SpellBuilder(Spell spell) throws IOException {
        String st = String.format("resources\\Jsons\\Cards\\%s.json", spell.getName().toLowerCase());
        FileWriter fileWriter = new FileWriter(st);
        objectMapper.writeValue(fileWriter, spell);
        fileWriter.close();
    }
}
