package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class Board implements IBoard {

	private String name;
	private int size;
	private char[][] navires;
	private boolean[][] frappes;

	private static final int DEFAULT_SIZE = 10;

	public Board(String name, int size) {
		this.name = name;
		this.size = size;
		navires = new char[size][size];
		frappes = new boolean[size][size];
	}

	public Board(String nom) {
		this(nom, DEFAULT_SIZE);
	}

	public void print() {
		System.out.println("Navires :");
		System.out.print("   ");
		for (char c = 'A'; c < 'A' + size; ++c) {
			System.out.print(c + " ");
		}
		System.out.println();
		for (int x = 0; x < size; ++x) {
			System.out.format("%-3d", x + 1);
			for (int y = 0; y < size; ++y) {
				System.out.print((navires[x][y] == 0 ? "." : navires[x][y]) + " ");
			}
			System.out.println();
		}

		System.out.println();

		System.out.println("Frappes :");
		System.out.print("   ");
		for (char c = 'A'; c < 'A' + size; ++c) {
			System.out.print(c + " ");
		}
		System.out.println();
		for (int x = 0; x < size; ++x) {
			System.out.format("%-3d", x + 1);
			for (int y = 0; y < size; ++y) {
				System.out.print((frappes[x][y] ? "x" : ".") + " ");
			}
			System.out.println();
		}
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() >= this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean putShip(AbstractShip ship, Coords coords) {
		try {
			if (canPutShip(ship, coords)) {
				Orientation o = ship.getOrientation();
				int dx = 0, dy = 0;
				switch (o) {
					case EAST:
						dx = 1;
						break;
					case SOUTH:
						dy = 1;
						break;
					case NORTH:
						dy = -1;
						break;
					case WEST:
						dx = -1;
						break;
				}

				Coords iCoords = new Coords(coords);

				for (int i = 0; i < ship.getLength(); ++i) {
					navires[iCoords.getY()][iCoords.getX()] = ship.getLabel();
					iCoords.setX(iCoords.getX() + dx);
					iCoords.setY(iCoords.getY() + dy);
				}
				return true;
			} else {
				return false;
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			return false;
		}
	}

	@Override
	public boolean hasShip(Coords coords) {
		try {
			return navires[coords.getY()][coords.getX()] != 0;
		} catch (ArrayIndexOutOfBoundsException exception) {
			return false;
		}
	}

	@Override
	public void setHit(boolean hit, Coords coords) {
		try {
			frappes[coords.getX()][coords.getY()] = hit;
		} catch (ArrayIndexOutOfBoundsException exception) {
		}

	}

	@Override
	public Boolean getHit(Coords coords) {
		try {
			return frappes[coords.getX()][coords.getY()];
		} catch (ArrayIndexOutOfBoundsException exception) {
		}
		return false;
	}

	@Override
	public Hit sendHit(Coords res) {
		// TODO Auto-generated method stub
		return null;
	}

}
