import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * Write a description of class GameSquare here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameSquare extends JComponent {
    private final Board game;

    /**
     * Constructor for objects of class GameSquare
     */
    public GameSquare(Board game)
    {
        super();
        this.game = game;
        
        // game.addBoardListener(new BoardListener() {
            // public void boardChanged(Board game) {
                // repaint();
            // }
        // });
        this.setPreferredSize(new Dimension(400, 400));
        
        // set square layout of the squares
        this.setLayout(new GridLayout(4, 4, 5, 5));
        
        Box[][] gameGrid = game.getState();
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[0].length; j++) {
                final BoxUI box = new BoxUI(game, i, j);
                this.add(box);
            }
        }
        registerKeyListener();
        setFocusable(true); 
    }
    
    public void paintComponent(final Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void registerKeyListener() {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(final KeyEvent e) {
                    // invoked when a key has been pressed
                    System.out.println("I am the keyboard");
                    switch (e.getKeyCode()) {
                        // left arrow from keyboard
                        case KeyEvent.VK_LEFT:
                            game.swipeLeft();
                            break;
                        // right arrow from keyboard
                        case KeyEvent.VK_RIGHT:
                            game.swipeRight();
                            break;
                        // up arrow from keyboard
                        case KeyEvent.VK_UP:
                            game.swipeUp();
                            break;
                        // down arrow from keyboard
                        case KeyEvent.VK_DOWN:
                            game.swipeDown();
                            break;
                        // backspace for undo from keyboard
                        case KeyEvent.VK_BACK_SPACE:
                            //game.refresh();
                            break;
                        default:
                            // do nothing
                            break;
                    }
                    repaint();
                    //
                }
            });
    }
}
