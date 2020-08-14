package Server;

import Server.Controller.MainLogic.DataBaseManagment;
import Server.Controller.MainLogic.HibernateCore;
import Server.Controller.MainLogic.Server;
import Server.Model.Cards.*;
import Server.Model.Heros.*;
import org.hibernate.SessionFactory;

public class ServerMain {

    public static void main(String[] args) {
        HibernateCore.connectToDataBase();
        SessionFactory sessionFactory = HibernateCore.getInstance();
//        save();
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
