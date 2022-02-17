package ensta.model.ship;

import ensta.util.Orientation;
import java.lang.RuntimeException;

public abstract class AbstractShip {

    private char label;
    private String name;
    private int length;
    private Orientation orientation;
    private int strikeCount;

    public void addStrike() {
        if (!isSunk())
            this.strikeCount += 1;
        else
            throw new RuntimeException("Ship is sunk");
    }

    public boolean isSunk() {
        return strikeCount >= length;
    }

    public AbstractShip(String name, char label, int length, Orientation orientation) {
        this.name = name;
        this.label = label;
        this.length = length;
        this.orientation = orientation;
    }

    public Character getLabel() {
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
