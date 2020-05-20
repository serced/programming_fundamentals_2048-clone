import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 * Write a description of class GraphicalUserInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GraphicalUserInterface
{
    // instance variables
    private final Model model;
    
    
    
    /**
     * Constructor for objects of class GraphicalUserInterface
     */
    public GraphicalUserInterface(final Model model)
    {
        // initialise instance variables
        this.model = model;
    }

    /**
     * Run the application.
     */
    public void run() {
        System.out.println("Welcome to " + model.toString());
    }
}
