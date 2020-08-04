package Server.Controller.MainLogic;

import Server.Model.Cards.*;
import Server.Model.Heros.*;
import Server.Model.Player;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.*;
import java.util.HashMap;

public class JsonBuilders {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static HashMap<String, Class> classes;

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
        classes.put("strengthinnumbersdr", StrengthInNumbersDR.class);
        classes.put("swarmofcats", SwarmOfCats.class);
        classes.put("truesilverchampion", TrueSilverChampion.class);
        classes.put("yasaman", Yasaman.class);
    }


    static {
        objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    }

    private static SessionFactory sessionFactory = HibernateCore.getInstance();

    public static void PlayerJsonBuilder(String username, Player player) {
//        try {
//            String path = String.format("resources/Jsons/Players/%s", username);
//            path = path + "/player.json";
//            File file = new File(path);
//            file.getParentFile().mkdir();
//            file.createNewFile();
//            FileWriter fileWriter = new FileWriter(path);
//            objectMapper.writeValue(fileWriter, player);
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.saveOrUpdate(player);

        session.getTransaction().commit();

        session.close();

    }

    static void NewPlayerHeroBuilder(Player player) {
//        String path = String.format("resources/Jsons/Players/%s", player.getUsername());
//        try {
//            String st = path + "/mage.json";
//            FileWriter fileWriter = new FileWriter(st);
//            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("mage"));
//            fileWriter.close();
//            st = path + "/rogue.json";
//            fileWriter = new FileWriter(st);
//            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("rogue"));
//            fileWriter.close();
//            st = path + "/warlock.json";
//            fileWriter = new FileWriter(st);
//            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("warlock"));
//            fileWriter.close();
//            st = path + "/priest.json";
//            fileWriter = new FileWriter(st);
//            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("priest"));
//            fileWriter.close();
//            st = path + "/hunter.json";
//            fileWriter = new FileWriter(st);
//            objectMapper.writeValue(fileWriter, JsonReaders.NewPlayerHeroReader("hunter"));
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        PlayerJsonBuilder(null, player);
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
//        String st = String.format("resources/Jsons/%s.json", hero.getName().toLowerCase());
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter(st);
//            objectMapper.writeValue(fileWriter, hero);
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(hero);


        session.getTransaction().commit();

        session.close();

    }


    public static void WeaponBuilder(Weapon weapon) {
//        String st = String.format("resources/Jsons/Cards/%s.json", weapon.getName().toLowerCase());
//        try {
//            FileWriter fileWriter = new FileWriter(st);
//            objectMapper.writeValue(fileWriter, weapon);
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(weapon);


        session.getTransaction().commit();

        session.close();
    }

    public static void SpellBuilder(Spell spell) {
//        String st = String.format("resources/Jsons/Cards/%s.json", spell.getName().toLowerCase());
//        try {
//            FileWriter fileWriter = new FileWriter(st);
//            objectMapper.writeValue(fileWriter, spell);
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(spell);


        session.getTransaction().commit();

        session.close();
    }

    public static void minionBuilder(Minion minions) {
//        String st = String.format("resources/Jsons/Cards/%s.json", minions.getName().toLowerCase());
//        try {
//            FileWriter fileWriter = new FileWriter(st);
//            objectMapper.writeValue(fileWriter, minions);
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(minions);


        session.getTransaction().commit();

        session.close();

    }


    public static <T> T MinionsReader(String minions) {
        Session session = sessionFactory.openSession();
        T t = (T) session.get(classes.get(minions), minions);
        session.close();
        return t;
    }


}
