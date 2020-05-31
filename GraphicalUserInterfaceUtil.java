/**
 * The main window of the GUI application.
 *
 * @author Maria Kolyvaki and Severin Husmann
 * @version final
 */
public class GraphicalUserInterfaceUtil
{
    /**
     * Private constructor of the class to show that it has only static methods.
     */
    private GraphicalUserInterfaceUtil() {}
    
    /**
     * Runs a new MainFrame operating on the given game model.
     * 
     */
    public static void run() {
        // Model
        final Board game = new Board();
        
        // GUI
        final MainFrame f = new MainFrame(game);
        // makes the frame visible, by default is invisible
        f.setVisible(true);
    }
}
