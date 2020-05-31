import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BoxTest.
 *
 * @author  Maria Kolyvaki and Severin Husmann
 * @version 31.05.2020
 */
public class BoxTest
{
    /**
     * Default constructor for test class BoxTest
     */
    public BoxTest()
    {
    }

    @Test
    public void cantMergeBox() {
        final Box someBox = new Box(2, 0, 0);
        final Box otherBox = new Box(4, 0, 0);
        assertEquals(null, someBox.merge(otherBox));
    }
}
