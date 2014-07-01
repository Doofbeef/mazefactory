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
 *  The room class holds the position of the room and if its side are either a
 *  door or wall.
 * 
 *--------------------------------------------------------------------------*/
import java.awt.Color;
import java.awt.Graphics;


public class Room extends MapSite {
    
    private Position pos;
    private MapSite north;
    private MapSite west;
    private MapSite east;
    private MapSite south;
    private Color color;
    // Constructor
    public Room(Position position) {
        this.pos = position;
    }
    // gets the position of the room
    public Position getPosition() {
        return pos;
    }
    // returns whether specified side is a door or wall
    public MapSite getSide(int n) {
        if (n == 0) {
            return north;
        } else if (n == 1) {
            return west;
        } else if (n == 3) {
            return east;
        } else if (n == 2) {
            return south;
        } else {
            return null;
        }
    }
    //  sets specified side to be a door or wall
    public void setSide(int n, MapSite mapSite) {
        if (n == 0) {
            north = mapSite;
        } else if (n == 1) {
            west = mapSite;
        } else if (n == 3) {
            east = mapSite;
        } else if (n == 2) {
            south = mapSite;
        }
    }
    //  sets the color of the room
    public void setColor(String s) {
        if(s.equals("1")) {
            color = Color.GREEN;
        }
        else if(s.equals("2")) {
            color = Color.RED;
        }
        else {
            color = Color.ORANGE;
        }  
    }
    
    public String display() {
        return "(" + getPosition().rowNum() + ", " + getPosition().colNum() + ") ";
    }
    
    //  draws the room
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(5, 5, 40, 40);   // color the room
        g.setColor(Color.BLACK);
        if(north.getClass().equals((new Wall()).getClass())){   //  Check and print the north side
            g.drawLine(5, 5, 45, 5 );
        }
        else {
            g.setColor(color);
            g.fillRect(20, 0, 10, 5);
            g.setColor(Color.BLACK);
            g.drawLine(5 , 5 , 20 , 5 );
            g.drawLine(20 , 5 , 20 , 0 );
            g.drawLine(30 , 0 , 30 , 5 );
            g.drawLine(30 , 5 , 45 , 5 );
        }
        if(west.getClass().equals((new Wall()).getClass())){    //  Check and print the west side
            g.drawLine(5 , 5 , 5 , 45 );
        }
        else {
            g.setColor(color);
            g.fillRect(0, 20, 5, 10);
            g.setColor(Color.BLACK);
            g.drawLine(5 , 5 , 5 , 20 );
            g.drawLine(5 , 20 , 0 , 20 );
            g.drawLine(0 , 30 , 5 , 30 );
            g.drawLine(5 , 30 , 5 , 45 );
        }
        if(east.getClass().equals((new Wall()).getClass())){    //  Check and print the east side
            g.drawLine(45 , 5 , 45 , 45 );
        }
        else {
            g.setColor(color);
            g.fillRect(45, 20, 5, 10);
            g.setColor(Color.BLACK);
            g.drawLine(45 , 5 , 45 , 20 );
            g.drawLine(45 , 20 , 50 , 20 );
            g.drawLine(50 , 30 , 45 , 30 );
            g.drawLine(45 , 30 , 45 , 45 );
        }
        if(south.getClass().equals((new Wall()).getClass())){   //  Check and print the south side
            g.drawLine(5, 45, 45 , 45);
        }
        else {
            g.setColor(color);
            g.fillRect(20, 45, 10, 5);
            g.setColor(Color.BLACK);
            g.drawLine(5 , 45 , 20 , 45 );
            g.drawLine(20 , 45 , 20 , 50 );
            g.drawLine(30 , 50 , 30 , 45 );
            g.drawLine(30 , 45 , 45 , 45 );
        }
    }
}
