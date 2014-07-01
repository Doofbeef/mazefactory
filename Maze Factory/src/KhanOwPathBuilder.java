import java.util.Iterator;
import java.util.LinkedList;

/* Written by: Khan Ow
 * Date Created: 2012/03/24
 * Last Modified: 2012/04/09
 */
public class KhanOwPathBuilder implements PathBuilder {
	MazeWithPath maze;
	LinkedList<PathNode> queue;
	LinkedList<PathNode> checked;
	LinkedList<PathRoom> path;
	PathNode[][] nodes;
	PathNode finishNode;
	PathNode startNode;
	boolean pathFound;
	
	public KhanOwPathBuilder() {
		queue = new LinkedList<PathNode>();
		checked = new LinkedList<PathNode>();
		path = new LinkedList<PathRoom>();
	}
	
	public void init(MazeWithPath maze) {
		this.maze = maze;
		nodes = new PathNode[maze.dim().dimSize(0)][maze.dim().dimSize(1)];
		for (int i = 0; i < maze.dim().dimSize(0); i++) {
			for (int j = 0; j < maze.dim().dimSize(1); j++) {
				nodes[i][j] = new PathNode((PathRoom)maze.roomAt(new Pos2D(i, j)));
			}
		}
		Pos2D startPos = (Pos2D)maze.start().getPosition();
		if (checkInBoundary(startPos)) {
			startNode = nodes[startPos.rowNum()][startPos.colNum()];
			queue.offer(startNode);
		} else {
			System.out.println("Start Node out of boundary!");
		}
		pathFound = false;
	}
	
	private boolean checkInBoundary(Pos2D position) {
		if (position.rowNum() >= 0 && position.rowNum() < maze.dim().dimSize(0) && position.colNum() >= 0 && position.colNum() < maze.dim().dimSize(1)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkInBoundary(int row, int col) {
		if (row >= 0 && row < maze.dim().dimSize(0) && col >= 0 && col < maze.dim().dimSize(1)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void findPath() {
		while (!queue.isEmpty() && !pathFound) {
			if (queue.peek().isFinish()) {
				finishNode = queue.remove();
				pathFound = true;
				break;
			}
			if (!checked.contains(queue.peek())) {
				for (int i = 0; i < 4; i++) {
					if (queue.peek().getSide(i).enter() && queue.peek().fromSide != i) {
						Pos2D currentNodePos = (Pos2D)queue.peek().pos();
						switch (i) {
						case 0:
							if (checkInBoundary(currentNodePos.rowNum() - 1, currentNodePos.colNum())) {
								nodes[currentNodePos.rowNum() - 1][currentNodePos.colNum()].setFroms((i + 2) % 4, queue.peek());
								queue.offer(nodes[currentNodePos.rowNum() - 1][currentNodePos.colNum()]);
							}
							break;
						case 1:
							if (checkInBoundary(currentNodePos.rowNum(), currentNodePos.colNum() + 1)) {
								nodes[currentNodePos.rowNum()][currentNodePos.colNum() + 1].setFroms((i + 2) % 4, queue.peek());
								queue.offer(nodes[currentNodePos.rowNum()][currentNodePos.colNum() + 1]);
							}
							break;
						case 2:
							if (checkInBoundary(currentNodePos.rowNum() + 1, currentNodePos.colNum())) {
								nodes[currentNodePos.rowNum() + 1][currentNodePos.colNum()].setFroms((i + 2) % 4, queue.peek());
								queue.offer(nodes[currentNodePos.rowNum() + 1][currentNodePos.colNum()]);
							}
							break;
						case 3:
							if (checkInBoundary(currentNodePos.rowNum(), currentNodePos.colNum() - 1)) {
								nodes[currentNodePos.rowNum()][currentNodePos.colNum() - 1].setFroms((i + 2) % 4, queue.peek());
								queue.offer(nodes[currentNodePos.rowNum()][currentNodePos.colNum() - 1]);
							}
							break;
						}
					}
				}
			}
			
			checked.offer(queue.poll());
		}
		
		if (pathFound) {
			maze.setHasPath(true);
			PathNode currentNode = finishNode;
			while (!currentNode.isStart()) {
				currentNode.getRoom().setOnPath(true);
				path.offer(currentNode.getRoom());
				currentNode = currentNode.getFrom();
			}
			startNode.setOnPath(true);
			path.offer(startNode.getRoom());
		}
		
		maze.setSearchDone(true);
		
	}
	
	public void displayPath() {
		Iterator<PathRoom> itrPathRooms = path.descendingIterator();
		while (itrPathRooms.hasNext()) {
			Pos2D pos = (Pos2D)itrPathRooms.next().getPosition();
			System.out.print("(" + pos.rowNum() + ", " + pos.colNum() + ") ");
		}
	}
        class PathNode {
	PathRoom room;
	PathNode cameFrom;
	int fromSide;
	
	public PathNode(PathRoom room) {
		// Creates a dummy node.
		this.room = room;
		fromSide = -1;
	}
	
	public void setFroms(int fromSide, PathNode cameFrom) {
		this.fromSide = fromSide;
		this.cameFrom = cameFrom;
	}
	
	public Position pos() {
		return room.getPosition();
	}
	
	public boolean isStart() {
		return room.isStart();
	}
	
	public boolean isFinish() {
		return room.isFinish();
	}
	
	public void setOnPath(boolean onPath) {
		room.setOnPath(onPath);
	}
	
	public boolean onPath() {
		return room.onPath();
	}
	
	public MapSite getSide(int side) {
		return room.getSide(side);
	}
	
	public PathRoom getRoom() {
		return room;
	}
	
	public PathNode getFrom() {
		return cameFrom;
	}
}

}
