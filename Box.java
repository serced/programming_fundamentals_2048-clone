
/**
 * This class should contain all the methods that handle a specific
 * box of our Game grid. Basically the entries of our Grid, which is going to 
 * be a 2-dimensional array, are going to be of Class type Box.
 * 
 * @ Author Maria Kolyvaki and Severin Husmann
 * @ version 1
 */
public class Box
{
    // instance variables 
    private final int value;
    private int row;
    private int column;

    /**
     * Constructor for objects of class Block
     */
    public Box()
    {
        // initialise instance variables
        this.value = 0;
        this.row = 0;
        this.column = 0;
    }
    
    /**
     * Constructor for objects of class Block
     */
    public Box(int value, int row, int column)
    {
        // initialise instance variables
        this.value = 0;        
        this.row = row;
        this.column = column;
    }
    
    /**
     * Constructor for objects of class Block
     */
    public Box(int init)
    {
        // initialise instance variables
        this.value = init;
    }

    /**
     * Returns the value field of the Box
     *
     * @return  an integer representing the value
     */
    public int getValue()
    {
        return this.value;
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
    public boolean equal(Box other)
    {
        if (this.value == other.value) {
            return true;
        } else {
            return false;
        }
    }
    
}
