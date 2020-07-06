package Model;

public class ActionModel {

    private String name;
    private int manaSpendOnSth;
    private int maxSpend;

    public ActionModel(String name, int manaSpendOnSth, int maxSpend) {
        this.name = name;
        this.manaSpendOnSth = manaSpendOnSth;
        this.maxSpend = maxSpend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManaSpendOnSth() {
        return manaSpendOnSth;
    }

    public void setManaSpendOnSth(int manaSpendOnSth) {
        this.manaSpendOnSth = manaSpendOnSth;
    }

    public int getMaxSpend() {
        return maxSpend;
    }

    public void setMaxSpend(int maxSpend) {
        this.maxSpend = maxSpend;
    }

    @Override
    public String toString() {
        return "ActionModel{" +
                "name='" + name + '\'' +
                ", manaSpendOnSth=" + manaSpendOnSth +
                ", maxSpend=" + maxSpend +
                '}';
    }
}
