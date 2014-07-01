
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Lee
 */
public class MazeGame {

    private Dimension dim;
    private List<String> mazeText;
    private int numberOfRows;
    private int numberOfCols;
    private Position start;
    private Position finish;

    public void init(String filename) {
        try {
            //  read text file input in command line
            Scanner in = new Scanner(new File(filename));
            //  read the line with the dimension specifications
            String line = in.nextLine();

            String[] tokens = line.split("[ |\n|\t|\r|.|,|)|(|\"]");

            dim = new Dimension();

            numberOfRows = Integer.parseInt(tokens[0]);
            numberOfCols = Integer.parseInt(tokens[1]);

            dim.setDimSize(numberOfRows, numberOfCols);

            //  create and add rooms to mazeComp with positions
            mazeText = new ArrayList<String>();
            //  read the maze part of the text file
            for (int i = 0; i < dim.dimSize(0) * 2 + 1; i++) {
                line = in.nextLine();
                mazeText.add(line);
            }

            line = in.nextLine();
            tokens = line.split("[ |\n|\t|\r|.|,|)|(|\"]");
            start = new Pos2D(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));

            line = in.nextLine();
            tokens = line.split("[ |\n|\t|\r|.|,|)|(|\"]");
            finish = new Pos2D(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));

            //  read the line in the text file with the starting position
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public Maze CreateMaze(MazeFactory f) {

        Maze maze = f.makeMaze(dim);

        MazeCompositor mazeComp = new MazeCompositor();

        maze.setCompositor(mazeComp);

        mazeComp.init(maze);

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                Position p = new Pos2D(i, j);
                Room room = f.makeRoom(p);
                room.setColor(Character.toString((mazeText.get(i * 2 + 1).charAt(1 + j * 2))));

                if (Character.toString(mazeText.get(i * 2).charAt(1 + j * 2)).equals("-")) {
                    room.setSide(Dir2D.N, f.makeWall());
                } else {
                    room.setSide(Dir2D.N, f.makeDoor());
                }
                if (Character.toString(mazeText.get(i * 2 + 1).charAt(j * 2)).equals("-")) {
                    room.setSide(Dir2D.W, f.makeWall());
                } else {
                    room.setSide(Dir2D.W, f.makeDoor());
                }
                if (Character.toString(mazeText.get(i * 2 + 1).charAt(2 + j * 2)).equals("-")) {
                    room.setSide(Dir2D.E, f.makeWall());
                } else {
                    room.setSide(Dir2D.E, f.makeDoor());
                }
                if (Character.toString(mazeText.get(i * 2 + 2).charAt(1 + j * 2)).equals("-")) {
                    room.setSide(Dir2D.S, f.makeWall());
                } else {
                    room.setSide(Dir2D.S, f.makeDoor());    // need to set doors to rooms
                }

                maze.addRoom(room, p);
            }
        }

        return maze;
    }

    public MazeWithPath CreateMazeWithPath(MazeWithPathFactory f, PathBuilder path) {

        MazeWithPath maze = f.makeMazeWithPath(dim);

        MazeCompositor mazeComp = new MazeCompositor();

        maze.setCompositor(mazeComp);

        mazeComp.init(maze);
        
        maze.setpathBuilder(path);

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                Position p = new Pos2D(i, j);
                PathRoom room = f.makePathRoom(p);
                room.setColor(Character.toString((mazeText.get(i * 2 + 1).charAt(1 + j * 2))));

                if (Character.toString(mazeText.get(i * 2).charAt(1 + j * 2)).equals("-")) {
                    room.setSide(Dir2D.N, f.makeWall());
                } else {
                    room.setSide(Dir2D.N, f.makeDoor());
                }
                if (Character.toString(mazeText.get(i * 2 + 1).charAt(j * 2)).equals("-")) {
                    room.setSide(Dir2D.W, f.makeWall());
                } else {
                    room.setSide(Dir2D.W, f.makeDoor());
                }
                if (Character.toString(mazeText.get(i * 2 + 1).charAt(2 + j * 2)).equals("-")) {
                    room.setSide(Dir2D.E, f.makeWall());
                } else {
                    room.setSide(Dir2D.E, f.makeDoor());
                }
                if (Character.toString(mazeText.get(i * 2 + 2).charAt(1 + j * 2)).equals("-")) {
                    room.setSide(Dir2D.S, f.makeWall());
                } else {
                    room.setSide(Dir2D.S, f.makeDoor());    // need to set doors to rooms
                }

                maze.addRoom(room, p);
            }
        }

        ((PathRoom) maze.roomAt(start)).setIsStart(true);
        maze.setStart((PathRoom) maze.roomAt(start));

        ((PathRoom) maze.roomAt(finish)).setIsFinish(true);
        maze.setFinish((PathRoom) maze.roomAt(finish));


        return maze;
    }
    
}
