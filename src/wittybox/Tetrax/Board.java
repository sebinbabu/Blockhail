package wittybox.Tetrax;

import wittybox.Tetrax.*;
import java.awt.Color;

public class Board {
	private Color [][]matrix;
	private int rows = 20;
	private int cols = 12;

	public void deleteRow(int row) {
		for(int i = row; i > 0; i--) {
			matrix[i] = matrix[i - 1];
		}
		matrix[0] = new Color[cols];
	}

	public void undeleteRow(Color []row, int n) {
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

	public void set(int x, int y, Color color) {
		this.matrix[x][y] = color;
	}

	public Color get(int x, int y) {
		return this.matrix[x][y];
	}

	public Board(){
		this.rows = 20;
		this.cols = 12;
		this.matrix = new Color[20][12];
	}

	public Board(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.matrix = new Color[rows][cols];
	}
 }