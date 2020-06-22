package Model.Cards;

public class HeroPower {
    private String name;
    private int manaCost;

    public HeroPower() {
    }

    public HeroPower(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}
