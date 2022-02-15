package ensta.model.ship;

import ensta.util.Orientation;

public class Submarine extends AbstractShip {
    public Submarine(String name, Orientation orientation) {
        super(name, 'S', 3, orientation);
    }
}
