package graphicalElements;

import javax.imageio.ImageIO;
import javax.swing.*;
import gameCommons.IFrog;
import util.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private ArrayList<Element> elementsToDisplay;
	public static int pixelByCase = 32;
	private int width;
	private int height;
	private IFrog frog;
	private JFrame frame;

	private Image background;
	public static Image frogImage;
	public static Image carLtoRImage;
	public static Image carRtoLImage;
	public static Image truckLtoRImage;
	public static Image truckRtoLImage;


	public FroggerGraphic(int width, int height) throws IOException {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();

		this.background = ImageIO.read(new File("ressources","road.png"));
		this.frogImage =  ImageIO.read(new File("ressources","frog.png"));
		this.carLtoRImage =  ImageIO.read(new File("ressources","carL_R.png"));
		this.carRtoLImage =  ImageIO.read(new File("ressources","carR_L.png"));
		this.truckLtoRImage =  ImageIO.read(new File("ressources","truckL_R.png"));
		this.truckRtoLImage =  ImageIO.read(new File("ressources","truckR_L.png"));

		this.background = background.getScaledInstance((int) (FroggerGraphic.pixelByCase/32.0 * background.getWidth(null)), (int) (FroggerGraphic.pixelByCase / 32.0 * background.getHeight(null)), Image.SCALE_SMOOTH);
		this.frogImage = frogImage.getScaledInstance((int) (FroggerGraphic.pixelByCase/32.0 * frogImage.getWidth(null)), (int) (FroggerGraphic.pixelByCase / 32.0 * frogImage.getHeight(null)), Image.SCALE_SMOOTH);
		this.carLtoRImage = carLtoRImage.getScaledInstance((int) (FroggerGraphic.pixelByCase/32.0 * carLtoRImage.getWidth(null)), (int) (FroggerGraphic.pixelByCase / 32.0 * carLtoRImage.getHeight(null)), Image.SCALE_SMOOTH);
		this.carRtoLImage = carRtoLImage.getScaledInstance((int) (FroggerGraphic.pixelByCase/32.0 * carRtoLImage.getWidth(null)), (int) (FroggerGraphic.pixelByCase / 32.0 * carRtoLImage.getHeight(null)), Image.SCALE_SMOOTH);
		this.truckLtoRImage = truckLtoRImage.getScaledInstance((int) (FroggerGraphic.pixelByCase/32.0 * truckLtoRImage.getWidth(null)), (int) (FroggerGraphic.pixelByCase / 32.0 * truckLtoRImage.getHeight(null)), Image.SCALE_SMOOTH);
		this.truckRtoLImage = truckRtoLImage.getScaledInstance((int) (FroggerGraphic.pixelByCase/32.0 * truckRtoLImage.getWidth(null)), (int) (FroggerGraphic.pixelByCase / 32.0 * truckRtoLImage.getHeight(null)), Image.SCALE_SMOOTH);



		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
		for (Element e : elementsToDisplay) {
			if(e.isImage) {
				g.drawImage(e.image, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
			}
			else {
				g.setColor(e.color);
				g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
			}
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog.move(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			frog.move(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			frog.move(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			frog.move(Direction.right);
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public void endGameScreen(String s, String clk) {
		frame.remove(this);

		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());

		JLabel clock= new JLabel("Time : " + clk);
		clock.setFont(new Font("Verdana", 1, 20));
		clock.setHorizontalAlignment(JLabel.CENTER);
		clock.setVerticalAlignment(JLabel.CENTER);
		clock.setLocation(0,25);
		clock.setSize(this.getSize());


		frame.getContentPane().add(label);
		frame.getContentPane().add(clock);

		frame.repaint();

	}

	public void endGameScreen(String s, int sc, int highSE, String clk) {
		frame.remove(this);

		JLabel statement = new JLabel(s);
		statement.setFont(new Font("Verdana", 1, 20));
		statement.setHorizontalAlignment(SwingConstants.CENTER);
		statement.setSize(this.getSize());

		JLabel score= new JLabel("Your Score: "+ (sc-1));
		score.setFont(new Font("Verdana", 1, 20));
		score.setHorizontalAlignment(0);
		score.setVerticalAlignment(JLabel.NORTH);
		score.setSize(this.getSize());

		JLabel maxScoreEver= new JLabel("Highest Score: "+ (highSE-1));
		maxScoreEver.setFont(new Font("Verdana", 1, 20));
		maxScoreEver.setHorizontalAlignment(0);
		maxScoreEver.setVerticalAlignment(JLabel.BOTTOM);
		maxScoreEver.setSize(this.getSize());


		JLabel clock= new JLabel("Time : " + clk);
		clock.setFont(new Font("Verdana", 1, 20));
		clock.setHorizontalAlignment(0);
		clock.setVerticalAlignment(JLabel.NORTH);
		clock.setLocation(0,25);
		clock.setSize(this.getSize());


		frame.getContentPane().add(statement);
		frame.getContentPane().add(score);
		frame.getContentPane().add(maxScoreEver);
		frame.getContentPane().add(clock);

		frame.repaint();
	}
}
