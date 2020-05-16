import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BoardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BoardTest
{
    @Test
    public void testSwipeRight() {
        Board game = new Board();
        Box[][] grid = game.getState();
        System.out.print('\u000C');
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(grid[i][j].getValue());
            }
        }
        assertEquals(2, game.filledBoxPositions().size());
    }
}
