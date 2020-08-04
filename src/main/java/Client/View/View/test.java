package Client.View.View;

import Client.View.Configs.ConfigsLoader;
import Server.Controller.MainLogic.HibernateCore;
import Client.View.View.Panels.Constants;
import Client.View.View.Panels.MyFrame;
import Server.Controller.MainLogic.Fundamentals;
import Client.View.View.Sounds.SoundAdmin;

import javax.sound.sampled.Clip;
import java.io.IOException;

public class test {
    static Clip clip;

    public static void main(String[] args) throws IOException {
        HibernateCore.connectToDataBase();
//        SessionFactory sessionFactory=HibernateCore.getInstance();
        ConfigsLoader configsLoader = ConfigsLoader.getInstance();

//        ObjectMapper objectMapper = new ObjectMapper();
//        File file;
//        FileWriter fileWriter = new FileWriter(new File("summonedconfig.json"));
//        Player player = new Player("as", "as");
//        objectMapper.writeValue(fileWriter, player);


//        JsonBuilders.HeroBuildeeer(new Rogue());
//        JsonBuilders.HeroBuildeeer(new Warlock());
//        JsonBuilders.HeroBuildeeer(new Mage());
//        JsonBuilders.HeroBuildeeer(new Priest());
//        JsonBuilders.HeroBuildeeer(new Hunter());
////
////
//        JsonBuilders.minionBuilder(new AghaHaghi());
//        JsonBuilders.minionBuilder(new Ali());
//        JsonBuilders.minionBuilder(new Benyamin());
//        JsonBuilders.minionBuilder(new Cat());
//        JsonBuilders.minionBuilder(new Faeze());
//        JsonBuilders.minionBuilder(new HighMasterSaman());
//        JsonBuilders.minionBuilder(new Hossein());
//        JsonBuilders.minionBuilder(new HosseinHima());
//        JsonBuilders.minionBuilder(new Javad());
//        JsonBuilders.minionBuilder(new Khashayar());
//        JsonBuilders.minionBuilder(new Lachin());
//        JsonBuilders.minionBuilder(new Matin());
//        JsonBuilders.minionBuilder(new Mobin());
//        JsonBuilders.minionBuilder(new Nima());
//        JsonBuilders.minionBuilder(new Shahryar());
//        JsonBuilders.minionBuilder(new Yasaman());
//
//        JsonBuilders.SpellBuilder(new SwarmOfCats());
//        JsonBuilders.SpellBuilder(new StrengthInNumbers());
//        JsonBuilders.SpellBuilder(new StrengthInNumbersDR());
//        JsonBuilders.SpellBuilder(new Sprint());
//        JsonBuilders.SpellBuilder(new Soroush());
//        JsonBuilders.SpellBuilder(new SandBreath());
//        JsonBuilders.SpellBuilder(new Quiz());
//        JsonBuilders.SpellBuilder(new Polymorph());
//        JsonBuilders.SpellBuilder(new LightforgedBlessing());
//        JsonBuilders.SpellBuilder(new LearnJavadonic());
//        JsonBuilders.SpellBuilder(new HolyLight());
//        JsonBuilders.SpellBuilder(new Flamestrike());
//        JsonBuilders.SpellBuilder(new DarkSkies());
//        JsonBuilders.SpellBuilder(new Cookie());
//        JsonBuilders.SpellBuilder(new BookOFSpecters());
//        JsonBuilders.SpellBuilder(new BlessingOfTheAncients());
//        JsonBuilders.SpellBuilder(new Aylar());
//
//        JsonBuilders.WeaponBuilder(new ArcaniteReaper());
//        JsonBuilders.WeaponBuilder(new Ashbringer());
//        JsonBuilders.WeaponBuilder(new BloodFury());
//        JsonBuilders.WeaponBuilder(new FieryWarAxe());
//        JsonBuilders.WeaponBuilder(new Gearblade());
//        JsonBuilders.WeaponBuilder(new SilverSword());
//        JsonBuilders.WeaponBuilder(new TrueSilverChampion());


        Fundamentals.MkDirs();
        Constants.pictureLoader();
        SoundAdmin.play1("resources/Sounds/login.wav");
        MyFrame.getInstance();


//        Lachin lachin= JsonBuilders.MinionsReader("lachin");
//
//        System.out.println(lachin.toString());


//        Player player = new Player("abf" , "dadada");
//
//
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        session.save(player);
//
//
//        session.getTransaction().commit();
//
//        session.close();


    }
}