package wittybox.Tetrax;

import wittybox.Tetrax.*;
import java.io.IOException;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Board {
	private boolean [][]matrix;
	private int rows = 20;
	private int cols = 16;

	private Terminal terminal;

	public void display() throws IOException {
		this.terminal.clearScreen();
		int i, j;
		for(i = 0, this.cols += 2; i < this.cols; i++) {
			this.terminal.putCharacter('-');
		}
		this.terminal.putCharacter('\n');

		for(i = 0, this.cols -= 2; i < this.rows; i++) {
			this.terminal.putCharacter('|');
			for(j = 0; j < this.cols; j++) {
				if(matrix[i][j]) {
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
	}

	public int getRows() {
		return this.rows;
	}

	public int getCols() {
		return this.cols;
	}

	public void set(int x, int y, boolean val) {
		this.matrix[x][y] = val;
	}

	public boolean get(int x, int y) {
		return this.matrix[x][y];
	}

	public Board(Terminal terminal) throws IOException {
		this.rows = 20;
		this.cols = 16;
		this.matrix = new boolean[20][16];
        this.terminal = terminal;
	}

	public Board(int rows, int cols, Terminal terminal) throws IOException {
		this.rows = rows;
		this.cols = cols;
		this.matrix = new boolean[rows][cols];
        this.terminal = terminal;
	}

	public static void main(String args[]) {
		try {
			DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
			Terminal terminal = terminalFactory.createTerminal();
			terminal.enterPrivateMode();

			Board b = new Board(terminal);
			b.display();
			Thread.sleep(3000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
 }