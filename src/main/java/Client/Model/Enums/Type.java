package Client.Model.Enums;

public enum Type {

    Minion(3), Spell(2), Weapon(1);

    private final int i;

    Type(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
