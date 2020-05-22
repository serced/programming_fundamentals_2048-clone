import javax.swing.JComponent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
/**
* Write a description of class SquaredModel here.
*
* @author (your name)
* @version (a version number or a date)
*/
public class SquaredModel extends JComponent {
    // instance variables 
    static int SCALE = 100;
    static int BORDER = SCALE / 20; 
    static int FONT_SIZE = (int)(SCALE * 0.4);
    static Font FONT = new Font("Consolas", Font.PLAIN, FONT_SIZE);
    private int value;
    
    /**
    * Constructor for objects of class SquaredModel
    */
    public SquaredModel(int value)
    {
        this.value = value;
        this.setFont(FONT);
        setPreferredSize(new Dimension(SCALE, SCALE));
    }
    
    /**
    * Get the value of the Square.
    * @return the value
    */
    public int getValue() {
        return this.value;
    }
    
    /**
    * Set the value of the Square.
    * @param other the other value
    */
    public void setValue(int other) {
        this.value = other; 
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(getValue(), getValue() ,getWidth(), getHeight());
        g.fillRoundRect(getValue(), getValue() ,getWidth(), getHeight(), SCALE / 3, SCALE / 3);
        Color color;
        if (value == 0) {
            color = Color.ORANGE;
        } else {
             int len = Integer.numberOfTrailingZeros(value);
             color = Color.getHSBColor(len / 11.0f, 0.8f, 0.5f);
        }
        g.setColor(color);
        setForeground(Color.GRAY);
        
    }
}
