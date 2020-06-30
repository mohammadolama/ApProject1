package Model.Cards;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Aylar.class, name = "aylar"),
        @JsonSubTypes.Type(value = BlessingOfTheAncients.class, name = "blessingoftheancients"),
        @JsonSubTypes.Type(value = BookOFSpecters.class, name = "bookofspecters"),
        @JsonSubTypes.Type(value = Cookie.class, name = "cookie"),
        @JsonSubTypes.Type(value = DarkSkies.class, name = "darkskies"),
        @JsonSubTypes.Type(value = Flamestrike.class, name = "flamestrike"),
        @JsonSubTypes.Type(value = HolyLight.class, name = "holylight"),
        @JsonSubTypes.Type(value = LearnJavadonic.class, name = "learnjavadonic"),
        @JsonSubTypes.Type(value = LightforgedBlessing.class, name = "lightforgedblessing"),
        @JsonSubTypes.Type(value = Polymorph.class, name = "polymorph"),
        @JsonSubTypes.Type(value = Quiz.class, name = "quiz"),
        @JsonSubTypes.Type(value = SandBreath.class, name = "sandbreath"),
        @JsonSubTypes.Type(value = Soroush.class, name = "soroush"),
        @JsonSubTypes.Type(value = Sprint.class, name = "sprint"),
        @JsonSubTypes.Type(value = StrengthInNumbers.class, name = "strengthinnumbers"),
        @JsonSubTypes.Type(value = SwarmOfCats.class, name = "swarmofcats"),
})
public class Spell extends Card {

    int usageTimes;
    int manaSpendOnSth;

    public int getUsageTimes() {
        return usageTimes;
    }

    public void setUsageTimes(int usageTimes) {
        this.usageTimes = usageTimes;
    }

    public int getManaSpendOnSth() {
        return manaSpendOnSth;
    }

    public void setManaSpendOnSth(int manaSpendOnSth) {
        this.manaSpendOnSth = manaSpendOnSth;
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
