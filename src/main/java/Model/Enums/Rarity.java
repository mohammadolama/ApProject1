package Model.Enums;

public enum Rarity {

    Common(1),Rare(2),Epic(3),Legendary(4);

    private int i;
    Rarity(int i ){
        this.i=i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
