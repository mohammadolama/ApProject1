package Server.Controller.MainLogic;


import Server.Model.Cards.*;
import Server.Model.Heros.*;
import Server.Model.Player;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private ArrayList<Player> players;
    private ArrayList waitingPlayers;
    private int port;
    private LogInSignUp logInSignUp = new LogInSignUp();

    public Server(int port) {
        try {
            this.port = port;
            players = new ArrayList<>();
            waitingPlayers = new ArrayList<>();
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println("server started at port : " + port);
//        new myThread().start();
        while (!isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        HibernateCore.connectToDataBase();
        SessionFactory sessionFactory = HibernateCore.getInstance();


//        Hero hero = DataBaseManagment.HeroJsonReader(null , "mage");
//
//        System.out.println(hero.toString());
//
//            save();

        new Server(8000).start();
    }


    public static void save() {

        DataBaseManagment.HeroBuildeeer(new Rogue());
        DataBaseManagment.HeroBuildeeer(new Warlock());
        DataBaseManagment.HeroBuildeeer(new Mage());
        DataBaseManagment.HeroBuildeeer(new Priest());
        DataBaseManagment.HeroBuildeeer(new Hunter());
//
//
        DataBaseManagment.minionBuilder(new AghaHaghi());
        DataBaseManagment.minionBuilder(new Ali());
        DataBaseManagment.minionBuilder(new Benyamin());
        DataBaseManagment.minionBuilder(new Cat());
        DataBaseManagment.minionBuilder(new Faeze());
        DataBaseManagment.minionBuilder(new HighMasterSaman());
        DataBaseManagment.minionBuilder(new Hossein());
        DataBaseManagment.minionBuilder(new HosseinHima());
        DataBaseManagment.minionBuilder(new Javad());
        DataBaseManagment.minionBuilder(new Khashayar());
        DataBaseManagment.minionBuilder(new Lachin());
        DataBaseManagment.minionBuilder(new Matin());
        DataBaseManagment.minionBuilder(new Mobin());
        DataBaseManagment.minionBuilder(new Nima());
        DataBaseManagment.minionBuilder(new Shahryar());
        DataBaseManagment.minionBuilder(new Yasaman());

        DataBaseManagment.SpellBuilder(new SwarmOfCats());
        DataBaseManagment.SpellBuilder(new StrengthInNumbers());
        DataBaseManagment.SpellBuilder(new StrengthInNumbersDR());
        DataBaseManagment.SpellBuilder(new Sprint());
        DataBaseManagment.SpellBuilder(new Soroush());
        DataBaseManagment.SpellBuilder(new SandBreath());
        DataBaseManagment.SpellBuilder(new Quiz());
        DataBaseManagment.SpellBuilder(new Polymorph());
        DataBaseManagment.SpellBuilder(new LightforgedBlessing());
        DataBaseManagment.SpellBuilder(new LearnJavadonic());
        DataBaseManagment.SpellBuilder(new HolyLight());
        DataBaseManagment.SpellBuilder(new Flamestrike());
        DataBaseManagment.SpellBuilder(new DarkSkies());
        DataBaseManagment.SpellBuilder(new Cookie());
        DataBaseManagment.SpellBuilder(new BookOFSpecters());
        DataBaseManagment.SpellBuilder(new BlessingOfTheAncients());
        DataBaseManagment.SpellBuilder(new Aylar());

        DataBaseManagment.WeaponBuilder(new ArcaniteReaper());
        DataBaseManagment.WeaponBuilder(new Ashbringer());
        DataBaseManagment.WeaponBuilder(new BloodFury());
        DataBaseManagment.WeaponBuilder(new FieryWarAxe());
        DataBaseManagment.WeaponBuilder(new Gearblade());
        DataBaseManagment.WeaponBuilder(new SilverSword());
        DataBaseManagment.WeaponBuilder(new TrueSilverChampion());

    }
}

