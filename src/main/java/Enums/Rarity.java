package Enums;

public enum Rarity {

    Common(1),Rare(2),Epic(3),Legendary(4);

    private final int i;
    Rarity(int i ){
        this.i=i;
    }

    public int getI() {
        return i;
    }
}
