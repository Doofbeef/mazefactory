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
 *  The MapSite class holds information for its children Room, Wall, and Door
 * 
 *--------------------------------------------------------------------------*/
import javax.swing.JPanel;

public class MapSite extends JPanel {
    
    public boolean enter() {
        if(this instanceof Door) {
            return true;
        }
        else return false;
    }
}
