
/**
 * Test Listener which can be registered to the Board to check the boardlistener implementations.
 *
 * @author Maria Kolyvaki and Severin Husmann
 * @version final
 */
public class TestListener implements BoardListener {
    private boolean changed = false;
    
    /**
     * Changes the changed field to True if it is called.
     *
     * @param game the game whith its current state
     */
    public void boardChanged(final Board game) {
        changed = true;
    }
    
    /**
     * This methods gives the changed field when asked. 
     * 
     * @return Returns the changed field
     */
    public boolean isChanged() {
        return changed;
    }
}
