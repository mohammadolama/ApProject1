package View.GUI.Panels;

public class Images {

    private String name;
    private int x;
    private int y;
    private int width;
    private int heigth;

    Images(String name, int x, int y, int width, int heigth) {
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

    int getX() {
        return x;
    }


    int getY() {
        return y;
    }


    int getWidth() {
        return width;
    }


    int getHeigth() {
        return heigth;
    }


}
