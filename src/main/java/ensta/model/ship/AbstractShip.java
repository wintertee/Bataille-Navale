package ensta.model.ship;

import ensta.util.Orientation;

public abstract class AbstractShip {

    private char label;
    private String name;
    private int length;
    private Orientation orientation;

    public AbstractShip(String name, char label, int length, Orientation orientation) {
        this.name = name;
        this.label = label;
        this.length = length;
        this.orientation = orientation;
    }

    public char getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getLength() {
        return length;
    }

}
