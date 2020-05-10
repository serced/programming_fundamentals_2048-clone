import java.util.Random;
import java.util.LinkedList;
import java.util.ArrayList;
/**
 * This class should contain the main grid of our Game and 
 * all the methods that handle a specific
 * box of our Game grid. Basically the entries of our Grid, which is going to 
 * be a 2-dimensional array, are going to be of Class type Box.
 * 
 * @ Author Maria Kolyvaki and Severin Husmann
 * @ version 1
 */
public class Board
{
    // instance variables - replace the example below with your own
    private Box[][] grid;
    
    /**
     * Constructor for objects of class Board.
     */
    public Board(int dim)
    {
        // initialise instance variables
        grid = new Box[dim][dim];
    }
    
    /**
     * Constructor that creates a board from a 2-dimensional array.
     * Maybe we want this method for contsucting the board again in case
     * that we call undo() method because we are going to save an array (2d)
     * of the previous state
     */
    public Board(Box[][] array)
    {
        // initialise instance variables
        grid = array;
    }
  
    /**
     * Method that refreshes the Game to its initial position
     * where we have 
     *
     */
    public void refresh()
    {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Box();
            }
        }
        // call the random generator here
    }
    
    /**
     * Method that swipes up the current configuration 
     * of the Game
     * 
     */
    public void swipeUp()
    {
        // TODO
    }
    
    /**
     * Method that swipes down the current configuration 
     * of the Game
     * 
     */
    public void swipeDown()
    {
        // TODO
    }
    
    /**
     * Method that swipes left the current configuration 
     * of the Game
     * 
     */
    public void swipeLeft()
    {
        // TODO
    }
    
    /**
     * Method that swipes right the current configuration 
     * of the Game
     * 
     */
    public void swipeRight()
    {
        // TODO
    }
    
    /**
     * // TODO explain thoroughly
     * 
     */
    public void isValidMove()
    {
        // TODO
    }
    
    /**
     * In case that 2 cnsecutive boxes are equal either in a row
     * or a cillumn then they have to get merged and form on box that
     * contains the result of their addition.
     * 
     * @ return the merged Box
     */
    public Box merge()
    {
        // TODO
        return new Box();
    }
    
    /**
     * Method that returns a list consisting of the 
     * points of the grid that are empty
     *
     * @return the list of Boxes
     */
    public ArrayList<Box> emptyBoxPosition()
    {
        ArrayList<Box> empty = new ArrayList<Box>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getValue() == 0) {
                    empty.add(new Box(grid[i][j].getValue(), i,j));
                }
            }
        }
        return empty;
    }
       
    /**
     * Method that returns a list consisting of the 
     * points of the grid that are filled
     *
     * @return the list of Boxes
     */
    public ArrayList<Box> filledBoxPosition()
    {
        ArrayList<Box> filled = new ArrayList<Box>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getValue() != 0) {
                    filled.add(new Box(grid[i][j].getValue(), i,j));
                }
            }
        }
        return filled;
    }
    
    
    /**
     * Returns a new block within the grid in a position, 
     * that is valid (there has not block bigger than 0)
     *
     * @return  a boolean value that corresponds 
     *          to the existence of equality
     */
    public Box randomGenerator()
    {
        int rowTemp = 0;
        int colTemp = 0;
        ArrayList<Box> availPositions = emptyBoxPosition();
        // somehow we should generate over these positions randomly a new box of 2
        return new Box(2); //we have to return this
    }
}
