import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Objects; 
import java.util.Arrays; 
/**
 * This class should contain the main grid of our Game and 
 * all the methods that handle a specific
 * box of our Game grid. Basically the entries of our Grid, which is going to 
 * be a 2-dimensional array, are going to be of Class type Box.
 * 
 * @author Maria Kolyvaki and Severin Husmann
 * @version 20.05.2020
 */
public class Board
{
    private static final int SIZE = 4;
    private Box[][] grid;
    private ArrayList<BoardListener> listeners;
    
    /**
     * Constructor for objects of class Board.
     */
    public Board()
    {
        // initialise instance variables
        listeners = new ArrayList<>();
        grid = new Box[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Box(0,i,j);
            }
        }
        // call random generator
        randomSpawnBox();
        randomSpawnBox();
        // why does this not work:
        // this = new Board(SIZE);
    }
    
    /**
     * Constructor for objects of class Board.
     * 
     * @param dim the square dimensions for a new Board object
     */
    public Board(final int dim)
    {
        listeners = new ArrayList<>();
        grid = new Box[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                grid[i][j] = new Box(0,i,j);
            }
        }
        // call random generator
        randomSpawnBox();
        randomSpawnBox();
    }
    
    /**
     * Constructor that creates a board from a 2-dimensional array.
     * Maybe we want this method for contsucting the board again in case
     * that we call undo() method because we are going to save an array (2d)
     * of the previous state.
     * 
     * @param array A grid array from which we can initialize our game with.
     */
    public Board(final Box[][] array)
    {
        listeners = new ArrayList<>();
        this.grid = new Box[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                grid[j][i] = array[j][i];
            }
        }
    }

    
    /**
     * Method that swipes up the current configuration 
     * of the Game.
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
     * of the Game.
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
     * of the Game.
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
     * Method that rotates the grid right by 90 degrees.
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
     * of the Game.
     * 
     */
    public void swipeRight()
    {
        // TODO fix the randomSpawnBox that it is only called when we were able to move/merge
        // move the whole board to the right, then merge, then move again to fill holes
        // then randomly spawn a new box in an empty slot
        final Box[][] oldBoard = copyBoxArray(grid);
        moveRight();
        mergeRight();
        moveRight();
        // should only be called when we were actually able to move/merge!
        // can we spawn only when oldBoard is not equal anymore?
        if (!areBoxArraysEqual(this.grid, oldBoard)) {
            randomSpawnBox();
            fireBoardChanged();
        }
    }
    
    /**
     * Method to copy a given Box array to a new Box array.
     * 
     * @param oldBoard a box array that we want to copy
     * @return a copied box array
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
                    grid[j][i] = grid[j][i - 1].merge(grid[j][i]);
                    grid[j][i - 1] = new Box(0, j, i - 1);
                }
            }
        }
    }
    
    
    /**
     * Method that swipes right the current configuration 
     * of the Game.
     * 
     * @param direction The direction in which we want to swipe the board.
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
        // check whether there are empty boxes
        if (!emptyBoxPositions().isEmpty()) {
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
        // TODO refactor to BoardHelper class as static method such that we hand
        // in two boards as we do here
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[0].length; j++) {
                // not sure if the second/third conditions add value
                // theoretically they should be the same already
                //if (currentBoard[i][j].getValue() != futureBoard[i][j].getValue()) {
                if (!currentBoard[i][j].equal(futureBoard[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Method that returns a list consisting of the 
     * points of the grid that are empty.
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
     * points of the grid that are filled.
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
     * Spawns a new box in the grid at a random location with value 2.
     *
     */
    public void randomSpawnBox()
    {   
        ArrayList<Box> availPositions = this.emptyBoxPositions();
        if (!availPositions.isEmpty()) {
            int randomPosition = (int) (Math.random() * availPositions.size());
            Box temp = availPositions.get(randomPosition);
            grid[temp.getRow()][temp.getColumn()] = new Box(2, temp.getRow(),temp.getColumn());
        }
    }
    
    /**
     * Method that gives the current state of the game.
     * 
     * @return Returns the current box array grid representing the current state
     */
    public Box[][] getState() {
        return grid;
    }
    
    public void addBoardListener(BoardListener li) {
        listeners.add(li);
    }
    
    
    private void fireBoardChanged() {
        for (BoardListener li : listeners) {
            li.boardChanged(this);
        }
    }
    
}