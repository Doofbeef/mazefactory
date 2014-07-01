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
 *  MazeCompositor creates the display for 
 * 
 *--------------------------------------------------------------------------*/

import java.awt.GridLayout;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeCompositor {

    private Maze maze;
    private Room[][] rooms;
    private int numberOfRows;
    private int numberOfCols;

    //  add an already made maze
    public void init(Maze maze) {
        this.maze = maze;
        numberOfRows = maze.dim().dimSize(0);
        numberOfCols = maze.dim().dimSize(1);
        rooms = new Room[numberOfRows][numberOfCols];
    }
    //  add a room to the list of rooms

    public void addRoom(Room room, Position p) {
        rooms[p.rowNum()][p.colNum()] = room;
    }
    
    //  find a room at specified position
    public Room roomAt(Position p) {
        return rooms[p.rowNum()][p.colNum()];
    }

    //  create a JApplet and add all the rooms to it.
    public void display() {
        JFrame frame = new JFrame("ShawnPathBuilder: Tree Algorithm");
        JApplet applet = new JApplet();
        frame.add(applet);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(50 * numberOfCols, 50 * numberOfRows + 25);
        frame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(numberOfRows, numberOfCols));
        for (int i = 0; i < numberOfRows; i++) { // add all the rooms to the JApplet
            for (int j = 0; j < numberOfCols; j++) {
                panel.add(roomAt(new Pos2D(i, j)));
            }
        }
        applet.add(panel);
    }
}
