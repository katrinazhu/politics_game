import java.util.Arrays;

import princeton.*;

/**
 * Simulate percolation thresholds for a grid-base system using depth-first-search,
 * aka 'flood-fill' techniques for determining if the top of a grid is connected
 * to the bottom of a grid.
 * <P>
 * Modified from the COS 226 Princeton code for use at Duke. The modifications
 * consist of supporting the <code>IPercolate</code> interface, renaming methods
 * and fields to be more consistent with Java/Duke standards and rewriting code
 * to reflect the DFS/flood-fill techniques used in discussion at Duke.
 * <P>
 * @author Kevin Wayne, wayne@cs.princeton.edu
 * @author Owen Astrachan, ola@cs.duke.edu
 * @author Jeff Forbes, forbes@cs.duke.edu
 */


public class PercolationDFS implements IPercolate {
	// possible instance variable for storing grid state
	public int[][] myGrid;
    /**
     * Initialize a grid so that all cells are blocked.
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFS(int n) {
    	myGrid = new int [n][n];
    	//fills each cell of the array in the array to be blocked
    	for(int x = 0; x<n; x++)
    		Arrays.fill(myGrid[x], BLOCKED);
    }
    /**
     * Sets a specific cell to open
     */
	public void open(int i, int j) {
		myGrid[i][j] = OPEN;
	}
	/**
     * Checks to see if a particular cell is open
     */
	public boolean isOpen(int i, int j) {
		if(myGrid[i][j] == OPEN)
			return true;
		return false;
	}
	/**
     * Checks to see if a particular cell if full
     */
	public boolean isFull(int i, int j) {
		if(myGrid[i][j] == FULL)
			return true;
		return false;
	}
	/**
     * Returns true if the grid percolates
     */
    public boolean percolates() {
    	//this for loop sets any full cells in the grid back to open
    	for(int x = 0; x<myGrid.length; x++){
			for(int y = 0; y<myGrid[0].length; y++){
				if(isFull(x, y))
					open(x, y);
			}
		}
    	//iterates through the first row of the grid, calling dfs, then checking to see if any cells on the last row are full
    	//if any last row cells are full, percolation was successful, and we return true
    	for(int col = 0; col<myGrid[0].length; col++){   		
    		dfs(0, col);
    		for(int c = 0; c<myGrid[0].length; c++){
    			if(isFull(myGrid.length-1, c))
    				return true;
    		}
    	}
        return false;
    }

    /**
     * Private helper method to mark all cells that are open and reachable
     * from (row,col).
     * 
     */
    private void dfs(int row, int col) {
        //if given cell is out of bounds, return
    	if(row<0 || row >=myGrid.length || col < 0 || col >= myGrid[0].length){
    		return;
    	}
    	//if given cell is not open, return
    	if(isFull(row, col)||!isOpen(row, col))
    		return;
    	//sets given cell to full, then use recursion to check surrounding cells
    	myGrid[row][col] = FULL;
    	dfs(row-1, col);
    	dfs(row, col-1);
    	dfs(row+1, col);
    	dfs(row, col+1);
    }
}
