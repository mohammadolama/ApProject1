package Server.Controller.MainLogic;

import Server.Model.Account;
import Server.Model.Cards.*;
import Server.Model.Heros.Hero;
import Server.Model.Player;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DataBaseManagment {


    static ObjectMapper objectMapper = new ObjectMapper();
    static HashMap<String, Class> classes;
    private static SessionFactory sessionFactory = HibernateCore.getInstance();


    static {
        classes = new HashMap<>();
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
        DataBaseManagment.objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    }


    public static void PlayerJsonBuilder(String username, Player player) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.saveOrUpdate(player);

        session.getTransaction().commit();

        session.close();

    }

    static void NewPlayerHeroBuilder(Player player) {
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
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(hero);


        session.getTransaction().commit();

        session.close();

    }

    public static void WeaponBuilder(Weapon weapon) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(weapon);


        session.getTransaction().commit();

        session.close();
    }

    public static void SpellBuilder(Spell spell) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(spell);


        session.getTransaction().commit();

        session.close();
    }

    public static void minionBuilder(Minion minions) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(minions);


        session.getTransaction().commit();

        session.close();

    }

    static Player PlayerJsonReader(String username) {
        Session session = sessionFactory.openSession();
        Player player = session.get(Player.class, username);
        session.close();
        return player;
    }

    public static Hero HeroJsonReader(Player player, String hero) {
        Session session = sessionFactory.openSession();
        Hero hero1 = session.get(Hero.class, hero);
        session.close();
        return hero1;

    }

    public static <T> T MinionsReader(String minions) {
        Session session = sessionFactory.openSession();
        T t = (T) session.get(classes.get(minions), minions);
        session.close();
        return t;
    }

    public static <T> T SpellReader(String spell) {
        Session session = sessionFactory.openSession();
        T t = (T) session.get(classes.get(spell), spell);
        session.close();
        return t;
    }

    public static <T> T WeaponReader(String weapon) {
        Session session = sessionFactory.openSession();
        T t = (T) session.get(classes.get(weapon), weapon);
        session.close();
        return t;
    }

    public static Account accountReader(String username) {
        Session session = sessionFactory.openSession();
        Account account = session.get(Account.class, username);
        session.close();
        return account;
    }


    public static void saveAccount(Account account) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        session.close();
    }


    public static void deleteAccount(String user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Account account = session.get(Account.class, user);
        System.out.println(account.toString());
        session.delete(account);
        session.getTransaction().commit();
        session.close();
    }
}
