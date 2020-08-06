package Server.Model;

public class PlayerModel {

    private String name;
    private int exp;
    private int level;
    private long money;
    private String selectedDeckName;

    public PlayerModel(String name, int exp, int level, long money, String selectedDeckName) {
        this.name = name;
        this.exp = exp;
        this.level = level;
        this.money = money;
        this.selectedDeckName = selectedDeckName;
    }

    public PlayerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSelectedDeckName() {
        return selectedDeckName;
    }

    public void setSelectedDeckName(String selectedDeckName) {
        this.selectedDeckName = selectedDeckName;
    }
}
