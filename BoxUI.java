
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

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
    
    final Color[] options = {
        new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3),
        new Color(0xffdac3), new Color(0xe7b08e), new Color(0xe7bf8e),
        new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
        new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710)
    };

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
        int value = game.getState()[row][column].getValue();
        if (game.getState()[row][column].getValue() != 0) {
            // change the color with respect to its value
            // basically from the properties of logarithm of base two we get 
            // the numbers in the corresponding options of colors
            g.setColor(options[(int) (Math.log(value) / Math.log(2)) + 1]);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            g.setColor(value < 128 ? options[0] : options[1]);
            final int centerWidth = getWidth() / 2;
            final int centerHeight = getHeight() / 2;
            // changes the color of the brush of the numbers to black
            g.setColor(Color.BLACK);
            // drows the string representation of the number with a centered position
            g.drawString(Integer.toString(game.getState()[row][column].getValue()), 
                centerWidth, centerHeight);
            
        } else {
            // otherwise paint a gray box
            g.setColor(new Color(0xCDC1B4));
            // define the boundaries of the box that we want to fill
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            if (game.isGameOver() == true ) {
                g.setColor(Color.BLACK);
                //g.drawString("Game over", 400, 400);
                
            }
        }
    }
        
}

