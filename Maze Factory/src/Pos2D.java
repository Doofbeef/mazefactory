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
 *  The Pos2D class is for 2-Dimensional mazes.
 * 
 *--------------------------------------------------------------------------*/

public class Pos2D extends Position{
    //  Constructor
    
    public Pos2D(int row, int column) {
        super(row, column);
    }
    
    @Override
    public int rowNum() {
        return super.rowNum();
    }
    //  return column
    @Override
    public int colNum() {
        return super.colNum();
    }
}
