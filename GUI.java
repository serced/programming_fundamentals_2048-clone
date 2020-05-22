
/**
 * Write a description of class GUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUI
{
    // instance variables - replace the example below with your own
    public static void run() {
        // Model
        final Board game = new Board();
        
        // GUI
        final MainFrame f = new MainFrame(game);
        f.setVisible(true);
    }
}
