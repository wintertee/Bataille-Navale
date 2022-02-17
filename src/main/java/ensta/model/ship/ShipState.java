package ensta.model.ship;

import ensta.util.ColorUtil;

public class ShipState {

    private AbstractShip ship;
    private boolean struck;

    public ShipState(AbstractShip ship) {
        this.ship = ship;
        this.struck = false;
    }

    public ShipState() {
    }

    public void addStrike() {
        if (!isStruck())
            ship.addStrike();
        else
            throw new RuntimeException("already struck");
    }

    public boolean isStruck() {
        return struck;
    }

    @Override
    public String toString() {
        if (struck)
            return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.RED);
        else
            return String.valueOf(ship.getLabel());
    }

    public boolean isSunk() {
        return ship.isSunk();
    }

    public AbstractShip getShip() {
        return ship;
    }

}
