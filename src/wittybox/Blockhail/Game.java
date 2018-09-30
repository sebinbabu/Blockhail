package wittybox.Blockhail;

import wittybox.Blockhail.*;
import java.awt.Color;
import java.util.Stack;

public class Game {
	private Shape activeShape;
	private Board board;
	private int score;
	private boolean paused = true;
	private boolean stopped = false;

	Stack <Operation> undoStack = new Stack<Operation>();
	Stack <Operation> redoStack = new Stack<Operation>();

	public boolean isPaused() {
		return paused;
	}

	public void resume() {
		if(!redoStack.empty())
			redoStack = new Stack<Operation>();
		this.paused = false;
	}

	public void pause() {
		this.paused = true;
	}

	public void undo() {
		this.pause();
		if(undoStack.empty())
			return;
		Operation op = (Operation) undoStack.pop();
		redoStack.push(op);

		switch(op.getOperation()) {
			case DELETE_ROW:
				this.undeleteBoardRow((BoardRowWrapper) op.getVal());
				score -= 10;
				break;
			case ROTATE_SHAPE:
				this.reverseRotateShape();
				break;
			case PASTE_SHAPE:
				this.activeShape = (Shape) op.getVal();
				this.clearShape();
				break;
			case MOVE_SHAPE_LEFT:
				this.activeShape.getPos().setY(this.activeShape.getPos().getY() + 1);
				break;
			case MOVE_SHAPE_RIGHT:
				this.activeShape.getPos().setY(this.activeShape.getPos().getY() - 1);
				break;
			case MOVE_SHAPE_DOWN:
				this.moveShapeUp();
				break;
		}
	}

