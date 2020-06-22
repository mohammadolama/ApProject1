package Model.Cards;

public class Spell extends Card {

    int usageTimes;
    int healthRestore;
    String title;

    public int getUsageTimes() {
        return usageTimes;
    }

    public void setUsageTimes(int usageTimes) {
        this.usageTimes = usageTimes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHealthRestore() {
        return healthRestore;
    }

    public void setHealthRestore(int healthRestore) {
        this.healthRestore = healthRestore;
    }
}
