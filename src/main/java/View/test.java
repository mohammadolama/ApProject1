package View;

import Main.JsonBuilders;
import Model.Cards.LearnJavadonic;
import Model.Cards.StrengthInNumbers;
import Model.Heros.*;
import View.Configs.ConfigsLoader;
import View.Panels.Constants;
import View.Panels.MyFrame;
import Main.Fundamentals;
import View.Sounds.SoundAdmin;

import javax.sound.sampled.Clip;
import java.util.Random;

public class test {
    static Clip clip;

    public static void main(String[] args) {
        ConfigsLoader configsLoader=ConfigsLoader.getInstance();
        Fundamentals.MkDirs();
        Constants.pictureLoader();
        SoundAdmin.play1("resources\\Sounds\\login.wav");
        MyFrame.getInstance();


//        JsonBuilders.HeroBuildeeer(new Rogue());
//        JsonBuilders.HeroBuildeeer(new Warlock());
//        JsonBuilders.HeroBuildeeer(new Mage());
//        JsonBuilders.HeroBuildeeer(new Priest());
//        JsonBuilders.HeroBuildeeer(new Hunter());
//
//
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
//        String path = "resources/pics/cards/";
//        for (Carts value : Carts.values()) {
//            System.out.println(String.format("cardPics.put(\"%s\", %s);" , value,value));
//        }

    }
}
