/*--------------------------------------------------------------------------
 * Author:          Shawn Wiley
 * Written:         2/27/2012
 * Last Updated:    3/6/2012
 * Class:           CSCI 5100U
 * 
 *      * Complilation:    javac MazeFactory.java
 * 
 *      * Execution:       java MazeFactory <Text File>
 * 
 *  The Dimension class holds the information of the rows, column, and plane
 *  of a maze.
 * 
 *--------------------------------------------------------------------------*/

public class Dimension {
    
    private int row = 0;
    private int column = 0;
    private int dimension = 0;
    //  return the dimensional size
    public int whichD() {
        return dimension;
    }
    //  set the dimensional size
    public void setD(int dimension) {
        this.dimension = dimension;
    }
    // get the row or column of the dimension
    public int dimSize(int n) {
        if (n == 0) {
            return row;
        }
        else if (n == 1) {
            return column;
        }
        else
            return 0;
    }
    // set the dimension size
    public void setDimSize(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
