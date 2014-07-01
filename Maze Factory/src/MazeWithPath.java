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
 *  MazeWithPath extends Maze and is used to display a maze with path.
 * 
 *--------------------------------------------------------------------------*/
public class MazeWithPath extends Maze{
    
    private PathBuilder pathBuilder;
    private PathRoom start;
    private PathRoom finish;
    private boolean searchDone = false;
    private boolean hasPath = false;
    // Constructor
    public MazeWithPath(Dimension dim) {
        super(dim);
    }
    
    public void setpathBuilder(PathBuilder pathBuilder) {
        this.pathBuilder = pathBuilder;
    }
    
    public PathBuilder getpathBuilder() {
        return pathBuilder;
    }
    //  returns the position for the start of the maze
    public PathRoom start() {
        return start;
    }
    //  set the position of the start of the maze
    public void setStart(PathRoom p) {
        this.start = p;
    }

    //  returns the position for the end of the maze
    public PathRoom finish() {
        return finish;
    }
    //  set the position of the end of the maze
    public void setFinish(PathRoom p) {
        this.finish = p;
    }
    //  set searchDone
    public void setSearchDone(boolean searchDone) {
        this.searchDone = searchDone;
    }
    //  return searchDone
    public boolean searchDone() {
        return searchDone;
    }
    //  set hasPath
    public void setHasPath(boolean hasPath) {
        this.hasPath = hasPath;
    }  
    
    public boolean hasPath() {
        return hasPath;
    }
    //  find the of the maze
    public void findPath() {
        pathBuilder.init(this);
        pathBuilder.findPath();
        pathBuilder.displayPath();
    }
    //  display the MazeWithPath, find the path with PathBuilder
    //  and display with the MazeCompositor display method in a
    //  JApplet.
    @Override
    public void display() {
        findPath();
        this.getCompositor().display();
    }
}
