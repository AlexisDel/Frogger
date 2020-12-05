package graphicalElements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class StartScreenGUI {

    public static boolean startScreen = true;
    public static boolean gamemodeInf = true;
    public static int gameWidth;
    public static int gameHeight;
    public static int pixelByCase;

    public StartScreenGUI() throws IOException {

        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());

        Image froggerLogoImage =  ImageIO.read(new File("ressources","frogger.png"));
        JLabel froggerLogo = new JLabel(new ImageIcon(froggerLogoImage));

        JLabel gamemodeLabel = new JLabel("Gamemode :");
        gamemodeLabel.setFont(new Font("Verdana", 1, 15));
        JButton infiniteGamemodeButton = new JButton("Infinite");
        infiniteGamemodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamemodeInf = true;
            }
        });
        JButton finiteGamemodeButton = new JButton("Finite");
        finiteGamemodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gamemodeInf = false;
            }
        });

        JLabel gameSizeLabel = new JLabel("Game size:");
        gameSizeLabel.setFont(new Font("Verdana", 1, 15));
        JTextField gameWidthText = new JTextField("26");
        gameWidthText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                gameWidthText.setText("");
            }
        });
        JTextField gameHeightText = new JTextField("20");
        gameHeightText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                gameHeightText.setText("");
            }
        });

        JLabel caseSizeLabel = new JLabel("Case size:");
        caseSizeLabel.setFont(new Font("Verdana", 1, 15));
        JTextField caseSizeText = new JTextField("32");
        caseSizeText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                caseSizeText.setText("");
            }
        });

        JPanel panelSetting = new JPanel();
        panelSetting.setLayout(new GridLayout(3,1));
        panelSetting.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panelSetting.add(gamemodeLabel);
        panelSetting.add(infiniteGamemodeButton);
        panelSetting.add(finiteGamemodeButton);
        panelSetting.add(gameSizeLabel);
        panelSetting.add(gameWidthText);
        panelSetting.add(gameHeightText);
        panelSetting.add(caseSizeLabel);
        panelSetting.add(caseSizeText);

        JButton playButton = new JButton("Jouer");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameWidth = Integer.parseInt(gameWidthText.getText());
                gameHeight = Integer.parseInt(gameHeightText.getText());
                pixelByCase = Integer.parseInt(caseSizeText.getText());
                startScreen = false;
                frame.dispose();
            }
        });

        frame.add(froggerLogo, BorderLayout.NORTH);
        frame.add(panelSetting, BorderLayout.CENTER);
        frame.add(playButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Frogger");
        frame.pack();
        frame.setVisible(true);
    }
}