	public void redo() {
		if(redoStack.empty())
			return;
		Operation op = (Operation) redoStack.pop();

		switch(op.getOperation()) {
			case DELETE_ROW:
				this.addOperation(Operations.DELETE_ROW, op.getVal());
				this.board.deleteRow(((BoardRowWrapper) op.getVal()).getLocation());
				score += 10;
				break;
			case ROTATE_SHAPE:
				this.activeShape.rotateLeft();
				this.addOperation(Operations.ROTATE_SHAPE);
				break;
			case PASTE_SHAPE:
				//this.activeShape = (Shape) op.getVal();
				this.pasteShape();
				break;
			case MOVE_SHAPE_LEFT:
				this.activeShape.getPos().setY(this.activeShape.getPos().getY() - 1);
				this.addOperation(Operations.MOVE_SHAPE_LEFT);
				break;
			case MOVE_SHAPE_RIGHT:
				this.activeShape.getPos().setY(this.activeShape.getPos().getY() + 1);
				this.addOperation(Operations.MOVE_SHAPE_RIGHT);
				break;
			case MOVE_SHAPE_DOWN:
				this.activeShape.getPos().setX(this.activeShape.getPos().getX() + 1);
				this.addOperation(Operations.MOVE_SHAPE_DOWN);
				break;
			case SET_NEW_SHAPE:
				this.activeShape = (Shape) op.getVal();
				break;
		}
	}

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
		return this.activeShape.getPos();
	}

	private boolean isRowFilled(int row) {
		int cols = this.board.getCols();
		for(int i = 0; i < cols; i++) {
			if(this.board.get(row, i) == 0) 
				return false;
		}
		return true;
	}

	private void removeFilledRows() {
		for(int i = 0; i < board.getRows(); i++) {
			if(isRowFilled(i)) {
				this.addOperation(Operations.DELETE_ROW, (Object) board.getRow(i));
				this.board.deleteRow(i);
				this.score += 10;
			}
		}
	}

	private void undeleteBoardRow(BoardRowWrapper row) {
		this.board.undeleteRow(row);
	} 

	public boolean reverseRotateShape() {
		this.activeShape.rotateLeft();
		return true;
	}

	public boolean rotateShape() {
		this.activeShape.rotateRight();
		Point []points = this.activeShape.getPoints();
		int potentialX = this.activeShape.getPos().getX(), potentialY = this.activeShape.getPos().getY();
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

			if(this.board.get(x, y) != 0) {
				this.activeShape.rotateLeft();
				return false;
			}
		}
		this.addOperation(Operations.ROTATE_SHAPE);
		return true;
	}

	public void stop() {
		this.stopped = true;
		this.pause();
	}

	public boolean isStopped() {
		return this.stopped;
	}

	public void setNewShape() {
		this.activeShape = Shape.getRandomShape();
		this.activeShape.setPos(new Point(2, 6));
		Point []points = this.activeShape.getPoints();
		int potentialX = this.activeShape.getPos().getX(), potentialY = this.activeShape.getPos().getY();
		for(Point pos : points) {
			if(this.board.get(potentialX + pos.getX(), potentialY + pos.getY()) != 0) {
				this.stop();
				return;
			}
		}
		this.addOperation(Operations.SET_NEW_SHAPE, (Object) this.activeShape);
	}

	public void pasteShape() {
		Point []points = this.activeShape.getPoints();
		for(Point p : points) {
			this.board.set(p.getX() + this.activeShape.getPos().getX(), p.getY() + this.activeShape.getPos().getY(), this.activeShape.getColorIndex());
		}
		this.addOperation(Operations.PASTE_SHAPE, (Object) this.activeShape);
	}

	public boolean moveShapeLeft() {
		int potentialX = this.activeShape.getPos().getX();
		int potentialY = this.activeShape.getPos().getY() - 1;
		Point []points = this.activeShape.getPoints();
		int res;

		for(Point pos : points) {
			res = pos.getY() + potentialY;
			if(res < 0 || res >= this.board.getCols())
				return false;
			if(this.board.get(potentialX + pos.getX(), res) != 0)
				return false;
		}
		this.activeShape.getPos().setY(potentialY);
		this.addOperation(Operations.MOVE_SHAPE_LEFT);
		return true;
	}

	public boolean moveShapeUp() {
		int potentialX = this.activeShape.getPos().getX() - 1;
		this.activeShape.getPos().setX(potentialX);
		return true;
	}

	public boolean moveShapeRight() {
		int potentialX = this.activeShape.getPos().getX();
		int potentialY = this.activeShape.getPos().getY() + 1;

		Point []points = this.activeShape.getPoints();
		int res;

		for(Point pos : points) {
			res = pos.getY() + potentialY;
			if(res < 0 || res >= this.board.getCols())
				return false;
			if(this.board.get(potentialX + pos.getX(), res) != 0)
				return false;
		}
		this.activeShape.getPos().setY(potentialY);
		this.addOperation(Operations.MOVE_SHAPE_RIGHT);
		return true;
	}

	public boolean moveShapeDown(){
		int potentialX = this.activeShape.getPos().getX() + 1;
		int potentialY = this.activeShape.getPos().getY();
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
			if(this.board.get(res, potentialY + pos.getY()) != 0) {
				this.pasteShape();
				this.removeFilledRows();
				this.setNewShape();
				return false;
			}
		}
		this.activeShape.getPos().setX(potentialX);
		this.addOperation(Operations.MOVE_SHAPE_DOWN);
		return true;
	}

	public void clearShape() {
		Point []points = this.activeShape.getPoints();
		for(Point p : points) {
			this.board.set(p.getX() + this.activeShape.getPos().getX(), p.getY() + this.activeShape.getPos().getY(), 0);			
		}
	}

	public Game() {
		this.activeShape = Shape.getRandomShape();
		this.addOperation(Operations.SET_NEW_SHAPE, this.activeShape);
		this.activeShape.setPos(new Point(2, 6));
		this.board = new Board(); 
		this.score = 0;
	}

	private void addOperation(Operations op, Object val) {
		undoStack.push(new Operation(op, val));
	}

	private void addOperation(Operations op) {
		undoStack.push(new Operation(op));
	}

	public static void main(String args[]) {

	}
}