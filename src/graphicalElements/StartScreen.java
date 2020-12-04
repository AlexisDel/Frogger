package graphicalElements;


import gameCommons.Game;
import gameCommons.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {

    public static boolean gamemodeInf;
    public static boolean startScreen = true;
    private JFrame frame;

    public StartScreen(int width, int height) {


        this.frame = this;
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Frogger");

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JButton infiniteGamemode = new JButton("Infinite Gamemode");
        infiniteGamemode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamemodeInf = true;
                frame.dispose();
                startScreen = false;

            }
        });

        JButton finiteGamemode = new JButton("Finite Gamemode");
        finiteGamemode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamemodeInf = false;
                frame.dispose();
                startScreen = false;

            }
        });

        JLabel froggerImage = new JLabel(new ImageIcon("ressources/frogger.png"));

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
