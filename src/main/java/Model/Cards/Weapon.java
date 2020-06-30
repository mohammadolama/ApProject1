package Model.Cards;


import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ArcaniteReaper.class, name = "arcanitereaper"),
        @JsonSubTypes.Type(value = Ashbringer.class, name = "ashbringer"),
        @JsonSubTypes.Type(value = BloodFury.class, name = "bloodfury"),
        @JsonSubTypes.Type(value = FieryWarAxe.class, name = "fierywaraxe"),
        @JsonSubTypes.Type(value = Gearblade.class, name = "gearblade"),
        @JsonSubTypes.Type(value = SilverSword.class, name = "silversword"),
        @JsonSubTypes.Type(value = HosseinHima.class, name = "hossein"),
        @JsonSubTypes.Type(value = TrueSilverChampion.class, name = "truesilverchampion"),
        @JsonSubTypes.Type(value = Javad.class, name = "javad"),
        @JsonSubTypes.Type(value = Khashayar.class, name = "khashayar"),
        @JsonSubTypes.Type(value = Lachin.class, name = "lachin"),
        @JsonSubTypes.Type(value = Matin.class, name = "matin"),
        @JsonSubTypes.Type(value = Mobin.class, name = "mobin"),
        @JsonSubTypes.Type(value = Nima.class, name = "nima"),
        @JsonSubTypes.Type(value = Shahryar.class, name = "shahryar"),
        @JsonSubTypes.Type(value = Yasaman.class, name = "yasaman"),
})
public class Weapon extends Card {
    private int damage;
    private int durability;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public int getAttack() {
        return super.getAttack();
    }

    @Override
    public void setAttack(int i) {
        super.setAttack(i);
    }

    @Override
    public int getLife() {
        return super.getLife();
    }

    @Override
    public void setLife(int i) {
        super.setLife(i);
    }
}
