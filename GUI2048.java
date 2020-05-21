import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class implements the GUI of the 2048 game implemented in the Board Class.
 *
 * @author Maria Kolyvaki and Severin
 * @version 20.05.2020
 */
public class GUI2048 extends JFrame {

    private final Board game;
    private JPanel panelMain;
    private JPanel panelGame;
    /**
     * Create a new GUI operating on the given Model.
     * @param model The model of this application
     */
    public GUI2048(final Board game) {
        this.game = game;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel panelMain = new JPanel();
        panelMain.setBackground(Color.ORANGE);  
        frame.add(panelMain);
        
        BoxLayout box = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
        panelMain.setLayout(box);
        JPanel panelGame = new JPanel();
        panelMain.add(panelGame);
        panelMain.setPreferredSize(new Dimension(400, 400));
        JButton refresh = new JButton("↺");
        JButton right = new JButton("→");
        JButton left = new JButton("←");
        JButton up = new JButton("↑");
        JButton down = new JButton("↓");
        
        JPanel panelButtons = new JPanel();
        panelMain.add(panelButtons);
        panelButtons.setBackground(Color.ORANGE);  

        
        panelGame.setLayout(new GridLayout(4, 4));
        final Box[][] gameGrid = game.getState();
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[0].length; j++) {
                if (gameGrid[i][j].getValue() != 0) {
                    panelGame.add(new JLabel(Integer.toString(gameGrid[i][j].getValue())));
                } else {
                    panelGame.add(new JLabel(""));
                }
                
            }
        }



        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.out.println("Refresh clicked");
            }
        });
        right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.out.println("right clicked");
                game.swipeRight();
            }
        });
        left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                game.swipeLeft();
            }
        });
        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                game.swipeUp();
            }
        });
        down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                game.swipeDown();
            }
        }); 
        
        //panelButtons.setPreferredSize(new Dimension(100, 100));
        panelButtons.add(refresh);
        panelButtons.add(right);
        panelButtons.add(left);
        panelButtons.add(up);
        panelButtons.add(down);
        frame.pack();
        frame.setVisible(true);
    }
    
}

