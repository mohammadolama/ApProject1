package GUI;

public class Images {

    private String name;
    private int x;
    private int y;
    private int width;
    private int heigth;

    public Images(String name, int x, int y, int width, int heigth) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }
}
