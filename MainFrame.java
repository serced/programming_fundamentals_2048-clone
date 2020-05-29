import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class implements the mainframe of the 2048 game implemented in the Board Class.
 *
 * @author Maria Kolyvaki and Severin
 * @version 20.05.2020
 */
public class MainFrame extends JFrame {
    
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
                game.undo();
            }
        });
        right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                game.swipeToDirection(SwipeDirection.RIGHT);
            }
        });
        left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                game.swipeToDirection(SwipeDirection.LEFT);
            }
        });
        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                game.swipeToDirection(SwipeDirection.UP);
            }
        });
        down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                game.swipeToDirection(SwipeDirection.DOWN);
            }
        });         
    }
    
}

