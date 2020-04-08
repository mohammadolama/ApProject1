package AllCards;

public class Weapon extends Cards {
    private int Att;
    private int durability;

    public int getAtt() {
        return Att;
    }

    public void setAtt(int att) {
        Att = att;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
