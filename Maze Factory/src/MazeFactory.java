/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lee
 */
public class MazeFactory {
    public Maze makeMaze(Dimension dim) {
        return new Maze(dim);
    }
    
    public Wall makeWall() {
        return new Wall();
    }
    
    public Room makeRoom(Position p) {
        return new Room(p);
    }
    
    public Door makeDoor() {
        return new Door();
    }
}
