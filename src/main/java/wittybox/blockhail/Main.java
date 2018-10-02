package wittybox.blockhail;

import wittybox.blockhail.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FontMetrics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements KeyListener {
	JFrame window = null;
	Game game = null;

	private Font bigFont = new Font("Arial", java.awt.Font.BOLD, 18);

	public void tick() {
		if(!this.game.isPaused() && !this.game.isStopped()) {
			this.game.moveShapeDown();
			this.render();
		}
	}

	public Main() {
		this.game = new Game();
		this.window = new JFrame("Blockhail");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setSize(this.game.getBoard().getCols() * 20 + 60, this.game.getBoard().getRows() * 20 + 100);
		this.window.setResizable(false);
		this.window.getContentPane().add(this);
		this.setBackground(Color.darkGray);
		this.setOpaque(true);
		this.window.setVisible(true);
		this.window.addKeyListener(this);
		this.render();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Board board = this.game.getBoard();
		int color;
		int i, j;
		if(board == null)
			return;
		int cols = board.getCols(), rows = board.getRows();
		for(i = 0; i < rows; i++) {
			for(j = 0; j < cols; j++) {
				color = board.get(i, j);
				if(color != 0)
					g.setColor(Shape.getColor(color));
				else
					g.setColor(Color.BLACK);
        		g.fillRect(j * 20 + 30, i * 20 + 30, 20, 20);
			}
		}

		if(!this.game.isStopped()) {
			Point []points = this.game.getActiveShape().getPoints();
			int posX = this.game.getActiveShapePos().getX(), posY = this.game.getActiveShapePos().getY(); 
			g.setColor(this.game.getActiveShape().getColor());
			for(Point p : points) {
				g.fillRect((posY + p.getY()) * 20 + 30, (posX + p.getX()) * 20 + 30, 20, 20);
			}
		}

		String text = "Score: " + this.game.getScore();
		g.setColor(Color.WHITE);
        g.drawString(text, 20, this.game.getBoard().getRows() * 20 + 50);

		if(this.game.isStopped()) {
			g.setFont(bigFont);
			this.drawStringMiddleOfPanel("GAME OVER", g);
		} else if(this.game.isPaused()) {
			g.setFont(bigFont);
			this.drawStringMiddleOfPanel("PAUSED", g);
		}
	}

	private void drawStringMiddleOfPanel(String string, Graphics g) {
        int stringWidth = 0;
        int stringAccent = 0;
        int xCoordinate = 0;
        int yCoordinate = 0;

        FontMetrics fm = g.getFontMetrics();
      
        stringWidth = fm.stringWidth(string);
        stringAccent = fm.getAscent();

        xCoordinate = getWidth() / 2 - stringWidth / 2;
        yCoordinate = getHeight() / 2 + stringAccent / 2;

        g.drawString(string, xCoordinate, yCoordinate);
	}

	public void render() {
		this.repaint();
	}

	@Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
		if (code == KeyEvent.VK_DOWN) {
			if(!this.game.isPaused())
				this.game.moveShapeDown();
		} else if (code == KeyEvent.VK_UP) {
			if(!this.game.isPaused())
				this.game.rotateShape();
		} else if (code == KeyEvent.VK_LEFT) {
			if(!this.game.isPaused())
				this.game.moveShapeLeft();
		} else if (code == KeyEvent.VK_RIGHT) {
			if(!this.game.isPaused())
				this.game.moveShapeRight();
		} else if(e.getKeyChar() == 'u') {
			if(!this.game.isStopped())
				this.game.pause();
				this.game.undo();
		} else if(e.getKeyChar() == 'i') {
			if(!this.game.isStopped())
				this.game.redo();
		} else if(e.getKeyChar() == 'q') {
			System.exit(0);
		} else if(e.getKeyChar() == 'z') {
			this.game.save();
		} else if(e.getKeyChar() == 'x') {
			this.game.load();
		} else if(e.getKeyChar() == 's') {
			if(this.game.isPaused() && !this.game.isStopped()) {
				this.game.resume();
			} else {
				this.game.pause();
			}
		}
		this.render();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String args[]) {
    	Main blockhail = new Main();
    	Timer t = new Timer();
		t.schedule(new TimerTask() {
	    	@Override
	    	public void run() {
	       		blockhail.tick();
	    	}
		}, 0, 500);
    }

}