import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Class that defines one box to "host" the Game.
 *
 * @author Maria Kolyvaki and Severin Husmann
 * @version final
 */
public class BoxUI extends JComponent {
    private final Board game;
    private final int row;
    private final int column;
    
    private final Color[] options = {
        new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3),
        new Color(0xffdac3), new Color(0xe7b08e), new Color(0xe7bf8e),
        new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
        new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710)
    };

    /**
     * Constructor for objects of class BoxUI.
     * 
     * @param game the Object of our Board class
     * @param row the row of the Board we are currently
     * @param column the column of the Board we are currently
     */
    public BoxUI(final Board game, final int row, final int column)
    {
        // initialise instance variables
        super();
        this.game = game;
        this.row = row;
        this.column = column;
        game.addBoardListener(new BoardListener() {
            public void boardChanged(final Board game) {
                repaint();
            }
        });
    }
    
    
    @Override
    public void paintComponent(final Graphics g) {
        // if value is not 0 paint the current value in a box
        final int value = game.getState()[row][column].getValue();
        if (game.getState()[row][column].getValue() != 0) {
            // change the color with respect to its value
            // basically from the properties of logarithm of base two we get 
            // the numbers in the corresponding options of colors
            g.setColor(options[(int) (Math.log(value) / Math.log(2)) + 1]);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
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
            
        }
    }
        
}

