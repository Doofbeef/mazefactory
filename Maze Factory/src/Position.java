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
 *  The Position class is used know the position of a room in a maze
 * 
 *--------------------------------------------------------------------------*/

public class Position {   
    private int row;
    private int column;
    
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int rowNum() {
        return row;
    }
    //  return column
    public int colNum() {
        return column;
    }
}
