package wittybox.Tetrax;

import wittybox.Tetrax.*;

public class Board {
	private boolean [][]matrix;
	private int rows = 20;
	private int cols = 12;

	public void deleteRow(int row) {
		for(int i = row; i > 0; i--) {
			matrix[i] = matrix[i - 1];
		}
		matrix[0] = new boolean[cols];
	}

	public void undeleteRow(int row) {
		for(int i = 0; i < row; i++) {
			matrix[i] = matrix[i + 1];
		}
		matrix[row] = new boolean[this.cols];
		for(int i = 0; i < this.cols; i++) {
			matrix[row][i] = true;
		}
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

	public Board(){
		this.rows = 20;
		this.cols = 12;
		this.matrix = new boolean[20][12];
	}

	public Board(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.matrix = new boolean[rows][cols];
	}
 }