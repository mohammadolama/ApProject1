package Client.View.View;

import Client.View.Configs.ConfigsLoader;
import Server.Controller.MainLogic.HibernateCore;
import Client.View.View.Panels.Constants;
import Client.View.View.Panels.MyFrame;
import Server.Controller.MainLogic.Fundamentals;
import Client.View.View.Sounds.SoundAdmin;
import org.hibernate.SessionFactory;

import javax.sound.sampled.Clip;
import java.io.IOException;

public class test {
    static Clip clip;

    public static void main(String[] args) throws IOException {
//        HibernateCore.connectToDataBase();
//        SessionFactory sessionFactory=HibernateCore.getInstance();
//        ConfigsLoader configsLoader = ConfigsLoader.getInstance();

//        ObjectMapper objectMapper = new ObjectMapper();
//        File file;
//        FileWriter fileWriter = new FileWriter(new File("summonedconfig.json"));
//        Player player = new Player("as", "as");
//        objectMapper.writeValue(fileWriter, player);

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
