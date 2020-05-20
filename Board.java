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
 * @ version 16.05.2020
 */
public class Board
{
    // instance variables - replace the example below with your own
    
    private static final int SIZE = 4;
    private Box[][] grid;
    private SwipeDirection direction;
    
    /**
     * Constructor for objects of class Board.
     */
    public Board()
    {
        // initialise instance variables
        grid = new Box[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Box(0,i,j);
            }
        }
        // call random generator
        randomGenerator();
        randomGenerator();
        // why does this not work:
        // this = new Board(SIZE);
    }
    
    /**
     * Constructor for objects of class Board.
     */
    public Board(final int dim)
    {
        grid = new Box[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                grid[i][j] = new Box(0,i,j);
            }
        }
        // call random generator
        randomGenerator();
        randomGenerator();
    }
    
    /**
     * Constructor that creates a board from a 2-dimensional array.
     * Maybe we want this method for contsucting the board again in case
     * that we call undo() method because we are going to save an array (2d)
     * of the previous state
     */
    public Board(final Box[][] array)
    {
        this.grid = new Box[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                grid[j][i] = array[j][i];
            }
        }
    }
  
    /**
     * Method that refreshes the Game to its initial position
     * where we have 
     *
     */
    public Board refresh()
    {
        return new Board(SIZE);
        // call the random generator here
    }
    
    /**
     * Method that swipes up the current configuration 
     * of the Game
     * 
     */
    public void swipeUp()
    {
        rotateRight();
        swipeRight();
        rotateRight();
        rotateRight();
        rotateRight();
    }
    
    /**
     * Method that swipes down the current configuration 
     * of the Game
     * 
     */
    public void swipeDown()
    {
        rotateRight();
        rotateRight();
        rotateRight();
        swipeRight();
        rotateRight();
    }
    
    /**
     * Method that swipes left the current configuration 
     * of the Game
     * 
     */
    public void swipeLeft()
    {
        rotateRight();
        rotateRight();
        swipeRight();
        rotateRight();
        rotateRight();
    }
    
    /**
     * Method that rotates the grid right by 90 degrees
     * 
     */
    private void rotateRight()
    {
        // sofar method should only work when grid is squared
        // TODO for non-square implementation would be to assign a new Box[][] 
        final Box[][] oldBoard = copyBoxArray(grid);
        // loop over rows
        for (int j = 0; j < grid.length; j++) {
            // loop over columns
            for (int i = 0; i < grid[0].length; i++) {
                final int newColumn = grid[0].length - 1 - j;
                grid[i][newColumn] = new Box(oldBoard[j][i].getValue(), i, newColumn);
            }
        }
    }
    
    
    
    /**
     * Method that swipes right the current configuration 
     * of the Game
     * 
     */
    public void swipeRight()
    {
        // TODO check for game over
        // TODO fix the randomSpawnBox that it is only called when we were able to move/merge
        // move the whole board to the right, then merge, then move again to fill holes
        // then randomly spawn a new box in an empty slot
        final Box[][] oldBoard = copyBoxArray(grid);
        moveRight();
        mergeRight();
        moveRight();
        // should only be called when we were actually able to move/merge!
        // can we spawn only when oldBoard is not equal anymore?
        randomSpawnBox();
        
    }
    
    /**
     * Method to copy a given Box array to a new Box array.
     */
    private Box[][] copyBoxArray(final Box[][] oldBoard)
    {
        Box[][] newBoard = new Box[oldBoard.length][oldBoard[0].length];
        for (int i = 0; i < oldBoard.length; i++) {
            for (int j = 0; j < oldBoard[0].length; j++) {
                newBoard[i][j] = new Box(oldBoard[i][j].getValue(), i, j);
            }
        }
        return newBoard;
    }
    
    /**
     * Method that moves all Boxes as far right as possible without merging.
     * 
     */
    private void moveRight() {
        // make movement the number of column times, in order to move tile from fully left
        // to fully right, should everything else be empty
        for (int z = 0; z < grid[0].length; z++) {
            // loop over columns - 1 
            for (int i = 0; i < grid[0].length - 1; i++) {
                // loop over rows
                for (int j = 0; j < grid.length; j++) {
                    // now move when possible
                    final Box left = grid[j][i];
                    final Box right = grid[j][i + 1];
                    if (right.getValue() == 0 && left.getValue() != 0) {
                        grid[j][i + 1] = new Box(left.getValue(), j, i + 1);
                        // replacing it with empty cell such that next can also move
                        grid[j][i] = new Box(right.getValue(), j, i);
                    }
                }
            }
        }
    }
    
    /**
     * Method that merges all Boxes in the direction right at most once. 
     * 
     */
    private void mergeRight() {
        // for merging we have to start from the right (if we merge "right-ways")
        // otherwise we will have double merging
        // loop over columns
        for (int i = grid[0].length - 1; i > 0; i--) {
            // loop over rows
            for (int j = 0; j < grid.length; j++) {
                // now merge when possible
                final Box left = grid[j][i - 1];
                final Box right = grid[j][i];
                if (grid[j][i - 1].canMerge(grid[j][i])) {
                    grid[j][i] = grid[j][i-1].merge(grid[j][i]);
                    grid[j][i - 1] = new Box(0, j, i - 1);
                }
            }
        }
    }
    
    /**
     * Spawns a box in a random location.
     * 
     */
    private void randomSpawnBox() {
        final ArrayList<Box> emptyBoxes = emptyBoxPositions();
        if (emptyBoxPositions().size() != 0) {
            randomGenerator();
        }
    }
    
    
    /**
     * Method that swipes right the current configuration 
     * of the Game
     * 
     */
    public void swipeToDirection(SwipeDirection direction)
    {
        switch (direction) {
            case UP: 
                swipeUp();
                break;
            case DOWN:  
                swipeDown(); 
                break;
            case LEFT: 
                swipeLeft();
                break;
            case RIGHT:  
                swipeRight();
                break;
        }
    }
    
    /**
     * Checks whether the game is over or there are still possible moves.
     * 
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver()
    {
        // TODO
        // check whether there are empty boxes
        if (emptyBoxPositions().size() > 0) {
            return false;
        }
        // check whether one could swipe in one direction (move or merge)
        final Box[][] currentBoard = getState();
        boolean gameOver = true;
        for (int i = 0; i < SwipeDirection.values().length; i++) {
            swipeToDirection(SwipeDirection.values()[i]);
            // grid will be a possible future Board
            // if grid == currentBoard return True
            if (!areBoxArraysEqual(currentBoard, grid)) {
                grid = copyBoxArray(currentBoard);
                gameOver = false;
            }
            // else reset board and go to next direction
            grid = copyBoxArray(currentBoard);
        }
        return gameOver;
    }
    
    
    /**
     * Method to check whether two box arrays are equal.
     * 
     * @return True if the given box arrays are equal
     */
    private boolean areBoxArraysEqual(final Box[][] currentBoard, 
        final Box[][] futureBoard)
    {
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[0].length; j++) {
                // not sure if the second/third conditions add value
                // theoretically they should be the same already
                // currentBoard[i][j].isEqual(futureBoard[i][j]);
                if (currentBoard[i][j].getValue() != futureBoard[i][j].getValue() 
                    && currentBoard[i][j].getRow() == futureBoard[i][j].getRow()
                    && currentBoard[i][j].getColumn() == futureBoard[i][j].getColumn()) {
                        return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Method that returns a list consisting of the 
     * points of the grid that are empty
     *
     * @return the list of Boxes
     */
    public ArrayList<Box> emptyBoxPositions()
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
    public ArrayList<Box> filledBoxPositions()
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
     */
    public void randomGenerator()
    {   
        ArrayList<Box> availPositions = this.emptyBoxPositions();
        if (!availPositions.isEmpty()) {
            int randomPosition = (int) (Math.random() * availPositions.size());
            Box temp = availPositions.get(randomPosition);
            grid[temp.getRow()][temp.getColumn()] = new Box(2, temp.getRow(),temp.getColumn());
        }
    }
    
    public Box[][] getState() {
        return grid;
    }
    
}