package Controller.Actions;

import Controller.Admin;
import Controller.ThreadColor;
import Main.JsonReaders;
import Model.Cards.*;
import Model.Enums.Attribute;
import Model.Enums.WeaponCarts;
import Model.Heros.Hero;
import Model.Interface.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ActionVisitor implements Visitor {

    @Override
    public void visitAli(Ali ali, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitArcaniteReaper(ArcaniteReaper arcaniteReaper, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitAshbringer(Ashbringer ashbringer, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitAylar(Aylar aylar, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
//        ArrayList<WeaponCarts> list = new ArrayList<>(Arrays.asList(WeaponCarts.values()));
//        Collections.shuffle(list);
//        Collections.shuffle(list);
//        Weapon weapon = JsonReaders.WeaponReader(list.get(0).toString().toLowerCase());
//        weapon.setDamage(weapon.getDamage() + aylar.getAttackRestore());
//        weapon.setDurability(weapon.getDurability() + aylar.getHealthRestore());
//        myDeck.add(weapon);
        System.out.println("Start action of aylar");
        ArrayList<WeaponCarts> list = new ArrayList<>(Arrays.asList(WeaponCarts.values()));
        Collections.shuffle(list);
        Admin.getInstance().discoverMode(list.get(0).name(), list.get(1).name(), list.get(2).name());
    }

    @Override
    public void visitBenyamin(Benyamin benyamin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        for (Card card : myPlayed) {
            ((Minion) card).setHealth(((Minion) card).getHealth() + benyamin.getHealthRestore());
        }
        for (Card card : targetPlayed) {
            ((Minion) card).setHealth(((Minion) card).getHealth() + benyamin.getHealthRestore());
        }
    }

    @Override
    public void visitBlessingOFTheAncient(BlessingOfTheAncients blessingOfTheAncients, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        for (Card card : myPlayed) {
            ((Minion) card).setHealth(((Minion) card).getHealth() + blessingOfTheAncients.getHealthRestore());
            ((Minion) card).setDamage(((Minion) card).getDamage() + blessingOfTheAncients.getAttackRestore());
        }
    }

    @Override
    public void visitBloodFury(BloodFury bloodFury, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitBookOfSpecters(BookOFSpecters bookOFSpecters, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        drawCard(3, myDeck, myHand);
    }

    @Override
    public void visitCat(Cat cat, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitCookie(Cookie cookie, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        for (Card card : myPlayed) {
            ((Minion) card).setHealth(((Minion) card).getHealth() + cookie.getHealthRestore());
            if (((Minion) card).getHealth() > ((Minion) card).getMaxHealth()) {
                ((Minion) card).setHealth(((Minion) card).getMaxHealth());
            }
        }
        friendly.setHealth(friendly.getHealth() + cookie.getHealthRestore());
        if (friendly.getHealth() > friendly.getMaxHealth()) {
            friendly.setHealth(friendly.getMaxHealth());
        }
    }

    @Override
    public void visitDarkSkies(DarkSkies darkSkies, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        Random random = new Random();
        int i = random.nextInt(targetPlayed.size());
        ((Minion) targetPlayed.get(i)).setHealth(((Minion) targetPlayed.get(i)).getHealth() + darkSkies.getHealthRestore());
    }

    @Override
    public void visitFaeze(Faeze faeze, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitFieryWarAxe(FieryWarAxe fieryWarAxe, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitFlamestrike(Flamestrike flamestrike, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        for (Card card : targetPlayed) {
            ((Minion) card).setHealth(((Minion) card).getHealth() + flamestrike.getHealthRestore());
        }
    }

    @Override
    public void visitGearblade(Gearblade gearblade, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitHighMasterSaman(HighMasterSaman highMasterSaman, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitHolyLight(HolyLight holyLight, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        ((Minion) target).setHealth(((Minion) target).getHealth() + holyLight.getHealthRestore());
        if (((Minion) target).getHealth() > ((Minion) target).getMaxHealth()) {
            ((Minion) target).setHealth(((Minion) target).getMaxHealth());
        }
    }

    @Override
    public void visitHossein(HosseinHima hosseinHima, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitHosseinHima(Hossein hossein, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitJavad(Javad javad, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitKhashayer(Khashayar khashayar, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitAghaHaghi(AghaHaghi aghaHaghi, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitLachin(Lachin lachin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitLearnJavadonic(LearnJavadonic learnJavadonic, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        if (learnJavadonic.getManaSpendOnSth() >= 8) {
            if (myPlayed.size() < 7) {
                Javad javad = (Javad) JsonReaders.MinionsReader("javad");
                myPlayed.add(javad);
            }
        }
    }

    @Override
    public void visitLightforgedBlessing(LightforgedBlessing lightforgedBlessing, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        for (Card card : myPlayed) {
            ((Minion) card).setHealth(((Minion) card).getHealth() + 2);
        }

    }

    @Override
    public void visitMatin(Matin matin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        matin.setHealth(matin.getHealth() + matin.getHealthRestore());
        matin.setDamage(matin.getDamage() + matin.getAttackRestore());
    }

    @Override
    public void visitMobin(Mobin mobin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitNima(Nima nima, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitPolymorph(Polymorph polymorph, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        target.setLife(1);
        target.setAttack(1);

    }

    @Override
    public void visitQuiz(Quiz quiz, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        Random random = new Random();
        if (targetPlayed.size() != 0) {
            int i = random.nextInt(targetPlayed.size());
            ((Minion) targetPlayed.get(i)).setHealth(((Minion) targetPlayed.get(i)).getHealth() + quiz.getHealthRestore());
        }

    }

    @Override
    public void visitSandBreath(SandBreath sandBreath, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        ((Minion) target).setHealth(((Minion) target).getHealth() + sandBreath.getHealthRestore());
        ((Minion) target).setDamage(((Minion) target).getDamage() + sandBreath.getAttackRestore());
        if (((Minion) target).getAttributes() != null && !((Minion) target).getAttributes().contains(Attribute.DivineShield)) {
            ((Minion) target).getAttributes().add(Attribute.DivineShield);
        }
    }

    @Override
    public void visitShahryar(Shahryar shahryar, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        shahryar.setHealth(target.getLife());
    }

    @Override
    public void visitSilverSword(SilverSword silverSword, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitSoroush(Soroush soroush, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        System.out.println(ThreadColor.ANSI_BLUE + target + ThreadColor.ANSI_RESET);
        ((Minion) target).setHealth(((Minion) target).getHealth() + soroush.getHealthRestore());
        ((Minion) target).setDamage(((Minion) target).getDamage() + soroush.getAttackRestore());
        if (((Minion) target).getAttributes() != null && !((Minion) target).getAttributes().contains(Attribute.DivineShield)) {
            ((Minion) target).getAttributes().add(Attribute.DivineShield);
        }
        if (((Minion) target).getAttributes() != null && !((Minion) target).getAttributes().contains(Attribute.Taunt)) {
            ((Minion) target).getAttributes().add(Attribute.Taunt);
        }
    }

    @Override
    public void visitSprint(Sprint sprint, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        drawCard(4, myDeck, myHand);
    }

    @Override
    public void visitStrengthInNumbers(StrengthInNumbers strengthInNumbers, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        if (strengthInNumbers.getManaSpendOnSth() >= 10) {
            if (myPlayed.size() < 7) {
                for (Card card : myDeck) {
                    if (card instanceof Minion) {
                        myPlayed.add(card);
                        myDeck.remove(card);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void visitSwarmOfCats(SwarmOfCats swarmOfCats, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        while (myPlayed.size() < 7) {
            Cat cat = (Cat) JsonReaders.MinionsReader("cat");
            myPlayed.add(cat);
        }

    }

    @Override
    public void visitTrueSilverChampion(TrueSilverChampion trueSilverChampion, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }

    @Override
    public void visitYasaman(Yasaman yasaman, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

    }


    private void drawCard(int i, ArrayList<Card> deck, ArrayList<Card> hand) {
        if (deck.size() < i) {
            i = deck.size();
        }
        for (int j = 0; j < i; j++) {
            Card cards = deck.get(0);
            if (hand.size() < 12) {
                hand.add(cards);
            }
            deck.remove(cards);
        }
    }

}
