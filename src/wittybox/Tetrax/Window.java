package wittybox.Tetrax;

import wittybox.Tetrax.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FontMetrics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel implements KeyListener {
	JFrame window = null;
	Game game = null;

	private Font bigFont = new Font("Arial", java.awt.Font.BOLD, 18);

	public void tick() {
		if(!this.game.isPaused()) {
			this.game.moveShapeDown();
			this.render();
		}
	}

	public Window() {
		this.game = new Game();
		this.window = new JFrame("Tetrax");
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
		Color color;
		int i, j;
		if(board == null)
			return;
		int cols = board.getCols(), rows = board.getRows();
		for(i = 0; i < rows; i++) {
			for(j = 0; j < cols; j++) {
				color = board.get(i, j);
				if(color != null)
					g.setColor(color);
				else
					g.setColor(Color.BLACK);
        		g.fillRect(j * 20 + 30, i * 20 + 30, 20, 20);
			}
		}

		Point []points = this.game.getActiveShape().getPoints();
		int posX = this.game.getActiveShapePos().getX(), posY = this.game.getActiveShapePos().getY(); 
		g.setColor(this.game.getActiveShape().getColor());
		for(Point p : points) {
			g.fillRect((posY + p.getY()) * 20 + 30, (posX + p.getX()) * 20 + 30, 20, 20);
		}

		String text = "Score: " + this.game.getScore();
		g.setColor(Color.WHITE);
        g.drawString(text, 20, this.game.getBoard().getRows() * 20 + 50);

		if(this.game.isPaused()) {
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
			this.game.moveShapeDown();
		} else if (code == KeyEvent.VK_UP) {
			this.game.rotateShape();
		} else if (code == KeyEvent.VK_LEFT) {
			this.game.moveShapeLeft();
		} else if (code == KeyEvent.VK_RIGHT) {
			this.game.moveShapeRight();
		} else if(e.getKeyChar() == 'u') {
			this.game.undo();
		} else if(e.getKeyChar() == 'i') {
			this.game.redo();
		} else if(e.getKeyChar() == 'q') {
			System.exit(0);
		}else if(e.getKeyChar() == 's') {
			if(this.game.isPaused()) {
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
    	Window w = new Window();
    	Timer t = new Timer();
		t.schedule(new TimerTask() {
	    	@Override
	    	public void run() {
	       		w.tick();
	    	}
		}, 0, 500);
    }

}