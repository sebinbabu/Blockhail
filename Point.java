package wittybox.Tetrax;

public class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	Point() {
		this.x = 0;
		this.y = 0;
	}

	public void rotateRight() {
		int p = this.x;
		this.x = this.y;
		this.y = -p;
	}

	public void rotateLeft() {
		int p = this.x;
		this.x = -this.y;
		this.y = p;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public boolean equals(Object p) {
		return p instanceof Point && this.x == ((Point) p).x && this.y == ((Point) p).y;
	}

	@Override
	public String toString() {
		return new String("{" + this.x + ", " + this.y + "}");
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 43 * result + 31 * x + 29 * y;
		return result;
	}

	public static void main(String args[]) {
		System.out.println("Testing Points");
		System.out.println();
		Point []pArr = {
			new Point(3, 5),
			new Point(3, -5),
			new Point(-3, 5),
			new Point(-3, -5)
		};

		for(Point p : pArr) {
			System.out.println("Before rotateRight: " + p);
			p.rotateRight();
			System.out.println("After rotateRight: " + p);
			System.out.println("After rotateLeft: " + p);
			p.rotateLeft();
			System.out.println();
		}

		System.out.println();
		Point p1 = new Point(1, 2);
		Point p2 = new Point(p1);
		if(p1.equals(p2))
			System.out.println(p1 + " and " + p2 + " are equal");
		else
			System.out.println(p1 + " and " + p2 + " are not equal");

		p2.rotateRight();

		if(p1.equals(p2))
			System.out.println(p1 + " and " + p2 + " are equal");
		else
			System.out.println(p1 + " and " + p2 + " are not equal");
	}
}
