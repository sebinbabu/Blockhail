package wittybox.blockhail;

import java.io.Serializable;
import wittybox.blockhail.*;

class BoardRowWrapper implements Serializable {
	private int location;
	private int []row;
	public BoardRowWrapper() {
		this.location = -1;
		this.row = null;
	}
	public BoardRowWrapper(int location, int []row) {
		this.location = location;
		this.row = row;
	}
	public int[] getRow() {
		return this.row;
	}

	public int getLocation() {
		return this.location;
	}
}

public class Board {
	private int [][]matrix;
	private int rows;
	private int cols;



	public void deleteRow(int row) {
		for(int i = row; i > 0; i--) {
			matrix[i] = matrix[i - 1];
		}
		matrix[0] = new int[cols];
	}

	public void undeleteRow(BoardRowWrapper row) {
		int n = row.getLocation();
		for(int i = 0; i < n; i++) {
			matrix[i] = matrix[i + 1];
		}
		matrix[n] = row.getRow();
	}

	public BoardRowWrapper getRow(int n) {
		return new BoardRowWrapper(n, this.matrix[n]);
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

	public void reset() {
		this.matrix = new int[this.rows][this.cols];
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