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

        setTitle("Frogger");
        setSize(width*16, height*16);

        this.frame = this;

        JPanel panel = new JPanel(new GridBagLayout());

        JButton infiniteGamemode = new JButton("Infinite Gamemode");
        infiniteGamemode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamemodeInf = true;
                frame.dispose();
                startScreen = false;

            }
        });
        panel.add(infiniteGamemode, new GridBagConstraints());

        JButton finiteGamemode = new JButton("Finite Gamemode");
        finiteGamemode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamemodeInf = false;
                frame.dispose();
                startScreen = false;

            }
        });
        panel.add(finiteGamemode, new GridBagConstraints());

        JLabel froggerImage = new JLabel(new ImageIcon("ressources/frogger.png"));
        froggerImage.setVerticalAlignment(JLabel.NORTH);

        this.getContentPane().add(froggerImage);
        setVisible(true);
        this.getContentPane().add(panel);
        setVisible(true);
    }
}
