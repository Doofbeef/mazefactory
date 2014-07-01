/*******************************************************
 * Author: Matthew Wilkinson
 * Date Created: March 8, 2012
 * Date Last Modified: April 18, 2012 
 * Brief Description:
 * A path builder that uses a combination of Dijkstra and LCS 
 * To determine the path to the end of the maze. It is also the 
 * shortest path 
 ********************************************************/

import java.util.*;

class Queue<T> {
	ArrayList<T> q = new ArrayList<T>();

	void offer(T ob) {
		q.add(ob);
	}

	T poll() {
		T ob = q.get(0);
		q.remove(0);
		return ob;
	}

	boolean isEmpty() {
		return q.isEmpty();
	}

	ArrayList<T> print() {
		return q;
	}
}

public class MatthewPathBuilder implements PathBuilder {
	MazeWithPath maze;
	int[][] pathOfInt = new int[1000][1000];

	public void init(MazeWithPath maze) {
		// TODO Auto-generated method stub
		this.maze = maze;
	}

	public void findPath() {
		PathRoom r = maze.start();
		// Unanimous for both codes

		// set up a integer array where all numbers are equal to Integer.Max

		for (int x = 0; x < 1000; x++)
			for (int y = 0; y < 1000; y++)
				pathOfInt[x][y] = 123456;

		// gets the position of the rooms that need processing
		Queue<Pos2D> roomQ = new Queue<Pos2D>();
		Pos2D curPos;
		PathRoom tempRoom;
		boolean[] isDoor = new boolean[4];
		// puts the first position in the room Queue
		roomQ.offer((Pos2D)r.getPosition());
		// set the start position to 0 this is due to the fact it takes 0 moves
		// to get to this position from the start
		int curX = maze.start().getPosition().rowNum();
		int curY = maze.start().getPosition().colNum();
		pathOfInt[curX][curY] = 0;
		double counter = 0;
		while (!roomQ.isEmpty()) {

			curPos = roomQ.poll();
			tempRoom = (PathRoom) maze.roomAt(curPos);
			curX = curPos.rowNum();
			curY = curPos.colNum();

			for (int x = 0; x < 4; x++) {
				isDoor[x] = tempRoom.getSide(x).enter();
			}

			if (isDoor[0]) {
				if (pathOfInt[curX][curY] < pathOfInt[curX - 1][curY] + 1) {
					pathOfInt[curX - 1][curY] = pathOfInt[curX][curY] + 1;
					roomQ.offer(new Pos2D(curX - 1, curY));
				}
			}
			if (isDoor[1]) {
				if (pathOfInt[curX][curY] < pathOfInt[curX][curY + 1] + 1) {
					pathOfInt[curX][curY + 1] = pathOfInt[curX][curY] + 1;
					roomQ.offer(new Pos2D(curX, curY + 1));
				}
			}
			if (isDoor[2]) {
				if (pathOfInt[curX][curY] < pathOfInt[curX + 1][curY] + 1) {
					pathOfInt[curX + 1][curY] = pathOfInt[curX][curY] + 1;
					roomQ.offer(new Pos2D(curX + 1, curY));
				}
			}
			if (isDoor[3]) {
				if (pathOfInt[curX][curY] < pathOfInt[curX][curY - 1] + 1) {
					pathOfInt[curX][curY - 1] = pathOfInt[curX][curY] + 1;
					roomQ.offer(new Pos2D(curX, curY - 1));
				}
			}

		}

		curPos = (Pos2D)maze.finish().getPosition();
		Pos2D nextCurPos = (Pos2D)maze.finish().getPosition();
		curX = curPos.rowNum();
		curY = curPos.colNum();
		int value = 0;

		while (curX != maze.start().getPosition().rowNum()
				| curY != maze.start().getPosition().colNum()) {
			counter++;

			((PathRoom) (maze.roomAt(curPos))).setOnPath(true);

			value = pathOfInt[curX][curY];

			tempRoom = (PathRoom) (maze.roomAt(curPos));
			for (int x = 0; x < 4; x++) {
				isDoor[x] = tempRoom.getSide(x).enter();

			}
			if (isDoor[0] && pathOfInt[curX - 1][curY] == value - 1)
				nextCurPos = new Pos2D(curX - 1, curY);
			if (isDoor[1] && pathOfInt[curX][curY + 1] == value - 1)
				nextCurPos = new Pos2D(curX, curY + 1);
			if (isDoor[2] && pathOfInt[curX + 1][curY] == value - 1)
				nextCurPos = new Pos2D(curX + 1, curY);
			if (isDoor[3] && pathOfInt[curX][curY - 1] == value - 1)
				nextCurPos = new Pos2D(curX, curY - 1);
			if (curPos == nextCurPos) {
				System.out.println("No Path");
				break;
			}

			curPos = nextCurPos;
			curX = curPos.rowNum();
			curY = curPos.colNum();

		}

	}

	@Override
	public void displayPath() {
		boolean[] isDoor = new boolean[4];
		PathRoom tempRoom;
		Pos2D curPos = (Pos2D)maze.finish().getPosition();
		Pos2D nextCurPos = (Pos2D)maze.finish().getPosition();
		int curX = curPos.rowNum();
		int curY = curPos.colNum();
		int value = 0;

		int counter = 0;
		while (curX != maze.start().getPosition().rowNum()
				| curY != maze.start().getPosition().colNum()) {
			counter++;
			((PathRoom) (maze.roomAt(curPos))).setOnPath(true);

			value = pathOfInt[curX][curY];

			tempRoom = (PathRoom) (maze.roomAt(curPos));
			for (int x = 0; x < 4; x++) {
				isDoor[x] = tempRoom.getSide(x).enter();
			}
			if (isDoor[0] && pathOfInt[curX - 1][curY] == value - 1)
				nextCurPos = new Pos2D(curX - 1, curY);
			if (isDoor[1] && pathOfInt[curX][curY + 1] == value - 1)
				nextCurPos = new Pos2D(curX, curY + 1);
			if (isDoor[2] && pathOfInt[curX + 1][curY] == value - 1)
				nextCurPos = new Pos2D(curX + 1, curY);
			if (isDoor[3] && pathOfInt[curX][curY - 1] == value - 1)
				nextCurPos = new Pos2D(curX, curY - 1);
			if (curPos == nextCurPos) {
				System.out.println("No Path");
				break;
			}
			curPos = nextCurPos;
			curX = curPos.rowNum();
			curY = curPos.colNum();

		}

	}

}
