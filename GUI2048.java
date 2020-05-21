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
 * Write a description of class GraphicalUserInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUI2048 extends JFrame {

    private final Model model;
    JPanel panelMain;
    
    /**
     * Create a new GUI operating on the given Model.
     * @param model The model of this application
     */
    public GUI2048(final Model model) {
        this.model = model;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        
        JPanel panelMain = new JPanel();
        panelMain.setBackground(Color.ORANGE);  
        frame.add(panelMain);
        
        frame.pack();
        JPanel panelGame = new JPanel();
        panelMain.add(panelGame);
        
        JButton refresh = new JButton("↺");
        JButton right = new JButton("→");
        JButton left = new JButton("←");
        JButton up = new JButton("↑");
        JButton down = new JButton("↓");
        panelGame.add(refresh);
        panelGame.add(right);
        panelGame.add(left);
        panelGame.add(up);
        panelGame.add(down);
        pack();
        frame.setVisible(true);
        
        refresh.addActionListener(new RefreshListener() {
            public void actionPerformed(ActionEvent ev) {
                System.out.println("Refresh clicked");
            }
        });
        right.addActionListener(new RightListener() {
            public void actionPerformed(ActionEvent ev) {
                System.out.println("right swipe clicked");
            }
        });
        left.addActionListener(new LeftListener() {
            public void actionPerformed(ActionEvent ev) {
                System.out.println("left swipe clicked");
            }
        });
        up.addActionListener(new UpListener() {
            public void actionPerformed(ActionEvent ev) {
                System.out.println("up swipe clicked");
            }
        });
        down.addActionListener(new DownListener() {
            public void actionPerformed(ActionEvent ev) {
                System.out.println("down swipe clicked");
            }
        });
            
            
    }
    
}

