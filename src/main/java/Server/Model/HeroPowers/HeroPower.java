package Server.Model.HeroPowers;

public abstract class HeroPower {
    private String name;
    private int manaCost;
    private boolean needFriendlyTarget;
    private boolean needEnemyTarget;

    public HeroPower() {
    }

    public HeroPower(String name, int manaCost, boolean needFriendlyTarget, boolean needEnemyTarget) {
        this.name = name;
        this.manaCost = manaCost;
        this.needFriendlyTarget = needFriendlyTarget;
        this.needEnemyTarget = needEnemyTarget;
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


    public boolean isNeedFriendlyTarget() {
        return needFriendlyTarget;
    }

    public void setNeedFriendlyTarget(boolean needFriendlyTarget) {
        this.needFriendlyTarget = needFriendlyTarget;
    }

    public boolean isNeedEnemyTarget() {
        return needEnemyTarget;
    }

    public void setNeedEnemyTarget(boolean needEnemyTarget) {
        this.needEnemyTarget = needEnemyTarget;
    }

//    public abstract void action(Character target) ;
}
