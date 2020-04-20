package Main;

import AllCards.*;
import Heros.Hero;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.*;

public class JsonReaders {
    public static Player PlayerJsonReader(String username) {
        String path=String.format("resources\\Jsons\\Players\\%s\\player.json" , username);
        Player player = null;
        try {
            FileReader fileReader = new FileReader(path);
            ObjectMapper objectMapper = new ObjectMapper();
            player = (Player) objectMapper.readValue(fileReader, Player.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }
    public static Hero NewPlayerHeroReader(String hero) throws IOException {
        String path=String.format("resources\\Jsons\\Heros\\%s.json" , hero.toLowerCase());
        Hero hero1 = null;
        FileReader fileReader=new FileReader(path);
        ObjectMapper objectMapper=new ObjectMapper();
        hero1=(Hero)objectMapper.readValue(fileReader,Hero.class);
        fileReader.close();
        return hero1;
    }
    public static Hero HeroJsonReader(Player player , String hero) {
        String path=String.format("resources\\Jsons\\Players\\%s\\%s.json" ,player.getUsername(), hero.toLowerCase());
        Hero hero1 = null;
        try {
            FileReader fileReader = new FileReader(path);
            ObjectMapper objectMapper = new ObjectMapper();
            hero1 = (Hero) objectMapper.readValue(fileReader, Hero.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hero1;
    }
    public static Minions MinionsReader(String minions) throws IOException {
        String path=String.format("resources\\Jsons\\Cards\\%s.json",minions);
        Minions minion = null;
        FileReader fileReader=new FileReader(path);
        ObjectMapper objectMapper=new ObjectMapper();
        minion= objectMapper.readValue(fileReader, Minions.class);
        fileReader.close();
        return minion;
    }
    public static Spell SpellReader(String spell) throws  IOException{
        String path=String.format("resources\\Jsons\\Cards\\%s.json",spell);
        Spell spells = null;
        FileReader fileReader=new FileReader(path);
        ObjectMapper objectMapper=new ObjectMapper();
        spells=objectMapper.readValue(fileReader,Spell.class);
        fileReader.close();
        return spells;
    }
    public static Weapon WeaponReader(String  weapon) throws  IOException{
        String path=String.format("resources\\Jsons\\Cards\\%s.json",weapon);
        Weapon weapon1 = null;
        FileReader fileReader=new FileReader(path);
        ObjectMapper objectMapper=new ObjectMapper();
        weapon1=objectMapper.readValue(fileReader,Weapon.class);
        fileReader.close();
        return weapon1;
    }
}