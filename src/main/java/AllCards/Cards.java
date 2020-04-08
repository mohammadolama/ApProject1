package AllCards;

public class Cards {
    private String name;
    private String description;
    private int ManaCost;
    private String type;
    private String heroClass;
    private String rarity;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getManaCost() {
        return ManaCost;
    }

    public void setManaCost(int manaCost) {
        ManaCost = manaCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public String getRarity() {
        return rarity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}