package ensta.model;

public class Coords {
    private int x;
    private int y;

    public Coords() {
        x = 0;
        y = 0;
    }

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coords(Coords coords) {
        x = coords.getX();
        y = coords.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
