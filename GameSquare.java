
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        this.game = game;
        
        game.addBoardListener(new BoardListener() {
            public void boardChanged(Board game) {
                repaint();
            }
        });
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
        
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }


}
