package Heros;

import Main.Player;
import Enums.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.io.IOException;
import java.util.ArrayList;

@JsonIgnoreProperties({"purchasedCards", "CardsInDeck"})
public class Rouge extends Hero {
    public Rouge(Player player) throws IOException {
        super(player);
        this.setName("Rouge");
        this.setCanAttack(false);
        this.setATT(0);
        this.setHP(30);
        this.setHeroPower("Steal a card from opponent's hand and add it to your hand .Costs 3 mana" +
                "\n Upgrade : if you have a weapon , You Steal a card from opponent's hand and a card from opponent's deck randomly.");
        this.setHeroPowerManaCost(3);
        this.setSpecialPower("A card which is not in your class or not neutral, costs 2 less mana .");
        this.setSPcarts(Spcards());

    }

    private ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.friendlysmith);
        ar.add(Carts.umbralskulker);
        return ar;
    }

}
