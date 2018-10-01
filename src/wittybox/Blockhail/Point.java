package wittybox.Blockhail;

import wittybox.Blockhail.*;

public class Point {
	private int x;
	private int y;

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Point(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	Point() {
		this.x = 0;
		this.y = 0;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Point &&
				this.x == ((Point) o).x &&
				this.y == ((Point) o).y;				
	}

	@Override
	public String toString() {
		return new String("{" + this.x + ", " + this.y + "}");
	}
}