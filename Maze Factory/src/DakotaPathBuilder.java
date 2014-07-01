import java.util.Stack;

/*
 * Name:		Dakota Brown
 * 
 * Class:		CSCI 5100-Object Oriented Programming
 * 
 * Started:		02/10/12
 * Modified:	03/07/12
 * 
 * Description:	References MazeCompositor's store[][] of Rooms and visits the
 * 				rooms to set if they are on the path or not
 * 
 */

public class DakotaPathBuilder implements PathBuilder{
	
	private MazeWithPath maze;					//used to determine dimensions
	private Stack<PathRoom> path, reverseP;		//holds path
	private int rowSize, colSize;
	
	/**
	 * init()
	 * 				points back to MazeWithPath
	 */
    @Override
	public void init(MazeWithPath maze) {
			this.maze = maze;
			path = new Stack<PathRoom>();		//holds path in reverse
			reverseP = new Stack<PathRoom>();	//reverses path to have correct order
			rowSize = maze.dim().dimSize(0);
			colSize = maze.dim().dimSize(1);                  
	}

	/**
	 * findPath():	RECURSIVE HELPER METHOD
	 * 				finds path and then iterates through stack to flip to onPath = true
	 * 
	 */
    @Override
	public void findPath() {
		PathRoom current = maze.start();
		Position currentPos = current.getPosition();
		
		maze.setHasPath(findPath(currentPos.rowNum(), currentPos.colNum()));
		
		if(maze.hasPath()){
			for(PathRoom i: path){
				i.setOnPath(true);
				reverseP.push(i);
			}
		}
	}

	/**
	 * findPath(int row, int col)
	 * 				recursively traverses maze
	 */
	public boolean findPath(int row, int col){
		/*	ALGORITHM:
		 * 
		 *  BASE CASES
		 * 	if (x,y outside maze) return false
		 *  if (x,y is goal) return true *******************
		 *	if (x,y not open) return false
		 *	
		 *	BODY
		 *	push room as part of solution path
		 *	if (FINDPATH(North of x,y) == true) return true
		 *	if (FINDPATH(East of x,y) == true) return true
		 *	if (FINDPATH(South of x,y) == true) return true
		 *	if (FINDPATH(West of x,y) == true) return true
		 *	pop room as part of solution path
		 *	return false
		 */
		
		PathRoom current = (PathRoom) maze.roomAt(new Pos2D(row, col));
		
		//base case: outside maze
		if(row < 0 || row >= rowSize || col < 0 || col >= colSize)
			return false;
		
		//base case: not open
		if(path.contains(current)){
			return false;
		}
		
		//base case: is the goal
		if(current.equals(maze.finish())){
			path.push(current);
			return true;
		}
		
		//add room to path
		path.push(current);
				
		//north check for door
		if(current.getSide(Dir2D.N).enter())
			if(findPath(row - 1, col))
				return true;
				
		//east check for door
		if(current.getSide(Dir2D.E).enter())
			if(findPath(row, col + 1))
				return true;
				
		//south check for door
		if(current.getSide(Dir2D.S).enter())
			if(findPath(row + 1, col))
				return true;
			
		//west check for door
		if(current.getSide(Dir2D.W).enter())
			if(findPath(row, col - 1))
				return true;
		
		//nothing found; pop from path and return false
		path.pop();
		return false;
	}
	
	/**
	 * displayPath():
	 * 				displays the sequential locations of rooms on
	 *				the path
	 */
    @Override
	public void displayPath() {
		System.out.println();
		for(PathRoom i : reverseP){
			System.out.print(i.display() + " ");
		}
		System.out.println();
	}
}
