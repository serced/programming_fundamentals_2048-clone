
/**
 * This class should contain the provide useful static method functionality to the Board class.
 * 
 * @author Maria Kolyvaki and Severin Husmann
 * @version final
 */
public class BoardHelper
{  
    /**
     * Private constructor of the class to show that it has only static methods.
     */
    private BoardHelper() {}
    
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
     * @param currentBoard the current board to check for equality
     * @param futureBoard the other board to check for equality
     * @return true if the given box arrays are equal
     */
    public static boolean areBoxArraysEqual(final Box[][] currentBoard, 
        final Box[][] futureBoard)
    {
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
