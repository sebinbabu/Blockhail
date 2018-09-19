package wittybox.Tetrax;

import wittybox.Tetrax.*;
import java.io.IOException;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Game {
	private Shape activeShape = null;
	private int activeShapeX = 0;
	private int activeShapeY = 0;
	private Board board = null;
	private DefaultTerminalFactory terminalFactory = null;
	private Terminal terminal = null;

	public void updateBoard() {
		this.activeShapeX = 5;
		this.activeShapeY = 5;
		Point []points = this.activeShape.getPoints();
		for(Point p : points) {
			this.board.set(p.getX() + activeShapeX, p.getY() + activeShapeY, true);
		}
	}

	public void displayBoard() throws IOException {
		board.display();
	}

	public Game() throws IOException {
		this.terminalFactory = new DefaultTerminalFactory();
		this.terminal = terminalFactory.createTerminal();
		this.activeShape = Shapes.getRandomShape();
		this.board = new Board(terminal); 
	}

	public static void main(String args[]) {
		try {
			Game game = new Game();
			game.updateBoard();
			game.displayBoard();
			Thread.sleep(3000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}