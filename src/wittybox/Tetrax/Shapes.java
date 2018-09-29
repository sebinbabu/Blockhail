package wittybox.Tetrax;

import java.util.Random;
import java.awt.Color;
import wittybox.Tetrax.*;

public class Shapes {
	private static Random rand = new Random();
	private static Point [][][]points = {
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

	private static Shape []shapes = {
		new Shape(points[0], Color.blue),
		new Shape(points[1], Color.green),
		new Shape(points[2], Color.magenta),
		new Shape(points[3], Color.orange),
		new Shape(points[4], Color.red),
		new Shape(points[5], Color.yellow),
		new Shape(points[6], Color.pink)
	};

	public static Shape getRandomShape() {
		int shape = rand.nextInt(points.length);
		return shapes[shape];
	}

	public static Shape getShape(int shape) {
		return shapes[shape];
	}

	public static void main(String args[]) {
		Shape t = getRandomShape();
		System.out.println(t.toString());
	}

}
