package wittybox.Tetrax;

import wittybox.Tetrax.Shape;

interface Tetramino {
	public boolean[][] getMatrix();
	public void rotateRight();
	public void rotateLeft();
}

class ShapeI extends Shape implements Tetramino {
	protected boolean [][][]matrix = {
		{
			{false, true, false, false},
			{false, true, false, false},
			{false, true, false, false},
			{false, true, false, false}
		},
		{
			{false, false, false, false},
			{false, false, false, false},
			{true, true, true, true},
			{false, false, false, false}
		}
	};
	ShapeI() {
		super.matrix = this.matrix;
	}
}

class ShapeJ extends Shape implements Tetramino {
	protected boolean [][][]matrix = {
		{
			{true, false, false},
			{true, true, true}
		},
		{
			{true, true},
			{true, false},
			{true, false},
		},
		{
			{true, true, true},
			{false, false, true}
		},
		{
			{false, true},
			{false, true},
			{true, true}
		}
	};
	ShapeJ() {
		super.matrix = this.matrix;
	}
}

class ShapeL extends Shape implements Tetramino {
	protected boolean [][][]matrix = {
		{
			{false, false, true},
			{true, true, true}
		},
		{
			{true, false},
			{true, false},
			{true, true},
		},
		{
			{true, true, true},
			{true, false, false}
		},
		{
			{true, true},
			{false, true},
			{false, true}
		}
	};

	ShapeL() {
		super.matrix = this.matrix;
	}
}

class ShapeO extends Shape implements Tetramino {
	protected boolean [][][]matrix = {
		{
			{true, true},
			{true, true}
		}
	};

	ShapeO() {
		super.matrix = this.matrix;
	}
}

class ShapeS extends Shape implements Tetramino {
	protected boolean [][][]matrix = {
		{
			{false, true, true},
			{true, true, false}
		},
		{
			{true, false},
			{true, true},
			{false, true},
		}
	};

	ShapeS() {
		super.matrix = this.matrix;
	}
}

class ShapeZ extends Shape implements Tetramino {
	protected boolean [][][]matrix = {
		{
			{true, true, false},
			{false, true, true}
		},
		{
			{false, true},
			{true, true},
			{true, false},
		}
	};

	ShapeZ() {
		super.matrix = this.matrix;
	}
}


class ShapeT extends Shape implements Tetramino {
	protected boolean [][][]matrix = {
		{
			{false, true, false},
			{true, true, true}
		},
		{
			{true, false},
			{true, true},
			{true, false},
		},
		{
			{true, true, true},
			{false, true, false}
		},
		{
			{false, true},
			{true, true},
			{false, true}
		}
	};

	ShapeT() {
		super.matrix = this.matrix;
	}
}

public class Shapes {
	public static void main(String args[]) {
		Tetramino t = new ShapeO();
		t.rotateRight();
		System.out.println(t);
	}
}
