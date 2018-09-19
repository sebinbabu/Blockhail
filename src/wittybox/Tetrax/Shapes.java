package wittybox.Tetrax;

import java.util.Random;
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

	public static Shape getRandomShape() {
		int shape = rand.nextInt(points.length);
		return new Shape(points[shape]);
	}

	public static void main(String args[]) {
		Shape t = getRandomShape();
		System.out.println(t.toString());
	}

}
