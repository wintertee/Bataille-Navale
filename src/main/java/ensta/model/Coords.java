package ensta.model;

import java.util.Random;

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

    public static Coords randomCoords(int size) {
        Random r = new Random();
        return new Coords(r.nextInt(size), r.nextInt(size));
    }

    public boolean isInBoard(int size) {
        return 0 <= x && x < size && 0 <= y && y < size;
    }

    public void setCoords(Coords res) {
        this.x = res.getX();
        this.y = res.getY();
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
