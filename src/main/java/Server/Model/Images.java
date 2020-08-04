package Server.Model;

public class Images {

    private String name;
    private int x;
    private int y;
    private int width;
    private int heigth;
    private int index;
    private boolean sleep;

    public Images(String name, int x, int y, int width, int heigth, int index) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.index=index;
    }
    public Images(String name, int x, int y, int width, int heigth, int index , boolean sleep) {
        this(name,x,y,width,heigth,index);
        this.sleep=sleep;
    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
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


    public int getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }


    public int getHeigth() {
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
