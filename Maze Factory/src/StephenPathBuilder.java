/*****************************************************************************/
/* Author: Stephen Cowart                                                    */
/* Created: 3/31/2012                                                        */
/* Last Updated: 4/2/2012                                                    */
/*                                                                           */
/* StudentPathBuilder implements the PathBuilder interface.                  */
/* The findPath uses a stack a recursion to find a path through the maze.    */
/* displayPath lists the location of the rooms in the path.                  */
/*****************************************************************************/
import java.util.Stack;

public class StephenPathBuilder implements PathBuilder {
    //init class variables
    private MazeWithPath maze;
    private Stack<PathRoom> rooms = new Stack<PathRoom>();
    private boolean pathFound = false;

    public void init(MazeWithPath maze) {
        //establish a pointer back to the MazeWithPath this pathbuilder
        //works on.
        this.maze = maze;
    }

    public void displayPath() {
        if (!maze.hasPath()) {
            //if no path exists print message
            System.out.println("No Path Found");
        } else {
            //copy the stack containing the rooms on the path and display them
            Stack<PathRoom> tempRooms = (Stack<PathRoom>) rooms.clone();
            while (!tempRooms.empty()) {
                PathRoom temp = tempRooms.pop();
                temp.display();
            }
            System.out.println("");
        }
    }

    public void findPath() {
        //push the start room onto the stack and call checkroom to check
        //the doors of the room. Passing a -1 so that all four sides
        //are checked.
        rooms.push(maze.start());
        checkRoom(-1);
        //If the stack is empty there is no path otherwise there is a path
        if (rooms.empty()) {
            maze.setHasPath(false);
        } else {
            maze.setHasPath(true);
        }
    }

    private void checkRoom(int dir) {
    	//checks the four sides of a room. The dir parameter indicates what
        //room not to check to prevent infinite loop.
        //Peek at the top room and its location
        PathRoom tempRoom = rooms.peek();
        int row = tempRoom.getPosition().rowNum();
        int col = tempRoom.getPosition().colNum();
        
        //Assume the room is on path
        tempRoom.setOnPath(true);
        //if this room is the finish room we have completed a path
        if (tempRoom.isFinish()) {
            pathFound = true;
        } else {
            //otherwise check to see if northside of the room is open door
            //if a path has not been found, we can enter to the north,
            //the room to the north is in the bounds of the maze, and
            //and verify we are not coming from the south
            if (!pathFound && (tempRoom.getSide(0).enter() == true) &&
                    (tempRoom.getPosition().rowNum() - 1 >= 0) && dir != 0) {
                //push the next room onto stack
            	if(!rooms.contains(maze.roomAt(new Pos2D(row - 1, col)))){
            		rooms.push((PathRoom) maze.roomAt(new Pos2D(row - 1, col)));
                //check the next room passing in the direction not to check
                //i.e. passing a two will prevent checkroom from checking
                //the southern room preventing it checking 2 rooms
                //back and forth
            		checkRoom(2);
            	}
            }
            //check east side the same as the north
            if (!pathFound && (tempRoom.getSide(1).enter() == true) && 
                    (tempRoom.getPosition().colNum() + 1 < maze.dim().dimSize(2))
                    && dir != 1) {
            	if(!rooms.contains(maze.roomAt(new Pos2D(row, col + 1)))){
            		rooms.push((PathRoom) maze.roomAt(new Pos2D(row, col + 1)));
            		checkRoom(3);
            	}
            }
            //check south side
            if (!pathFound && (tempRoom.getSide(2).enter() == true) && 
                    (tempRoom.getPosition().rowNum() + 1 < maze.dim().dimSize(1))
                    && dir != 2) {
            	if(!rooms.contains(maze.roomAt(new Pos2D(row + 1, col)))){
            		rooms.push((PathRoom) maze.roomAt(new Pos2D(row + 1, col)));
            		checkRoom(0);
            	}
            }
            //check west side
            if (!pathFound && (tempRoom.getSide(3).enter() == true) &&
                    (tempRoom.getPosition().colNum() - 1 >= 0) && dir != 3) {
            	if(!rooms.contains((PathRoom) maze.roomAt(new Pos2D(row, col - 1)))){
            		rooms.push((PathRoom) maze.roomAt(new Pos2D(row, col - 1)));
            		checkRoom(1);
            	}
            }
            //if we have checked all four sides without finding the finish
            //we set the room to not be on the path and pop the room
            if (!pathFound) {
                tempRoom.setOnPath(false);
                rooms.pop();
            }
        
        }
    }
}
