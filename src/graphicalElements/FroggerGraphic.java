package graphicalElements;

import gameCommons.IFrog;
import util.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private final ArrayList<Element> elementsToDisplay;

	public final int pixelByCase;
	private final int height;
	private final boolean twoPlayers;

	private IFrog frog;
	private IFrog frog2;

	private final JFrame frame;
	private final Image background;

	public static Image frogImage;
	public static Image carLtoRImage;
	public static Image carRtoLImage;
	public static Image truckLtoRImage;
	public static Image truckRtoLImage;
	public static Image surpriseImage;


	public FroggerGraphic(int width, int height, int pixelByCase, boolean twoPlayers) throws IOException {
		this.height = height;
		this.pixelByCase = pixelByCase;
		this.twoPlayers = twoPlayers;

		elementsToDisplay = new ArrayList<>();

		//Chargement des ressources graphiques nécessaires au jeu
		frogImage =  ImageIO.read(new File("ressources","frog.png"));
		carLtoRImage =  ImageIO.read(new File("ressources","carL_R.png"));
		carRtoLImage =  ImageIO.read(new File("ressources","carR_L.png"));
		truckLtoRImage =  ImageIO.read(new File("ressources","truckL_R.png"));
		truckRtoLImage =  ImageIO.read(new File("ressources","truckR_L.png"));
		surpriseImage = ImageIO.read(new File("ressources","surprise.png"));

		//Mise à l échelle des ressources en fonction de la taille de la fenêtre
		frogImage = frogImage.getScaledInstance(pixelByCase, pixelByCase, Image.SCALE_SMOOTH);
		carLtoRImage = carLtoRImage.getScaledInstance(2 * pixelByCase, pixelByCase, Image.SCALE_SMOOTH);
		carRtoLImage = carRtoLImage.getScaledInstance(2 * pixelByCase, pixelByCase, Image.SCALE_SMOOTH);
		truckLtoRImage = truckLtoRImage.getScaledInstance(3 * pixelByCase, pixelByCase, Image.SCALE_SMOOTH);
		truckRtoLImage = truckRtoLImage.getScaledInstance(3 * pixelByCase, pixelByCase, Image.SCALE_SMOOTH);
		surpriseImage = surpriseImage.getScaledInstance(pixelByCase, pixelByCase, Image.SCALE_SMOOTH);

		BufferedImage background = new BufferedImage(width * pixelByCase,height * pixelByCase,BufferedImage.TYPE_INT_ARGB);
		Image road = ImageIO.read(new File("ressources","road.png"));
		road = road.getScaledInstance( width * pixelByCase, pixelByCase, BufferedImage.SCALE_SMOOTH);
		Graphics2D g = background.createGraphics();
		for (int i = 0; i < (height); i++){
			g.drawImage(road, 0, i * pixelByCase, null);
		}
		g.dispose();
		this.background = background;


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
			break;
		}
		if (twoPlayers){
			switch (e.getKeyCode()) {
				case KeyEvent.VK_Z:
					frog2.move(Direction.up);
					break;
				case KeyEvent.VK_S:
					frog2.move(Direction.down);
					break;
				case KeyEvent.VK_Q:
					frog2.move(Direction.left);
					break;
				case KeyEvent.VK_D:
					frog2.move(Direction.right);
					break;
			}
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
	public void setFrog2(IFrog frog) {
		this.frog2 = frog;
	}

	public void endGameScreen(String s, String clk) {
		frame.remove(this);

		frame.setLayout(new BorderLayout());

		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", Font.BOLD, 20));

		JLabel clock= new JLabel("Time : " + clk);
		clock.setFont(new Font("Verdana", Font.BOLD, 20));


		GridBagConstraints gbc = new GridBagConstraints();
		JPanel panel = new JPanel(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(label,gbc);
		gbc.gridy = 1;
		panel.add(clock,gbc);

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.repaint();
	}

	public void endGameScreen(String s, String bestTime, String clk) {
		frame.remove(this);

		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());

		JLabel clock= new JLabel("Time : " + clk);
		clock.setFont(new Font("Verdana", Font.BOLD, 20));
		clock.setHorizontalAlignment(JLabel.CENTER);
		clock.setVerticalAlignment(JLabel.CENTER);
		clock.setLocation(0,25);
		clock.setSize(this.getSize());

		JLabel bestTimeLabel= new JLabel("Best time : " + bestTime);
		bestTimeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		bestTimeLabel.setHorizontalAlignment(JLabel.CENTER);
		bestTimeLabel.setVerticalAlignment(JLabel.CENTER);
		bestTimeLabel.setLocation(0,50);
		bestTimeLabel.setSize(this.getSize());


		frame.getContentPane().add(label);
		frame.getContentPane().add(clock);
		frame.getContentPane().add(bestTimeLabel);

		frame.repaint();
	}

	public void endGameScreen(String s, int sc, int highSE, String clk) {
		frame.remove(this);

		JLabel statement = new JLabel(s);
		statement.setFont(new Font("Verdana", Font.BOLD, 20));
		statement.setHorizontalAlignment(SwingConstants.CENTER);
		statement.setSize(this.getSize());

		JLabel score= new JLabel("Your Score: "+ (sc-1));
		score.setFont(new Font("Verdana", Font.BOLD, 20));
		score.setHorizontalAlignment(0);
		score.setVerticalAlignment(JLabel.NORTH);
		score.setSize(this.getSize());

		JLabel maxScoreEver= new JLabel("Highest Score: "+ (highSE-1));
		maxScoreEver.setFont(new Font("Verdana", Font.BOLD, 20));
		maxScoreEver.setHorizontalAlignment(0);
		maxScoreEver.setVerticalAlignment(JLabel.BOTTOM);
		maxScoreEver.setSize(this.getSize());


		JLabel clock= new JLabel("Time : " + clk);
		clock.setFont(new Font("Verdana", Font.BOLD, 20));
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
