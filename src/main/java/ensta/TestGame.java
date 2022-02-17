package ensta;

import ensta.ai.BattleShipsAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.Battleship;
import ensta.model.ship.Carrier;
import ensta.util.Orientation;

public class TestGame {
    public static void main(String args[]) {
        Board board = new Board("test");
        board.print();

        AbstractShip ships[] = {
                new Battleship("battleship", Orientation.EAST),
                new Carrier("carrier", Orientation.NORTH) };

        board.putShip(ships[0], new Coords(4, 4));
        board.putShip(ships[1], new Coords(2, 4));

        BattleShipsAI ai = new BattleShipsAI(board, board);

        int sunkShipCounter = 0;
        Hit hit;

        while (sunkShipCounter < ships.length) {
            System.out.println();
            System.out.println("====================");
            Coords coords = new Coords();
            hit = ai.sendHit(coords);
            if (hit != Hit.MISS && hit != Hit.STRIKE)
                sunkShipCounter++;
            System.out.println(coords);
            System.out.print(hit);
            if (hit != Hit.MISS && hit != Hit.STRIKE)
                System.out.print(" coulé");
            System.out.println();
            board.print();
        }
    }
}
