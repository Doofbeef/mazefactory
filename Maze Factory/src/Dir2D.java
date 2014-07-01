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
 *  Allows for developer to reference indexes based on the cardinal
 *  directions N,E,S,W as [N][W][S][E] in that order
 * 
 *--------------------------------------------------------------------------*/

public final class Dir2D implements Direction{
	public final static int N = 0;
	public final static int S = 2;
	public final static int E = 3;
	public final static int W = 1;
}
