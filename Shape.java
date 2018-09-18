package wittybox.Tetrax;

import java.util.Arrays;
import wittybox.Tetrax.*;

class Shape {
	private Point [][]points;
	private int rotation;

	public Point[] getPoints() {
		if(points == null)
			return null;
		return points[rotation];
	}

	public void rotateRight() {
		if(points != null) {
			rotation++;
			if(rotation == points.length)
				rotation = 0;
		}
	}

	public void rotateLeft() {
		if(points != null) {
			rotation--;
			if(rotation < 0)
				rotation = points.length - 1;
		}
	}

	@Override
	public boolean equals(Object s) {
		return s instanceof Shape &&
				this.rotation == ((Shape) s).rotation &&
				Arrays.equals(this.points, ((Shape) s).points);
	}

	@Override
	public String toString() {
		int i, j;
		StringBuffer sb = new StringBuffer(32);
		sb.append("{");
		for(i = 0; i < this.points[this.rotation].length - 1; i++) {
			sb.append(this.points[this.rotation][i].toString());
			sb.append(", ");
		}

		if(i < this.points[this.rotation].length) {
			sb.append(this.points[this.rotation][i].toString());
		}
		sb.append("}");
		return sb.toString();
	}

	Shape() {
		this.points = null;
		this.rotation = 0;
	}

	Shape(Point [][]points) {
		this.points = points;
		this.rotation = 0;
	}
}