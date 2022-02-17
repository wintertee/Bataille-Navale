package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.ai.PlayerAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.Battleship;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;
import ensta.util.Orientation;

public class Game {

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.dat");

	/*
	 * *** Attributs
	 */
	private Player player1;
	private Player player2;
	// private Scanner sin;

	/*
	 * *** Constructeurs
	 */
	public Game() {
	}

	public Game init() {
		if (!loadSave()) {

			// init boards
			Board board1 = new Board("board1");
			Board board2 = new Board("board2");

			// init this.player1 & this.player2
			player1 = new Player(board1, board2, createDefaultShips());
			player2 = new PlayerAI(board2, board1, createDefaultShips());

			// place player ships
			player1.putShips();
			player2.putShips();
		}
		return this;
	}

	/*
	 * *** Méthodes
	 */
	public void run() {
		Coords coords = new Coords();
		Board b1 = player1.getBoard();
		Hit hit;

		// main loop
		b1.print();
		boolean done;
		do {
			hit = player1.sendHit(coords); // player1 send a hit

			boolean strike = hit != Hit.MISS;
			b1.setHit(strike, coords);// set this hit on his board (b1)

			done = updateScore();
			b1.print();
			System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

			// save();

			if (!done && !strike) {
				do {
					hit = player2.sendHit(coords); // player2 send a hit.

					strike = hit != Hit.MISS;
					if (strike) {
						b1.print();
					}
					System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
					done = updateScore();

					if (!done) {
						// save();
					}
				} while (strike && !done);
			}

		} while (!done);

		SAVE_FILE.delete();
		System.out.println(String.format("joueur %d gagne", player1.isLose() ? 2 : 1));
		// sin.close();
	}

	private void save() {
		// try {
		// // TODO bonus 2 : uncomment
		// // if (!SAVE_FILE.exists()) {
		// // SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
		// // }
		//
		// // TODO bonus 2 : serialize players
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	private boolean loadSave() {
		// if (SAVE_FILE.exists()) {
		// try {
		// // TODO bonus 2 : deserialize players
		//
		// return true;
		// } catch (IOException | ClassNotFoundException e) {
		// e.printStackTrace();
		// }
		// }
		return false;
	}

	private boolean updateScore() {
		for (Player player : new Player[] { player1, player2 }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) {
				if (ship.isSunk()) {
					destroyed++;
				}
			}

			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {
				return true;
			}
		}
		return false;
	}

	private String makeHitMessage(boolean incoming, Coords coords, Hit hit) {
		String msg;
		ColorUtil.Color color = ColorUtil.Color.RESET;
		switch (hit) {
			case MISS:
				msg = hit.toString();
				break;
			case STRIKE:
				msg = hit.toString();
				color = ColorUtil.Color.RED;
				break;
			default:
				msg = hit.toString() + " coulé";
				color = ColorUtil.Color.RED;
		}
		msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords.getX())),
				(coords.getY() + 1), msg);
		return ColorUtil.colorize(msg, color);
	}

	private static List<AbstractShip> createDefaultShips() {
		return Arrays.asList(new AbstractShip[] {
				new Destroyer("Destroyer", Orientation.EAST),
				new Submarine("Submarine", Orientation.WEST),
				new Submarine("Submarine2", Orientation.WEST),
				new Battleship("Battleship", Orientation.NORTH),
				new Carrier("Battleship", Orientation.SOUTH)
		});
	}
}
