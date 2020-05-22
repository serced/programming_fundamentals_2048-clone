
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Write a description of class BoxUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoxUI extends JComponent {
    private final Board game;
    private final int row;
    private final int column;

    /**
     * Constructor for objects of class BoxUI
     */
    public BoxUI(final Board game, final int row, final int column)
    {
        // initialise instance variables
        this.game = game;
        this.row = row;
        this.column = column;
        
        game.addBoardListener(new BoardListener() {
            public void boardChanged(Board game) {
                repaint();
            }
        });
    }
    
    public void paintComponent(Graphics g) {
        // if value is not 0 paint the current value in a box
        if (game.getState()[row][column].getValue() != 0) {
            g.setColor(Color.ORANGE);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            final int centerWidth = getWidth() / 2;
            final int centerHeight = getHeight() / 2;
            g.setColor(Color.BLACK);
            
            g.drawString(Integer.toString(game.getState()[row][column].getValue()), 
                centerWidth, centerHeight);
        } else {
            // otherwise paint a gray box
            g.setColor(Color.GRAY);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            final int centerWidth = getWidth() / 2;
            final int centerHeight = getHeight() / 2;
        }
    }

}
