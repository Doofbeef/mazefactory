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
 *  The pathroom class extends room class and is used to draw the path of the
 *  maze.
 * 
 *--------------------------------------------------------------------------*/
import java.awt.Color;
import java.awt.Graphics;

public class PathRoom extends Room{
     
    private boolean onPath = false;
    private boolean isStart = false;
    private boolean isFinish = false;
    //  Constructor
    public PathRoom(Position position) {
        super(position);
    } 
    //  returns if the room is on the path
    public boolean onPath() {
        return onPath;
    }
    //  sets if the room is on the path or not
    public void setOnPath(boolean onPath) {
        this.onPath = onPath;
    }
    //  set if the room is the start of the maze
    public void setIsStart(boolean start) {
        isStart = start;
    }
    //  set if the room is the end of the maze
    public void setIsFinish(boolean finish) {
        isFinish = finish;
    }
    public boolean isStart() {
        return isStart;
    }
    
    public boolean isFinish() {
        return isFinish;
    }
    //  paints the rooms and a block if it is on the path. If it is the start
    //  room it will print "S" in the block and if it is the end of the path
    //  it will print a "F" in the block.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(onPath){
            g.fillRect(15, 15, 20, 20);
            g.setColor(Color.WHITE);
            if(isStart && isFinish) {
                g.drawString("S/F", 16, 30);
            }
            else if(isStart){
                g.drawString("S", 21, 30);
            }
            else if(isFinish) {
                g.drawString("F", 21, 30);
            }
        }       
    }
}
