package View.GUI.Panels;

public class Images {

    private String name;
    private int x;
    private int y;
    private int width;
    private int heigth;
    private int index;

    Images(String name, int x, int y, int width, int heigth , int index) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.index=index;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
