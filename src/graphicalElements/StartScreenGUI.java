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
    public static double density;

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

        JLabel difficultyLabel = new JLabel("Difficulty:");
        caseSizeLabel.setFont(new Font("Verdana", 1, 15));
        JComboBox difficultyBox = new JComboBox( new String[] {"Facile", "Moyen", "Difficile"} );

        JPanel panelSetting = new JPanel();
        panelSetting.setLayout(new GridLayout(4,3));
        panelSetting.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panelSetting.add(gamemodeLabel);
        panelSetting.add(infiniteGamemodeButton);
        panelSetting.add(finiteGamemodeButton);
        panelSetting.add(gameSizeLabel);
        panelSetting.add(gameWidthText);
        panelSetting.add(gameHeightText);
        panelSetting.add(caseSizeLabel);
        panelSetting.add(caseSizeText);
        panelSetting.add(new JLabel()); //Case vide (il y a surement une meilleur manière de faire)
        panelSetting.add(difficultyLabel);
        panelSetting.add(difficultyBox);

        JButton playButton = new JButton("Jouer");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameWidth = Integer.parseInt(gameWidthText.getText());
                gameHeight = Integer.parseInt(gameHeightText.getText());
                pixelByCase = Integer.parseInt(caseSizeText.getText());
                density = GetDensityFromDifficulty((String) difficultyBox.getSelectedItem());
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

    private double GetDensityFromDifficulty(String s){
        double density = 0.05;
        if (s.equals("Moyen")){
            return 0.1f;
        }
        else if (s.equals("Difficile")){
            return 0.2f;
        }
        return density;
    }
}