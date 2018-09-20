package wittybox.Tetrax;

import wittybox.Tetrax.*;

public class Board {
	private boolean [][]matrix;
	private int rows = 20;
	private int cols = 16;

	public void deleteRow(int row) {
		for(int i = row; i > 0; i--) {
			matrix[i] = matrix[i - 1];
		}
		matrix[0] = new boolean[cols];
	}

	public int getRows() {
		return this.rows;
	}

	public int getCols() {
		return this.cols;
	}

	public void set(int x, int y, boolean val) {
		this.matrix[x][y] = val;
	}

	public boolean get(int x, int y) {
		return this.matrix[x][y];
	}

	public Board(Terminal terminal) throws IOException {
		this.rows = 20;
		this.cols = 16;
		this.matrix = new boolean[20][16];
	}

	public Board(int rows, int cols, Terminal terminal) throws IOException {
		this.rows = rows;
		this.cols = cols;
		this.matrix = new boolean[rows][cols];
	}
 }