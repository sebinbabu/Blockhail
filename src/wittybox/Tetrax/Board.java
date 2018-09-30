package wittybox.Tetrax;

import wittybox.Tetrax.*;

class BoardRow {
	
}

public class Board {
	private int [][]matrix;
	private int rows = 20;
	private int cols = 12;

	public void deleteRow(int row) {
		for(int i = row; i > 0; i--) {
			matrix[i] = matrix[i - 1];
		}
		matrix[0] = new int[cols];
	}

	public void undeleteRow(int []row, int n) {
		for(int i = 0; i < n; i++) {
			matrix[i] = matrix[i + 1];
		}
		matrix[n] = row;
	}

	public int getRows() {
		return this.rows;
	}

	public int getCols() {
		return this.cols;
	}

	public void set(int x, int y, int val) {
		this.matrix[x][y] = val;
	}

	public int get(int x, int y) {
		return this.matrix[x][y];
	}

	public Board(){
		this.rows = 20;
		this.cols = 12;
		this.matrix = new int[20][12];
	}

	public Board(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.matrix = new int[rows][cols];
	}
 }