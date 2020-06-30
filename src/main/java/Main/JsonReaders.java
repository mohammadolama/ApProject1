package Main;

import Model.Cards.*;
import Model.Heros.Hero;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.HashMap;

public class JsonReaders {
    private static HashMap<String, Class> classes;
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        classes = new HashMap<>();
        classes.put("aghahaghi", AghaHaghi.class);
        classes.put("ali", Ali.class);
        classes.put("arcanitereaper", ArcaniteReaper.class);
        classes.put("ashbringer", Ashbringer.class);
        classes.put("aylar", Aylar.class);
        classes.put("benyamin", Benyamin.class);
        classes.put("blessingoftheancients", BlessingOfTheAncients.class);
        classes.put("bloodfury", BloodFury.class);
        classes.put("bookofspecters", BookOFSpecters.class);
        classes.put("cat", Cat.class);
        classes.put("cookie", Cookie.class);
        classes.put("darkskies", DarkSkies.class);
        classes.put("faeze", Faeze.class);
        classes.put("fierywaraxe", FieryWarAxe.class);
        classes.put("flamestrike", Flamestrike.class);
        classes.put("gearblade", Gearblade.class);
        classes.put("highmastersaman", HighMasterSaman.class);
        classes.put("shahryar", Shahryar.class);
        classes.put("holylight", HolyLight.class);
        classes.put("hossein", Hossein.class);
        classes.put("hosseinhima", HosseinHima.class);
        classes.put("javad", Javad.class);
        classes.put("khashayar", Khashayar.class);
        classes.put("lachin", Lachin.class);
        classes.put("learnjavadonic", LearnJavadonic.class);
        classes.put("lightforgedblessing", LightforgedBlessing.class);
        classes.put("matin", Matin.class);
        classes.put("mobin", Mobin.class);
        classes.put("nima", Nima.class);
        classes.put("polymorph", Polymorph.class);
        classes.put("quiz", Quiz.class);
        classes.put("sandbreath", SandBreath.class);
        classes.put("silversword", SilverSword.class);
        classes.put("soroush", Soroush.class);
        classes.put("sprint", Sprint.class);
        classes.put("strengthinnumbers", StrengthInNumbers.class);
        classes.put("swarmofcats", SwarmOfCats.class);
        classes.put("truesilverchampion", TrueSilverChampion.class);
        classes.put("yasaman", Yasaman.class);
    }



    static Player PlayerJsonReader(String username) {
        String path = String.format("resources/Jsons/Players/%s/player.json", username);
        Player player = null;
        try {
            FileReader fileReader = new FileReader(path);
            player = objectMapper.readValue(fileReader, Player.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

    static Hero NewPlayerHeroReader(String hero) {
        String path = String.format("resources/Jsons/Heros/%s.json", hero.toLowerCase());
        Hero hero1 = null;
        try {
            FileReader fileReader = new FileReader(path);
            hero1 = objectMapper.readValue(fileReader, Hero.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hero1;
    }

    public static Hero HeroJsonReader(Player player, String hero) {
        String path = String.format("resources/Jsons/Players/%s/%s.json", player.getUsername(), hero.toLowerCase());
        Hero hero1 = null;
        try {
            FileReader fileReader = new FileReader(path);
            hero1 = objectMapper.readValue(fileReader, Hero.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hero1;
    }

    public static Minion MinionsReader(String minions) {
        String path = String.format("resources/Jsons/Cards/%s.json", minions);
        Minion minion = null;
        try {
            FileReader fileReader = new FileReader(path);
            Class clas = classes.get(minions.toLowerCase());
            System.out.println(minions);
            minion = (Minion) objectMapper.readValue(fileReader, clas);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return minion;
    }

    public static Spell SpellReader(String spell) {
        String path = String.format("resources/Jsons/Cards/%s.json", spell);
        Spell spells = null;
        try {
            FileReader fileReader = new FileReader(path);
            Class clas = classes.get(spell.toLowerCase());
            spells = (Spell) objectMapper.readValue(fileReader, clas);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spells;
    }

    public static Weapon WeaponReader(String weapon) {
        String path = String.format("resources/Jsons/Cards/%s.json", weapon);
        Weapon weapon1 = null;
        try {
            FileReader fileReader = new FileReader(path);
            Class clas = classes.get(weapon.toLowerCase());
            System.out.println(weapon);
            weapon1 = (Weapon) objectMapper.readValue(fileReader, clas);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weapon1;
    }
}
