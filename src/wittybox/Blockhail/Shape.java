package wittybox.Blockhail;

import java.awt.Color;
import java.util.Random;
import wittybox.Blockhail.*;

class Shape {
	private static Random rand = new Random();
	private static Point [][][]points = {
		null,
		{	//I Shape
			{new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2)},
			{new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0)}
		},
		{	// O Shape
			{new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)}
		},
		{	// L Shape
			{new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(1, 0)},
			{new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)},
			{new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)},
			{new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(2, 1)}
		},
		{	// J Shape
			{new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(1, 2)},
			{new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0)},
			{new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(1, 2)},
			{new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(2, 0)}
		},
		{	// S Shape
			{new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, -1)},
			{new Point(-1, 0), new Point(0, 0), new Point(0, 1), new Point(1, 1)},
		},
		{	// Z Shape
			{new Point(0, -1), new Point(0, 0), new Point(1, 0), new Point(1, 1)},
			{new Point(-1, 1), new Point(0, 0), new Point(0, 1), new Point(1, 0)},
		},
		{	// T Shape
			{new Point(-1, 0), new Point(0, -1), new Point(0, 0), new Point(0, 1)},
			{new Point(-1, 0), new Point(0, 0), new Point(0, 1), new Point(1, 0)},
			{new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(1, 0)},
			{new Point(-1, 0), new Point(0, -1), new Point(0, 0), new Point(1, 0)}
		}
	};

	private static Color[] colors = {
		null,
		new Color(128, 0, 0),
		new Color(100, 100, 100),
		new Color(128, 0, 128),
		new Color(0, 51, 102),
		new Color(0, 100, 60),
		new Color(210, 105, 30),
		new Color(0, 128, 128)
	};

	public static Color getColor(int val) {
		return Shape.colors[val];
	}

	public static Shape getRandomShape() {
		int shape = rand.nextInt(Shape.points.length - 1) + 1;
		return Shape.getShape(shape);
	}

	public static Shape getShape(int shape) {
		return new Shape(shape);
	}

	private int shape;
	private int rotation;
	private Point pos;

	public Point[] getPoints() {
		return Shape.points[shape][rotation];
	}

	public Color getColor() {
		return Shape.colors[shape];
	}

	public int getColorIndex() {
		return shape;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Point getPos() {
		return this.pos;
	}

	public void rotateRight() {
		if(points != null) {
			rotation++;
			if(rotation == Shape.points[shape].length)
				rotation = 0;
		}
	}

	public void rotateLeft() {
		if(points != null) {
			rotation--;
			if(rotation < 0)
				rotation = Shape.points[shape].length - 1;
		}
	}

	public Shape() {
		this.pos = null;
		this.shape = 0;
		this.rotation = 0;
	}

	public Shape(int shape) {
		this.pos = null;
		this.shape = shape;
		this.rotation = 0;
	}

	public Shape(Point pos, int shape) {
		this.pos = pos;
		this.shape = shape;
		this.rotation = 0;
	}
}