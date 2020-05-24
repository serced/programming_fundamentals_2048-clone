import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Objects; 
import java.util.Arrays; 
import java.util.Stack;

/**
 * This class should contain the provide useful static method functionality to the Board class.
 * 
 * @author Maria Kolyvaki and Severin Husmann
 * @version 20.05.2020
 */
public class BoardHelper
{   
    /**
     * Method to copy a given Box array to a new Box array.
     * 
     * @param oldBoard a box array that we want to copy
     * @return a copied box array
     */
    public static Box[][] copyBoxArray(final Box[][] oldBoard)
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
     * Method to check whether two box arrays are equal.
     * 
     * @return true if the given box arrays are equal
     */
    public static boolean areBoxArraysEqual(final Box[][] currentBoard, 
        final Box[][] futureBoard)
    {
        // TODO refactor to BoardHelper class as static method such that we hand
        // in two boards as we do here
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[0].length; j++) {
                if (!currentBoard[i][j].equal(futureBoard[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
