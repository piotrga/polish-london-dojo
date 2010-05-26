package pld.gameoflife;

import java.util.Set;
import java.util.TreeSet;

/**
 * Methods which take x/y coordinates use them module width/height so the client
 * does not need to ensure the arguments are within range.
 */
public class Board {
	private final int width;
	private final int height;
	private boolean[] cells;
	private final Set<Listener> listeners = new TreeSet<Listener>();

	public static Board random(int width, int height) {
		return createBoard(width, height, new Setup() {
			public boolean valueAt(int x, int y) {
				return Math.random() < 0.4;
			}
		});
	}
	
	public static Board gosperGliderGun(int width, int height) {
		final String[] template = {
				"                                      ",
				"                         X            ",
				"                       X X            ",
				"             XX      XX            XX ",
				"            X   X    XX            XX ",
				" XX        X     X   XX               ",
				" XX        X   X XX    X X            ",
				"           X     X       X            ",
				"            X   X                     ",
				"             XX                       ",
				"                                      ",
			};
		int minWidth = template[0].length();
		int minHeight = template.length;
		if (height < minHeight || width < minWidth)
			throw new IllegalArgumentException("minimum width x height: " + minWidth + " x " + minHeight);
		return createBoard(width, height, new Setup() {
			public boolean valueAt(int x, int y) {
				return y < template.length && x < template[y].length() && template[y].charAt(x) != ' ';
			}
		});
	}
	
	private static Board createBoard(int width, int height,
			Setup setup) {
		boolean[] cells = new boolean[height * width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				cells[toIndex(x, y, width, height)] = setup.valueAt(x, y); 
			}
		}
		return new Board(width, height, cells);
	}

	private Board(int width, int height, boolean[] cells) {
		if (cells.length != width * height)
			throw new IllegalArgumentException("invalid array lenght "
					+ cells.length + " for given width " + width
					+ "and height " + height);
		this.width = width;
		this.height = height;
		this.cells = cells;
	}

	public synchronized void nextTurn() {
		boolean[] newCells = new boolean[cells.length];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < height; x++) {
				setAliveInNextTurn(newCells, x, y);
			}
		}
		cells = newCells;
		notifyListeners();
	}

	private void setAliveInNextTurn(boolean[] newCells, int x, int y) {
		int neighboursAlive = 0;
		for (int oy = -1; oy <= 1; oy++) {
			for (int ox = -1; ox <= 1; ox++) {
				if (ox == 0 && oy == 0)
					continue;
				if (cells[toIndex(x + ox, y + oy)])
					neighboursAlive++;
			}
		}
		int index = toIndex(x, y);
		if (neighboursAlive == 3 || neighboursAlive == 2 && cells[index]) newCells[index] = true;
	}

	public synchronized boolean isAliveAt(int x, int y) {
		return cells[toIndex(x, y)];
	}

	private int toIndex(int x, int y) {
		return toIndex(x, y, width, height);
	}

	private static int toIndex(int x, int y, int width, int height) {
		return ((y + height) % height) * width + (x + width) % width;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void notifyListeners() {
		for (Listener listener : listeners)
			listener.onBoardUpdated(this);
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	public interface Listener {
		void onBoardUpdated(Board sender);
	}

	private interface Setup {
		boolean valueAt(int x, int y);
	}
}
