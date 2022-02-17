package ensta;

import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.ship.Battleship;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.util.Orientation;

public class TestBoard {
    public static void main(String args[]) {
        Board board = new Board("test");
        boolean result;

        Battleship ship = new Battleship("battleship", Orientation.EAST);

        result = board.putShip(ship, new Coords(4, 4));
        System.out.println("Ajouter un bateau battleship à (5,5) vers est: " + Boolean.toString(result));

        result = board.putShip(new Carrier("carrier", Orientation.NORTH), new Coords(2, 4));
        System.out.println("Ajouter un bateau carrier à (3,5) vers nord: " + Boolean.toString(result));

        result = board.putShip(new Destroyer("destroyer", Orientation.SOUTH), new Coords(5, 4));
        System.out.println("Ajouter un bateau battleship à (6,5) vers sud: " + Boolean.toString(result));

        result = board.putShip(new Destroyer("destroyer2", Orientation.SOUTH), new Coords(25, 4));
        System.out.println("Ajouter un bateau battleship à (26,5) vers sud: " + Boolean.toString(result));

        board.print();

        Coords coords = new Coords(2, 4);
        Hit hit = board.sendHit(coords);
        board.setHit(hit != Hit.MISS, coords);

        coords = new Coords(0, 0);
        hit = board.sendHit(coords);
        board.setHit(hit != Hit.MISS, coords);

        for (int i = 4; i < 8; ++i) {
            coords = new Coords(i, 4);
            hit = board.sendHit(coords);
            board.setHit(hit != Hit.MISS, coords);
        }
        board.print();

        if (hit == Hit.BATTLESHIP && ship.isSunk())
            System.out.println(ship.getLabel() + " coulé");
    }
}
