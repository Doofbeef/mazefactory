/*--------------------------------------------------------------------------
 * Author:          Shawn Wiley
 * Written:         2/27/2012
 * Last Updated:    3/28/2012
 * Class:           CSCI 5100U
 * 
 *      * Complilation:    javac MazeFactory.java
 * 
 *      * Execution:       java MazeFactory <Text File>
 * 
 *  PathBuilder sets PathRooms in a MazeCompositor onPath if it is on the path.
 * 
 *--------------------------------------------------------------------------*/

public interface PathBuilder {
    void init(MazeWithPath maze);
    
    void displayPath();
    
    void findPath();
}
