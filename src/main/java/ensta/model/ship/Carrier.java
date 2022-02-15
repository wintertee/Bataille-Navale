package ensta.model.ship;

import ensta.util.Orientation;

public class Carrier extends AbstractShip {
    public Carrier(String name, Orientation orientation) {
        super(name, 'C', 5, orientation);
    }
}
