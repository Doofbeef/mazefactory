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
 *  Maze holds the dimension and list of rooms.
 * 
 *--------------------------------------------------------------------------*/

public class Maze {

    private Dimension dim;
    private MazeCompositor mazeComp;
    // Constructor
    public Maze(Dimension dim) {
        this.dim = dim;
    }
    //  returns the dimension of the maze
    public Dimension dim() {
        return dim;
    }
    //  add a room to the maze
    public void addRoom(Room room, Position p) {
        mazeComp.addRoom(room, p);
    }
    //  find a room at specified position
    public Room roomAt(Position p) {
        return mazeComp.roomAt(p);
    }
    
    public void setCompositor(MazeCompositor mazeComp) {
        this.mazeComp = mazeComp;
    }
    
    public MazeCompositor getCompositor() {
        return mazeComp;
    }
    //  display a regular maze in a JApplet
    public void display() {
        mazeComp.display();
    }
}
