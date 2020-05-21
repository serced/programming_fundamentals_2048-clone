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
    
    /**
     * Create a new GUI operating on the given Model.
     * @param model The model of this application
     */
    public GUI2048(final Model model) {
        this.model = model;
        JPanel panel = new JPanel() ;
        JButton refresh = new JButton("↺");
        JButton right = new JButton("→");
        JButton left = new JButton("←");
        JButton up = new JButton("↑");
        JButton down = new JButton("↓");
        this.add(refresh);
        this.add(right);
        this.add(left);
        this.add(up);
        this.add(down);
        pack();
        setVisible(true);
        
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

