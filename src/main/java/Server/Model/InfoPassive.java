package Server.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class InfoPassive {

    private String name;
    private String description;

    private InfoPassive(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public InfoPassive() {
    }

    public static ArrayList<InfoPassive> infoPassiveCreator() {
        ArrayList<InfoPassive> ar = new ArrayList<>();
        InfoPassive twiceDraw = new InfoPassive("twiceDraw", "Draw 2 cards instead of 1;");
        InfoPassive offCards = new InfoPassive("offCards", "All cards cost 1 less mana.");
        InfoPassive warrior = new InfoPassive("warriors", "Whenever a minion dies , add 2 defence to your hero");
        InfoPassive manaJump = new InfoPassive("manaJump", "You start with 2 mana");
        InfoPassive freePower = new InfoPassive("freePower", "Hero power costs 1 less mana .You can use Hero power twice.");
        ar.add(twiceDraw);
        ar.add(freePower);
        ar.add(offCards);
        ar.add(warrior);
        ar.add(manaJump);
        return ar;
    }

    public static InfoPassive sample() {
        return infoPassiveCreator().get(2);
    }

    public static InfoPassive practiceRandomChoice() {
        Random random = new Random();
        int index = random.nextInt(5);
        return infoPassiveCreator().get(index);
    }

    public static ArrayList<InfoPassive> randomGenerate(ArrayList<InfoPassive> passives) {
        ArrayList<InfoPassive> ar = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < 3; i++) {
            ar.add(passives.get(list.get(i)));
        }
        return ar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
