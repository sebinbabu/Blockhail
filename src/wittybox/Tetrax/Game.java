package wittybox.Tetrax;

import wittybox.Tetrax.*;
import java.awt.Color;
import java.io.IOException;

public class Game {
	private Shape activeShape = null;
	private Point activeShapePos = null;
	private Board board = null;
	private int score = 0;

	public int getScore() {
		return this.score;
	}

	public Board getBoard() {
		return this.board;
	}

	public Shape getActiveShape() {
		return this.activeShape;
	}

	public Point getActiveShapePos() {
		return this.activeShapePos;
	}

	private boolean isRowFilled(int row) {
		int cols = this.board.getCols();
		for(int i = 0; i < cols; i++) {
			if(this.board.get(row, i) == false) 
				return false;
		}
		return true;
	}

	private void removeFilledRows() {
		for(int i = 0; i < board.getRows(); i++) {
			if(isRowFilled(i)) {
				this.board.deleteRow(i);
				this.score += 10;
			}
		}
	}

	public boolean rotateShape() {
		this.activeShape.rotateRight();
		Point []points = this.activeShape.getPoints();
		int potentialX = this.activeShapePos.getX(), potentialY = this.activeShapePos.getY();
		int x, y;

		for(Point pos : points) {
			x = pos.getX() + potentialX;
			y = pos.getY() + potentialY;
			if(y < 0) {
				if(!this.moveShapeRight()) {
					this.activeShape.rotateLeft();
					return false;
				} else {
					return true;
				}
			}
			if(y >= this.board.getCols()) {
				if(!this.moveShapeLeft()) {
					this.activeShape.rotateLeft();
					return false;
				} else {
					return true;
				}
			}
			if(x >= this.board.getRows()) {
				this.activeShape.rotateLeft();
				return false;
			}

			if(this.board.get(x, y)) {
				this.activeShape.rotateLeft();
				return false;
			}
		}
		return true;
	}

	public void setNewShape() {
		this.activeShape = Shapes.getRandomShape();
		this.activeShapePos = new Point(3, 6);
	}

	public void pasteShape() {
		Point []points = this.activeShape.getPoints();
		for(Point p : points) {
			this.board.set(p.getX() + this.activeShapePos.getX(), p.getY() + this.activeShapePos.getY(), true);
		}
	}

	public boolean moveShapeLeft() {
		int potentialX = this.activeShapePos.getX();
		int potentialY = this.activeShapePos.getY() - 1;
		Point []points = this.activeShape.getPoints();
		int res;

		for(Point pos : points) {
			res = pos.getY() + potentialY;
			if(res < 0 || res >= this.board.getCols())
				return false;
			if(this.board.get(potentialX + pos.getX(), res))
				return false;
		}
		this.activeShapePos.setY(potentialY);
		return true;
	}

	public boolean moveShapeRight() {
		int potentialX = this.activeShapePos.getX();
		int potentialY = this.activeShapePos.getY() + 1;
		Point []points = this.activeShape.getPoints();
		int res;

		for(Point pos : points) {
			res = pos.getY() + potentialY;
			if(res < 0 || res >= this.board.getCols())
				return false;
			if(this.board.get(potentialX + pos.getX(), res))
				return false;
		}
		this.activeShapePos.setY(potentialY);
		return true;
	}

	public boolean moveShapeDown(){
		int potentialX = this.activeShapePos.getX() + 1;
		int potentialY = this.activeShapePos.getY();
		Point []points = this.activeShape.getPoints();
		int res;
		for(Point pos : points) {
			res = pos.getX() + potentialX;
			if(res >= this.board.getRows()) {
				this.pasteShape();
				this.removeFilledRows();
				this.setNewShape();
				return false;
			}
			if(this.board.get(res, potentialY + pos.getY())) {
				this.pasteShape();
				this.removeFilledRows();
				this.setNewShape();
				return false;
			}
		}
		this.activeShapePos.setX(potentialX);
		return true;
	}

	public void clearShape() {
		Point []points = this.activeShape.getPoints();
		for(Point p : points) {
			this.board.set(p.getX() + this.activeShapePos.getX(), p.getY() + this.activeShapePos.getY(), false);			
		}
	}

	public Game() {
		this.activeShape = Shapes.getRandomShape();
		this.activeShapePos = new Point(3, 6);
		this.board = new Board(); 
		this.score = 0;
	}

	public static void main(String args[]) {

	}
}