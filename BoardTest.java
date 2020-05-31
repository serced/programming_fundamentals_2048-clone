import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Objects;
/**
 * The test class BoardTest.
 *
 * @author  Maria Kolyvaki and Severin Husmann
 * @version 16.02.2020
 */
public class BoardTest
{
    Box[][] grid = new Box[4][4];
    Box[][] grid1 = new Box[4][4];
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        grid = new Box[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new Box(0,i,j);
            }
        }
    }
    
    @Test
    public void testInitilization() {
        Board game = new Board();
        assertEquals(14, game.emptyBoxPositions().size());
    }
    
    @Test
    public void testRandomCheckFullGrid() {
        
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[0].length; i++) {
                grid[j][i] = new Box(2, i, j);
            }
        }
    
        Board game = new Board(grid);
        game.randomSpawnBox();
        
        assertEquals(0, game.emptyBoxPositions().size());
    }
    
    @Test
    public void testSwipeRightOneValuePerRow() {
        // check whether at any column position, swipe right will go to most right column
        grid[0][0] = new Box(2,0,0);
        grid[1][1] = new Box(2,1,1);
        grid[2][2] = new Box(2,2,2);
        grid[3][3] = new Box(2,3,3);
        
        Board game = new Board(grid);
        game.swipeRight();
        Box[][] state = game.getState();
        assertEquals(2, state[0][3].getValue());
        assertEquals(2, state[1][3].getValue());
        assertEquals(2, state[2][3].getValue());
        assertEquals(2, state[3][3].getValue());
    }
    
    @Test
    public void testSwipeRightTwoValuesPerRowNoMerge() {
        // check whether at any column position, swipe right will go to most right column
        grid[0][0] = new Box(2,0,0);
        grid[1][1] = new Box(2,1,1);
        grid[2][2] = new Box(2,2,2);
        grid[3][3] = new Box(2,3,3);
        grid[0][1] = new Box(4,0,0);
        grid[1][2] = new Box(4,1,1);
        grid[2][3] = new Box(4,2,2);
        grid[3][2] = new Box(4,3,3);
        
        Board game = new Board(grid);
        game.swipeRight();
        Box[][] state = game.getState();
        assertEquals(4, state[0][3].getValue());
        assertEquals(4, state[1][3].getValue());
        assertEquals(4, state[2][3].getValue());
        assertEquals(2, state[3][3].getValue());
        
        assertEquals(2, state[0][2].getValue());
        assertEquals(2, state[1][2].getValue());
        assertEquals(2, state[2][2].getValue());
        assertEquals(4, state[3][2].getValue());
        
        //assertEquals(9, game.filledBoxPositions().size());
        assertEquals(7, game.emptyBoxPositions().size());
    }
    
    @Test
    public void testSwipeRightTwoValuesPerRowWithMerge() {
        // check whether at any column position, swipe right will go to most right column
        grid[0][0] = new Box(4,0,0);
        grid[1][1] = new Box(4,1,1);
        grid[2][2] = new Box(4,2,2);
        grid[3][3] = new Box(4,3,3);
        grid[0][1] = new Box(4,0,0);
        grid[1][2] = new Box(4,1,1);
        grid[2][3] = new Box(4,2,2);
        grid[3][2] = new Box(4,3,3);
        
        Board game = new Board(grid);
        game.swipeRight();
        Box[][] state = game.getState();
        assertEquals(8, state[0][3].getValue());
        assertEquals(8, state[1][3].getValue());
        assertEquals(8, state[2][3].getValue());
        assertEquals(8, state[3][3].getValue());
        
        //assertEquals(5, game.filledBoxPositions().size());
        assertEquals(11, game.emptyBoxPositions().size());
    }
    
    
    @Test
    public void testRotateRightWithSwipeUp() {
        // check whether at any column position 
        // at lowest row swipe up will go to most upper column
        grid[3][0] = new Box(1,3,0);
        grid[3][1] = new Box(2,3,1);
        grid[3][2] = new Box(3,3,2);
        grid[3][3] = new Box(4,3,3);
        
        Board game = new Board(grid);
        game.swipeUp();
        Box[][] state = game.getState();
        assertEquals(1, state[0][0].getValue());
        assertEquals(0, state[0][0].getRow());
        assertEquals(0, state[0][0].getColumn());
        
        assertEquals(2, state[0][1].getValue());
        assertEquals(0, state[0][1].getRow());
        assertEquals(1, state[0][1].getColumn());
        
        assertEquals(3, state[0][2].getValue());
        assertEquals(0, state[0][2].getRow());
        assertEquals(2, state[0][2].getColumn());
        
        assertEquals(4, state[0][3].getValue());
        assertEquals(0, state[0][3].getRow());
        assertEquals(3, state[0][3].getColumn());
        
        //assertEquals(5, game.filledBoxPositions().size());
        assertEquals(11, game.emptyBoxPositions().size());
    }
    
    @Test
    public void testSwipeUpWithMerge() {
        grid[0][0] = new Box(2,0,0);
        grid[0][1] = new Box(2,0,1);
        grid[0][2] = new Box(4,0,2);
        grid[0][3] = new Box(4,0,3);
        
        grid[3][0] = new Box(2,3,0);
        grid[2][1] = new Box(2,2,1);
        grid[1][2] = new Box(4,1,2);
        grid[3][3] = new Box(4,3,3);
        
        Board game = new Board(grid);
        game.swipeUp();
        Box[][] state = game.getState();
        assertEquals(4, state[0][0].getValue());
        assertEquals(0, state[0][0].getRow());
        assertEquals(0, state[0][0].getColumn());
        
        assertEquals(4, state[0][1].getValue());
        assertEquals(0, state[0][1].getRow());
        assertEquals(1, state[0][1].getColumn());
        
        assertEquals(8, state[0][2].getValue());
        assertEquals(0, state[0][2].getRow());
        assertEquals(2, state[0][2].getColumn());
        
        assertEquals(8, state[0][3].getValue());
        assertEquals(0, state[0][3].getRow());
        assertEquals(3, state[0][3].getColumn());
        
        //assertEquals(5, game.filledBoxPositions().size());
        assertEquals(11, game.emptyBoxPositions().size());
    }
    
    @Test
    public void testUndo() {
        int value = 2;
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[0].length; i++) {
                grid[j][i] = new Box(value, i, j);
                value++;
            }
        }
    
        Board game = new Board(grid);
        Box[][] temp = game.getState();
        game.swipeToDirection(SwipeDirection.RIGHT);
        game.swipeToDirection(SwipeDirection.LEFT);
        game.undo();
        
        assertEquals(game.getState(), temp);
    }
    
    @Test
    public void testUndoPrevious() {
        int value = 1;
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[0].length; i++) {
                grid[j][i] = new Box(value, i, j);
                value++;
            }
        }
        Board game = new Board(grid);
        
        game.swipeToDirection(SwipeDirection.RIGHT);
        game.swipeToDirection(SwipeDirection.LEFT);
        game.swipeToDirection(SwipeDirection.UP);
        Box[][] temp = game.getState();
        game.swipeToDirection(SwipeDirection.DOWN);
        game.undo();
        
        assertEquals(game.getState(), temp);
    }
    
    @Test
    public void testIsGameOverTrue() {
        int value = 1;
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[0].length; i++) {
                grid[j][i] = new Box(value, i, j);
                value++;
            }
        }
    
        Board game = new Board(grid);
        
        //assertEquals(16, game.filledBoxPositions().size());
        assertEquals(0, game.emptyBoxPositions().size());
        assertEquals(true, game.isGameOver());
    }
    
    @Test
    public void testIsGameOverFalseBoardNotFull() {
        int value = 0; // equivalent to the first box being empty
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[0].length; i++) {
                grid[j][i] = new Box(value, i, j);
                value++;
            }
        }
    
        Board game = new Board(grid);
        
        //assertEquals(15, game.filledBoxPositions().size());
        assertEquals(1, game.emptyBoxPositions().size());
        assertEquals(false, game.isGameOver());
    }
    
    @Test
    public void testIsGameOverFalseMergePossible() {
        int value = 2;
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[0].length; i++) {
                grid[j][i] = new Box(value, i, j);
            }
            value = value + 2;
        }
    
        Board game = new Board(grid);
        
        Box[][] state = game.getState();
        //assertEquals(16, game.filledBoxPositions().size());
        assertEquals(0, game.emptyBoxPositions().size());
        assertEquals(false, game.isGameOver());
    }
    
    // TODO add test for spwaning box only when something changed
}
