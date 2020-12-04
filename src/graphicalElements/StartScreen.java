package graphicalElements;


import gameCommons.Game;
import gameCommons.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartScreen extends JFrame {

    public static boolean gamemodeInf;
    public static boolean startScreen = true;
    private JFrame frame;

    public StartScreen(int width, int height) throws IOException {


        this.frame = this;
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Frogger");

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JButton infiniteGamemode = new JButton("Infinite");
        infiniteGamemode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamemodeInf = true;
                frame.dispose();
                startScreen = false;

            }
        });

        JButton finiteGamemode = new JButton("Finite");
        finiteGamemode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamemodeInf = false;
                frame.dispose();
                startScreen = false;

            }
        });


        Image froggerLogo =  ImageIO.read(new File("ressources","frogger.png"));
        int scaledWidth = (int) ((FroggerGraphic.pixelByCase / 32.0) * froggerLogo.getWidth(null));
        int scaledHeight = (int) ((FroggerGraphic.pixelByCase / 32.0) * froggerLogo.getHeight(null));
        froggerLogo = froggerLogo.getScaledInstance(scaledWidth,  scaledWidth, Image.SCALE_SMOOTH);
        JLabel froggerImage = new JLabel(new ImageIcon(froggerLogo));


        gbc.gridx = 0;
        contentPane.add(infiniteGamemode, gbc);

        gbc.gridx = 2;
        contentPane.add(finiteGamemode, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        contentPane.add(froggerImage, gbc);

        frame.setContentPane(contentPane);
        frame.setSize(width*FroggerGraphic.pixelByCase, height*FroggerGraphic.pixelByCase);
        frame.setVisible(true);


    }
}
