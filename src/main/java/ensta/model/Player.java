package ensta.model;

import java.io.Serializable;
import java.util.List;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;

public class Player {
	/*
	 * ** Attributs
	 */
	private Board board;
	protected Board opponentBoard;
	private int destroyedCount;
	protected AbstractShip[] ships;
	private boolean lose;

	/*
	 * ** Constructeur
	 */
	public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
		this.setBoard(board);
		this.ships = ships.toArray(new AbstractShip[0]);
		this.opponentBoard = opponentBoard;
	}

	/*
	 * ** Méthodes
	 */

	/**
	 * Read keyboard input to get ships coordinates. Place ships on given
	 * coodrinates.
	 */
	public void putShips() {
		boolean done = false;
		int i = 0;

		do {
			AbstractShip ship = ships[i];
			String msg = String.format("placer %d : %s(%d)", i + 1, ship.getName(), ship.getLength());
			System.out.println(msg);
			InputHelper.ShipInput res = InputHelper.readShipInput();
			// set ship orientation
			switch (res.orientation) {
				case "north":
					ship.setOrientation(Orientation.NORTH);
					break;
				case "south":
					ship.setOrientation(Orientation.SOUTH);
					break;
				case "east":
					ship.setOrientation(Orientation.EAST);
					break;
				case "west":
					ship.setOrientation(Orientation.WEST);
					break;
			}
			// put ship at given position
			boolean placementResult = board.putShip(ship, new Coords(res.x, res.y));
			// when ship placement successful
			if (placementResult) {
				++i;
			} else {
				System.out.println("Cannot put this ship on board.");
			}
			done = i == 5;

			board.print();
		} while (!done);
	}

	public Hit sendHit(Coords[] coords) {
		boolean done = false;
		Hit hit = null;

		do {
			System.out.println("où frapper?");
			InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
			// call sendHit on this.opponentBoard
			Coords coordsInput = new Coords(hitInput.x, hitInput.y);
			hit = this.opponentBoard.sendHit(coordsInput);

			// Game expects sendHit to return BOTH hit result & hit coords.
			// return hit is obvious. But how to return coords at the same time ?
			coords[0] = coordsInput;
			// use array parameter to pass coords
		} while (!done);

		return hit;
	}

	public AbstractShip[] getShips() {
		return ships;
	}

	public void setShips(AbstractShip[] ships) {
		this.ships = ships;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getDestroyedCount() {
		return destroyedCount;
	}

	public void setDestroyedCount(int destroyedCount) {
		this.destroyedCount = destroyedCount;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
}
