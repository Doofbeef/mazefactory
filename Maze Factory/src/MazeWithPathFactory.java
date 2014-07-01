/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lee
 */
public class MazeWithPathFactory {
    public MazeWithPath makeMazeWithPath(Dimension dim) {
        return new MazeWithPath(dim);
    }
    
    public Wall makeWall() {
        return new Wall();
    }
    
    public PathRoom makePathRoom(Position p) {
        return new PathRoom(p);
    }
    
    public Door makeDoor() {
        return new Door();
    }
}

