package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {

    public Destroyer(String name, Orientation orientation) {
        super(name, 'D', 2, orientation);
    }

}
