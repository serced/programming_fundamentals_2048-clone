import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


/**
 * This class implements the mainframe of the 2048 game implemented in the Board Class.
 *
 * @author Maria Kolyvaki and Severin
 * @version 20.05.2020
 */
public class MainFrame extends JFrame /*implements KeyListener*/{
    
    private final Board game;

    /**
     * Create a new MainFrame operating on the given game model.
     * @param game The game model of this application
     */
    public MainFrame(final Board game) {
        // initialize instance variables
        this.game = game;
        // operation performed when we close the frame 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // setup of game board GUI in the center
        GameSquare gameSquare = new GameSquare(game);
        this.add(gameSquare, BorderLayout.CENTER);
        
        // buttons panel
        JPanel buttons = new JPanel();
        JButton refresh = new JButton("↺");
        JButton right = new JButton("→");
        JButton left = new JButton("←");
        JButton up = new JButton("↑");
        JButton down = new JButton("↓");
        buttons.add(refresh);
        buttons.add(right);
        buttons.add(left);
        buttons.add(up);
        buttons.add(down);
        this.add(buttons, BorderLayout.SOUTH);
        this.pack();
        
        
        // register listeners to corresponding buttons
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.out.println("Refresh clicked");
                //game.undo();
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
        
    }
    
    
}

