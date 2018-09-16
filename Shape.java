package wittybox.Tetrax;

import java.util.Arrays;

class Shape {
	protected boolean [][][]matrix;
	protected int rotation;

	public boolean[][] getMatrix() {
		return matrix[rotation];
	}

	public void rotateRight() {
		rotation++;
		if(rotation == matrix.length)
			rotation = 0;
	}

	public void rotateLeft() {
		rotation--;
		if(rotation < 0)
			rotation = matrix.length - 1;
	}

	@Override
	public boolean equals(Object s) {
		return s instanceof Shape &&
				this.rotation == ((Shape) s).rotation &&
				Arrays.deepEquals(this.matrix, ((Shape) s).matrix);
	}

	@Override
	public String toString() {
		int i, j;
		StringBuffer sb = new StringBuffer(32);
		for(i = 0; i < this.matrix[this.rotation].length; i++) {
			for(j = 0; j < this.matrix[this.rotation][i].length; j++) {
				sb.append(this.matrix[this.rotation][i][j] ? '#' : ' ');				
			}
			if(i != this.matrix[this.rotation].length - 1)
				sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	Shape() {
		this.matrix = null;
		this.rotation = 0;
	}
}