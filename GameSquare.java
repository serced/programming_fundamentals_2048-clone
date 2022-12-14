import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * Write a description of class GameSquare here.
 *
 * @author Maria Kolyvaki and Severin Husmann
 * @version final
 */
public class GameSquare extends JComponent {
    private Board game;
    
    /**
     * Constructor for objects of class GameSquare.
     * 
     * @param game the Board instance we will work with
     */
    public GameSquare(final Board game) {
        super();
        this.game = game;
        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new GridLayout(4, 4, 5, 5));
        final Box[][] gameGrid = game.getState();
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
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        g.setColor(new Color(0xBBADA0));
        g.fillRect(0, 0, getWidth(), getHeight());
        if (this.game.isGameOver()) {
            gameOver();
        }
    }
    
    /**
     * Method responsible for enabling the teleoperation of the keyboard of the game.
     * 
     */
    private void registerKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                // invoked when a key has been pressed
                switch (e.getKeyCode()) {
                    // left arrow from keyboard
                    case KeyEvent.VK_LEFT:
                        game.swipeToDirection(SwipeDirection.LEFT);
                        break;
                    // right arrow from keyboard
                    case KeyEvent.VK_RIGHT:
                        game.swipeToDirection(SwipeDirection.RIGHT);
                        break;
                    // up arrow from keyboard
                    case KeyEvent.VK_UP:
                        game.swipeToDirection(SwipeDirection.UP);
                        break;
                    // down arrow from keyboard
                    case KeyEvent.VK_DOWN:
                        game.swipeToDirection(SwipeDirection.DOWN);
                        break;
                    // backspace for undo from keyboard
                    case KeyEvent.VK_BACK_SPACE:
                        game.undo();
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
    
    private void gameOver() {
        final String[] options = new String[]{"New Game", "Exit"};
        final int result = JOptionPane.showOptionDialog(this,"Game over.\n","Game Over!", 
                                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                                        null, options,options[0]);
        if (result == JOptionPane.YES_OPTION) {
            newGame();
        } else {
            System.exit(0);
        }
    }
    
    private void newGame() {
        game = new Board();
        //JOptionPane in this case should be closed automatically and we cannot close them
        // form the object that fires them 
    }
}
