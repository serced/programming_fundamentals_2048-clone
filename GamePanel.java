import javax.swing.JPanel;
import java.awt.GridLayout;
/**
 * Write a description of class GamePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GamePanel extends JPanel
{
    // instance variables 
    private int rows;
    private int columns;
    private SquaredModel[][] nums;

    /**
     * Initialize method for the corresponding grid.
     *
     * @param  size of the grid
     */
    public void initialize(int size)
    {
        removeAll();
        columns = size;
        rows = size;
        setLayout(new GridLayout(rows, columns));
        nums = new SquaredModel[rows][columns];
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                nums[i][j] = new SquaredModel(0);
                add(nums[i][j]);
            }
        }
    }
    
    /**
    * Constructor for objects of class SquaredModel
    */
    public GamePanel(int size)
    {
        initialize(size);
    }
    
    /**
    * Get the value of the Square.
    * @return the value
    */
    public SquaredModel getValue(int row, int col) {
        return nums[row][col];
    }
    
    /**
    * Set the value of the Square.
    * @param other the other value
    */
    public void setValue(int row, int col, int value) {
        nums[row][col].setValue(value); 
    }
}
