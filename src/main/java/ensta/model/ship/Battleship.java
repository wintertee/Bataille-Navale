package ensta.model.ship;

import ensta.util.Orientation;

public class Battleship extends AbstractShip {
    public Battleship(String name, Orientation orientation) {
        super(name, 'B', 4, orientation);
    }
}
