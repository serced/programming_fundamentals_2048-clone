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
import java.awt.geom.Rectangle2D;

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
        // set the vertical and horizontal size of the frame
        this.setPreferredSize(new Dimension(400, 400));
        
        // set square layout of the squares
        this.setLayout(new GridLayout(4, 4, 5, 5));
        
        Box[][] gameGrid = game.getState();
        // fill up the game with the corrsponding object of BoxUI type
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[0].length; j++) {
                final BoxUI box = new BoxUI(game, i, j);
                this.add(box);
            }
        }
        // we need a key binding technique in order to 
        // gain control after pressing ActionListener JButton
        setFocusable(true); 
        registerKeyListener();
        // enableEvents(AWTEvent.KEY_EVENT_MASK);
    }
    
    public void paintComponent(final Graphics g) {
        g.setColor(new Color(0xBBADA0));
        g.fillRect(0, 0, getWidth(), getHeight());
        //final Painter painter = new Painter(g, 400);
        if (game.isGameOver() == true ) {
            //System.out.println("Game Over mode");
            g.setColor(Color.BLACK);
            final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
            final FontMetrics fm = g.getFontMetrics(font);
            final Rectangle2D textsize = fm.getStringBounds("Game over", g);
            g.drawString("Game over", 400, 400);
            // option dialog for the Game over
            //TO DO, I get here but I cannot draw the text
        }
    }

    private void registerKeyListener() {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(final KeyEvent e) {
                    // invoked when a key has been pressed
                    //System.out.println("I am the keyboard");
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
                            //game.undo();
                            break;
                        default:
                            // do nothing
                            break;
                    }
                    repaint();
                    // we should call that instead of the paintComponent
                }
            });
    }
}
