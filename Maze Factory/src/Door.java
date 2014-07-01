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
 *  The door class
 * 
 *--------------------------------------------------------------------------*/
public class Door extends MapSite {
    
    boolean open = true;
    //  returns if the door is open
    public boolean open() {
        return open;
    }
    //  sets the door open or closed
    public void setOpen(boolean open) {
        this.open = open;
    }
}
