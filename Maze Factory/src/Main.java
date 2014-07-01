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
 *  MazeFactory reads a text file and then generates a maze with MazeCompositor.
 *  Then a MazeWithPath is created and is displayed in a JApplet.
 * 
 *--------------------------------------------------------------------------*/
import java.sql.SQLException;
import javax.swing.JApplet;

public class Main extends JApplet {

    public static void main(String[] args) throws SQLException {
        if (args.length != 1) {
            System.out.println(
                    "Usage: java MazeFactory fullfilename");
            System.exit(0);
        }       

        String filename = args[0];
        
        MazeGame game = new MazeGame();
        game.init(filename);
        
        MazeFactory factory = new MazeFactory();
        MazeWithPathFactory factory2 = new  MazeWithPathFactory();
        
        Maze maze = game.CreateMaze(factory);
        Maze maze2 = game.CreateMazeWithPath(factory2, new ShawnPathBuilder());
        maze.display();
        maze2.display();
        
    }
}
