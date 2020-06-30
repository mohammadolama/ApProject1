package Controller.Actions;

import Model.Cards.*;
import Model.Heros.Hero;
import Model.Interface.Character;

import java.util.ArrayList;

public interface Visitor {
//    void visitAli(Ali ali , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitArcaniteReaper(ArcaniteReaper arcaniteReaper , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitAshbringer(Ashbringer ashbringer , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitAylar(Aylar aylar , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitBenyamin(Benyamin benyamin , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitBlessingOFTheAncient(BlessingOfTheAncients blessingOfTheAncients , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitBloodFury(BloodFury bloodFury , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitBookOfSpecters(BookOFSpecters bookOFSpecters , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitCat(Cat cat, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitCookie(Cookie cookie , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitDarkSkies(DarkSkies darkSkies, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitFaeze(Faeze faeze, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitFieryWarAxe(FieryWarAxe fieryWarAxe, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitFlamestrike(Flamestrike flamestrike, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitGearblade(Gearblade gearblade , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitHighMasterSaman(HighMasterSaman highMasterSaman, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitHolyLight(HolyLight holyLight , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitHossein(Hossein hossein , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitHosseinHima(HosseinHima hosseinHima, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitJavad(Javad javad, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitKhashayer(Khashayar khashayar, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitKingHaghi(KingHaghi kingHaghi, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitLachin(Lachin lachin, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitLearnJavadonic(LearnJavadonic learnJavadonic, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitLightforgedBlessing(LightforgedBlessing lightforgedBlessing, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitMatin(Matin matin, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitMobin(Mobin mobin, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitNima(Nima nima, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitPolymorph(Polymorph polymorph, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitQuiz(Quiz quiz, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitSandBreath(SandBreath sandBreath, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitShahryar(Shahryar shahryar, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitSilverSword(SilverSword silverSword, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitSoroush(Soroush soroush , Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitSprint(Sprint sprint, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitStrengthInNumbers(StrengthInNumbers strengthInNumbers, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitSwarmOfCats(SwarmOfCats swarmOfCats, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitTrueSilverChampion(TrueSilverChampion trueSilverChampion, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);
//    void visitYasaman(Yasaman yasaman, Character target , ArrayList<Card> myDeck , ArrayList<Card> myHand , ArrayList<Card> myPlayed , ArrayList<Card> targetDeck , ArrayList<Card> targetHand , ArrayList<Card> targetPlayed);

    void visitAli(Ali ali, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitArcaniteReaper(ArcaniteReaper arcaniteReaper, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitAshbringer(Ashbringer ashbringer, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitAylar(Aylar aylar, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitBenyamin(Benyamin benyamin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitBlessingOFTheAncient(BlessingOfTheAncients blessingOfTheAncients, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitBloodFury(BloodFury bloodFury, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitBookOfSpecters(BookOFSpecters bookOFSpecters, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitCat(Cat cat, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitCookie(Cookie cookie, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitDarkSkies(DarkSkies darkSkies, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitFaeze(Faeze faeze, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitFieryWarAxe(FieryWarAxe fieryWarAxe, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitFlamestrike(Flamestrike flamestrike, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitGearblade(Gearblade gearblade, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitHighMasterSaman(HighMasterSaman highMasterSaman, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitHolyLight(HolyLight holyLight, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitHossein(HosseinHima hosseinHima, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitHosseinHima(Hossein hossein, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitJavad(Javad javad, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitKhashayer(Khashayar khashayar, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitAghaHaghi(AghaHaghi aghaHaghi, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitLachin(Lachin lachin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitLearnJavadonic(LearnJavadonic learnJavadonic, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitLightforgedBlessing(LightforgedBlessing lightforgedBlessing, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitMatin(Matin matin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitMobin(Mobin mobin, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitNima(Nima nima, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitPolymorph(Polymorph polymorph, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitQuiz(Quiz quiz, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitSandBreath(SandBreath sandBreath, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitShahryar(Shahryar shahryar, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitSilverSword(SilverSword silverSword, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitSoroush(Soroush soroush, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitSprint(Sprint sprint, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitStrengthInNumbers(StrengthInNumbers strengthInNumbers, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitSwarmOfCats(SwarmOfCats swarmOfCats, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitTrueSilverChampion(TrueSilverChampion trueSilverChampion, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

    void visitYasaman(Yasaman yasaman, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy);

}
