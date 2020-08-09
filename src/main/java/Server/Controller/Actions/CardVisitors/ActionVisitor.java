package Server.Controller.Actions.CardVisitors;

import Server.Controller.Actions.ActionHandler;
import Server.Controller.MainLogic.*;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.*;
import Server.Model.Enums.*;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import java.util.*;

public class ActionVisitor implements Visitor {

    @Override
    public void visitAli(Ali ali, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitArcaniteReaper(ArcaniteReaper arcaniteReaper, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitAshbringer(Ashbringer ashbringer, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitAylar(Aylar aylar, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        ArrayList<WeaponCarts> list = new ArrayList<>(Arrays.asList(WeaponCarts.values()));
        Collections.shuffle(list);
        Admin.getInstance().discoverMode(list.get(0).name(), list.get(1).name(), list.get(2).name());
    }

    @Override
    public void visitBenyamin(Benyamin benyamin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        if (target != null) {
            for (Card card : myPlayed) {
                if (!card.equals(benyamin))
                    ((Minion) card).setHealth(((Minion) card).getHealth() + benyamin.getHealthRestore());
            }
            for (Card card : targetPlayed) {
                if (!card.equals(benyamin))
                    ((Minion) card).setHealth(((Minion) card).getHealth() + benyamin.getHealthRestore());
            }
        }
    }

    @Override
    public void visitBlessingOFTheAncient(BlessingOfTheAncients blessingOfTheAncients, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        for (Card card : myPlayed) {
            ((Minion) card).setHealth(((Minion) card).getHealth() + blessingOfTheAncients.getHealthRestore());
            ((Minion) card).setDamage(((Minion) card).getDamage() + blessingOfTheAncients.getAttackRestore());
        }
    }

    @Override
    public void visitBloodFury(BloodFury bloodFury, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitBookOfSpecters(BookOFSpecters bookOFSpecters, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        Admin.getInstance().drawCard(3, "extra", myDeck, myHand);
    }

    @Override
    public void visitCat(Cat cat, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitCookie(Cookie cookie, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        for (Card card : myPlayed) {
            ActionHandler actionHandler = new ActionHandler();
            actionHandler.restoreHealth(card, cookie.getHealthRestore());
        }
        ActionHandler actionHandler = new ActionHandler();
        actionHandler.restoreHealth(friendly, cookie.getHealthRestore());
    }

    @Override
    public void visitDarkSkies(DarkSkies darkSkies, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        if (targetPlayed.size() > 0) {
            Random random = new Random();
            int i = random.nextInt(targetPlayed.size());
            ((Minion) targetPlayed.get(i)).setHealth(((Minion) targetPlayed.get(i)).getHealth() + darkSkies.getHealthRestore());
            Admin.getInstance().summonedMinion(targetPlayed.get(i), 1, targetPlayed.get(i).getAttack(), targetPlayed.get(i).getLife());
        }
    }

    @Override
    public void visitFaeze(Faeze faeze, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitFieryWarAxe(FieryWarAxe fieryWarAxe, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitFlamestrike(Flamestrike flamestrike, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        for (Card card : targetPlayed) {
            ((Minion) card).setHealth(((Minion) card).getHealth() + flamestrike.getHealthRestore());
        }
    }

    @Override
    public void visitGearblade(Gearblade gearblade, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitHighMasterSaman(HighMasterSaman highMasterSaman, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitHolyLight(HolyLight holyLight, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        ActionHandler actionHandler = new ActionHandler();
        actionHandler.restoreHealth(target, holyLight.getHealthRestore());
    }

    @Override
    public void visitHossein(HosseinHima hosseinHima, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitHosseinHima(Hossein hossein, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitJavad(Javad javad, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitKhashayer(Khashayar khashayar, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitAghaHaghi(AghaHaghi aghaHaghi, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitLachin(Lachin lachin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitLearnJavadonic(LearnJavadonic learnJavadonic, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        if (learnJavadonic.getManaSpendOnSth() >= learnJavadonic.getMaxManaSpendOnSth()) {
            Javad javad = DataBaseManagment.MinionsReader("javad");
            Admin.getInstance().summonMinion(javad, -1);
            Admin.getInstance().summonedMinion(javad, 0, 6, 6);
            managers.finishAction(learnJavadonic, friendly);
        }
    }

    @Override
    public void visitLightforgedBlessing(LightforgedBlessing lightforgedBlessing, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        for (Card card : myPlayed) {
            ((Minion) card).setHealth(((Minion) card).getHealth() + 2);
        }

    }

    @Override
    public void visitMatin(Matin matin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        matin.setHealth(matin.getHealth() + matin.getHealthRestore());
        matin.setDamage(matin.getDamage() + matin.getAttackRestore());
        Admin.getInstance().summonedMinion(matin, 1, matin.getDamage(), matin.getHealth());
    }

    @Override
    public void visitMobin(Mobin mobin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitNima(Nima nima, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitPolymorph(Polymorph polymorph, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        target.setLife(1);
        target.setAttack(1);
        if (((Minion) target).getAttributes() != null) {
            ((Minion) target).getAttributes().remove(Attribute.DivineShield);
            ((Minion) target).getAttributes().remove(Attribute.Taunt);
        }
        Admin.getInstance().summonedMinion((Minion) target, 1, 1, 1);

    }

    @Override
    public void visitQuiz(Quiz quiz, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        Random random = new Random();
        if (targetPlayed.size() != 0) {
            int i = random.nextInt(targetPlayed.size());
            ((Minion) targetPlayed.get(i)).setHealth(((Minion) targetPlayed.get(i)).getHealth() + quiz.getHealthRestore());
            Admin.getInstance().summonedMinion(targetPlayed.get(i), 1, 0, 0);
        }
    }

    @Override
    public void visitSandBreath(SandBreath sandBreath, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        ((Minion) target).setHealth(((Minion) target).getHealth() + sandBreath.getHealthRestore());
        ((Minion) target).setDamage(((Minion) target).getDamage() + sandBreath.getAttackRestore());
        Admin.getInstance().summonedMinion((Minion) target, 1, target.getAttack(), target.getLife());
        if (((Minion) target).getAttributes() != null && !((Minion) target).getAttributes().contains(Attribute.DivineShield)) {
            ((Minion) target).getAttributes().add(Attribute.DivineShield);
        }
    }

    @Override
    public void visitShahryar(Shahryar shahryar, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        if (target != null) {
            target.setLife(shahryar.getLife());
        }
    }

    @Override
    public void visitSilverSword(SilverSword silverSword, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitSoroush(Soroush soroush, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        ((Minion) target).setHealth(((Minion) target).getHealth() + soroush.getHealthRestore());
        ((Minion) target).setDamage(((Minion) target).getDamage() + soroush.getAttackRestore());
        Admin.getInstance().summonedMinion((Minion) target, 1, target.getAttack(), target.getLife());
        if (((Minion) target).getAttributes() != null && !((Minion) target).getAttributes().contains(Attribute.DivineShield)) {
            ((Minion) target).getAttributes().add(Attribute.DivineShield);
        }
        if (((Minion) target).getAttributes() != null && !((Minion) target).getAttributes().contains(Attribute.Taunt)) {
            ((Minion) target).getAttributes().add(Attribute.Taunt);
        }
    }

    @Override
    public void visitSprint(Sprint sprint, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        Admin.getInstance().drawCard(4, null, myDeck, myHand);
    }

    @Override
    public void visitStrengthInNumbers(StrengthInNumbers strengthInNumbers, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        if (strengthInNumbers.getManaSpendOnSth() >= 10) {
            for (Card card : myDeck) {
                if (card instanceof Minion) {
                    Admin.getInstance().summonMinion((Minion) card, -1);
                    Admin.getInstance().summonedMinion(card, 0, ((Minion) card).getDamage(), ((Minion) card).getDamage());
                    break;
                }
            }
            managers.finishAction(strengthInNumbers, friendly);
        }
    }

    @Override
    public void visitStrengthInNumbersDR(StrengthInNumbersDR strengthInNumbersDR, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        if (strengthInNumbersDR.getManaSpendOnSth() >= 10) {
            for (Card card : myDeck) {
                if (card.getName().equalsIgnoreCase("lachin")) {
                    Admin.getInstance().summonMinion((Minion) card, -1);
                    Admin.getInstance().summonedMinion(card, 0, ((Minion) card).getDamage(), ((Minion) card).getHealth());
                    managers.finishAction(strengthInNumbersDR, friendly);
                    myDeck.remove(card);
                    break;
                }
            }
        }
    }

    @Override
    public void visitSwarmOfCats(SwarmOfCats swarmOfCats, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        while (myPlayed.size() < 7) {
            Cat cat = DataBaseManagment.MinionsReader("cat");
            Admin.getInstance().summonMinion(cat, -1);
        }
    }

    @Override
    public void visitTrueSilverChampion(TrueSilverChampion trueSilverChampion, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }

    @Override
    public void visitYasaman(Yasaman yasaman, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {

    }


}
