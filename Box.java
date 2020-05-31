
/**
 * This class should contain all the methods that handle a specific
 * box of our Game grid. Basically the entries of our Grid, which is going to 
 * be a 2-dimensional array, are going to be of Class type Box.
 * 
 * @author Maria Kolyvaki and Severin Husmann
 * @version final
 */
public class Box
{
    // instance variables 
    private int value;
    private final int row;
    private final int column;
    
    /**
     * Constructor for objects of class Block.
     * 
     * @param value The value of the box that should be created
     * @param row The row of where the box will be stored in the boxArray
     * @param column The column of where the box will be stored in the boxArray
     */
    public Box(final int value, final int row, final int column)
    {
        // initialise instance variables
        this.value = value;        
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the value field of the Box.
     *
     * @return  an integer representing the value
     */
    public int getValue()
    {
        return this.value;
    }
    
    /**
     * Returns the row field of the Box.
     *
     * @return  an integer representing the row
     */
    public int getRow()
    {
        return this.row;
    }
    
    /**
     * Returns the column field of the Box.
     *
     * @return  an integer representing the column
     */
    public int getColumn()
    {
        return this.column;
    }
    
    /**
     * Checks if two consecutives boxes on our grid that are 
     * consecutive with regards to a spesific row, have equals
     * values.
     *
     * @param  other  the other box
     * @return  a boolean value that corresponds 
     *          to the existence of equality
     */
    public boolean equal(final Box other)
    {
        return this.value == other.value;
    }
    
    /**
     * Check whether two boxes are equal either in a row
     * or a column.
     * 
     * @param other The other box with which we want 
     *      to compare whether it can be merged.
     * @return true if they can be merged
     */
    public boolean canMerge(final Box other)
    {
        return this.equal(other);
    }
    
    /**
     * Doubles the value of the current box if it can be merged with the other one.
     *
     * @param  other  the other box
     * @return Returns the current box from which we doubled the value.
     */
    public Box merge(final Box other) {
        if (this.canMerge(other)) {
            this.value += other.value;
            return this;
        }
        return null;
    }
}
