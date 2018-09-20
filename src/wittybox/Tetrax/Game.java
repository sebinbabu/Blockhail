package wittybox.Tetrax;

import wittybox.Tetrax.*;
import java.io.IOException;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Game {
	private Shape activeShape = null;
	private Point activeShapePos = null;
	private Board board = null;
	private DefaultTerminalFactory terminalFactory = null;
	private Terminal terminal = null;

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

	private void pasteShape() {
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

	public boolean moveShapeDown() throws IOException{
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

	public KeyType getBlockingInput() throws IOException {
		return this.terminal.readInput().getKeyType();
	}

	public KeyType getNonBlockingInput() throws IOException {
		return this.terminal.pollInput().getKeyType();
	}

	private void clearShape() {
		Point []points = this.activeShape.getPoints();
		for(Point p : points) {
			this.board.set(p.getX() + this.activeShapePos.getX(), p.getY() + this.activeShapePos.getY(), false);			
		}
	}

	public void displayBoard() throws IOException {
		this.pasteShape();
		this.terminal.clearScreen();
		int i, j;
		for(i = 0, this.cols += 2; i < this.cols; i++) {
			this.terminal.putCharacter('-');
		}
		this.terminal.putCharacter('\n');

		for(i = 0, this.cols -= 2; i < this.rows; i++) {
			this.terminal.putCharacter('|');
			for(j = 0; j < this.cols; j++) {
				if(this.board.get(i, j)) {
					this.terminal.putCharacter('*');
				} else {
					this.terminal.putCharacter(' ');
				}
			}
			this.terminal.putCharacter('|');
			this.terminal.putCharacter('\n');
		}

		for(i = 0, this.cols += 2; i < this.cols; i++) {
			this.terminal.putCharacter('-');
		}
		this.cols -= 2;
		this.terminal.putCharacter('\n');
		this.terminal.flush();
		this.clearShape();
	}

	public Game() throws IOException {
		this.terminalFactory = new DefaultTerminalFactory();
		this.terminal = terminalFactory.createTerminal();
		//this.terminal.enterPrivateMode();
        this.terminal.setCursorVisible(false);
		
		this.activeShape = Shapes.getRandomShape();
		this.activeShapePos = new Point(3, 6);

		this.board = new Board(terminal); 
	}

	public static void main(String args[]) {
		try {
			Game game = new Game();
			KeyType key;

			while(true) {
				game.displayBoard();
				key = game.getBlockingInput();
				if(key == KeyType.ArrowUp) {
					game.rotateShape();
				} else if(key == KeyType.ArrowLeft) {
					game.moveShapeLeft();
				} else if(key == KeyType.ArrowRight) {
					game.moveShapeRight();
				} else if(key == KeyType.ArrowDown) {
					game.moveShapeDown();
				}

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}