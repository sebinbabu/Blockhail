package wittybox.Tetrax;

import java.util.Random;
import java.util.Arrays;
import wittybox.Tetrax.Point;

class Shape {
	private static Random r = new Random(); 
	public static final int I = 0;
	public static final int O = 1;
	public static final int T = 2;
	public static final int S = 3;
	public static final int Z = 4;
	public static final int J = 5;
	public static final int L = 6;

	Point []points = new Point[4];
	boolean halted;
	Point pos;
	int shape;

	Point[] getPoints() {
		return points;
	}

	void rotate() {
		for(Point p : points) {
			p.rotateRight();
		}
	}

	@Override
	public boolean equals(Object s) {
		return s instanceof Shape &&
				this.halted == ((Shape) s).halted &&
				this.pos.equals(((Shape) s).pos) &&
				this.shape == ((Shape) s).shape &&
				Arrays.equals(this.points, ((Shape) s).points);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(32);
		sb.append("{");
		for(int i = 0; i < points.length - 1; i++) {
			sb.append(points[i]);
			sb.append(", ");
		}
		sb.append(points[points.length - 1]);
		sb.append("}");
		return sb.toString();
	}

	Shape() {
		this(r.nextInt(7), new Point(10, 10));
	}

	Shape(int shape, Point pos) {
		this.shape = shape;
		this.pos = pos;
		halted = false;
		switch(shape) {
			case I:
					points[0] = new Point(-2, 0);
					points[1] = new Point(-1, 0);
					points[2] = new Point(0, 0);
					points[3] = new Point(1, 0);
				break;
			case O:
					points[0] = new Point(0, 0);
					points[1] = new Point(0, 1);
					points[2] = new Point(1, 0);
					points[3] = new Point(1, 1);
				break;

			case T:
					points[0] = new Point(0, 0);
					points[1] = new Point(1, 0);
					points[2] = new Point(-1, 0);
					points[3] = new Point(0, 1);
				break;

			case S:
					points[0] = new Point(1, 1);
					points[1] = new Point(0, 1);
					points[2] = new Point(0, 0);
					points[3] = new Point(-1, 0);
				break;

			case Z:
					points[0] = new Point(-1, 1);
					points[1] = new Point(0, 1);
					points[2] = new Point(0, 0);
					points[3] = new Point(1, 0);
				break;

			case J:
					points[0] = new Point(-1, 1);
					points[1] = new Point(-1, 0);
					points[2] = new Point(0, 0);
					points[3] = new Point(1, 0);
				break;

			case L:
					points[0] = new Point(1, 1);
					points[1] = new Point(1, 0);
					points[2] = new Point(0, 0);
					points[3] = new Point(-1, 0);
				break;
		}
	}

	public static void main(String args[]) {
		Shape activeShape = new Shape();
		System.out.println("Before rotation: " + activeShape);
		activeShape.rotate();
		System.out.println("After rotation: " + activeShape);
	}
}