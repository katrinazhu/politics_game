/**
 * Simulate a system to see its Percolation Threshold, but use a UnionFind
 * implementation to determine whether simulation occurs. The main idea is that
 * initially all cells of a simulated grid are each part of their own set so
 * that there will be n^2 sets in an nxn simulated grid. Finding an open cell
 * will connect the cell being marked to its neighbors --- this means that the
 * set in which the open cell is 'found' will be unioned with the sets of each
 * neighboring cell. The union/find implementation supports the 'find' and
 * 'union' typical of UF algorithms.
 * <P>
 * 
 * @author Owen Astrachan
 * @author Jeff Forbes
 *
 */

public class PercolationUF implements IPercolate {
	private final int OUT_BOUNDS = -1;
	int[][]myGrid;
	IUnionFind myUniter;
	int source;
	int sink;
	
	/**
	 * Constructs a Percolation object for a nxn grid that uses unionThing to
	 * store sets representing the cells and the top/source and bottom/sink
	 * virtual cells
	 */
	public PercolationUF(int n, IUnionFind unionThing) {
		//initializing nxn grid with all blocked cells
		myGrid=new int[n][n];
		for(int x = 0; x<n ;x++){
			for(int y = 0; y<n; y++)
				myGrid[x][y]=BLOCKED;
		}
		//Initializing source and sink values
		source=n*n;
		sink = n*n-1;
		myUniter = unionThing;
		//unioning everything in the grid's first row with the source
		for(int x = 0; x<n; x++){
			myUniter.union(source, getIndex(0,x));
		}
		//unioning everything in the grid's last row with the sink
		for(int x = 0; x<n; x++){
			myUniter.union(sink, getIndex(n-1,x));
		}
	}

	/**
	 * Return an index that uniquely identifies (row,col), typically an index
	 * based on row-major ordering of cells in a two-dimensional grid. However,
	 * if (row,col) is out-of-bounds, return OUT_BOUNDS.
	 */
	public int getIndex(int row, int col) {
		//return a unique index that identifies this cell
		//based on row major ordering

		if(row<0||row>=myGrid.length||col<0||col>=myGrid[0].length)
			return OUT_BOUNDS;
		return row*myGrid[row].length + col;
	}

	public void open(int i, int j) {
		//check that other cells are not off the grid
		if(i<0||i>=myGrid.length||j<0||j>=myGrid[0].length)
			return;
		//open the cell
		myGrid[i][j]=OPEN;
		//union with 4 other surrounding cells
		connect(i, j);
		
	}
	/**
     * checks if a specific cell is open
     */
	public boolean isOpen(int i, int j) {
		if(myGrid[i][j]==OPEN)
			return true;
		return false;
	}
	/**
     * checks if a specific cell is full
     */
	public boolean isFull(int i, int j) {
		if(myGrid[i][j]==FULL)
			return true;
		return false;
	}
	/**
     * check if the grid percolates
     */
	public boolean percolates() {
		//check if source and sink are connected
		if(myUniter.connected(source, sink))
			return true;
		return false;
	}

	/**
	 * Connect new site (row, col) to all adjacent open sites
	 */
	private void connect(int row, int col) {

		//if cell is out of bounds, return
		if(row<0||row>=myGrid.length||col<0||col>=myGrid[0].length)
			return;
		//unions with 4 adjacent cells provided they are in the grid and not blocked
		if(!(row-1<0)&&(isOpen(row-1, col)||isFull(row-1, col)))
			myUniter.union(getIndex(row, col), getIndex(row-1, col));
		if(!(row+1>=myGrid.length)&&(isOpen(row+1, col)||isFull(row+1, col)))
			myUniter.union(getIndex(row, col), getIndex(row+1, col));
		if(!(col-1<0)&&(isOpen(row, col-1)||isFull(row, col-1)))
			myUniter.union(getIndex(row, col), getIndex(row, col-1));
		if(!(col+1>=myGrid[0].length)&&(isOpen(row, col+1)||isFull(row, col+1)))
			myUniter.union(getIndex(row, col), getIndex(row, col+1));
		//sets this cell and all other cells that are connected to the source as full
		if(myUniter.connected(getIndex(row, col), source))
			myGrid[row][col]=FULL;
		for(int x = 0; x<myGrid.length; x++){
			for(int y = 0; y<myGrid.length; y++){
				if(myUniter.connected(getIndex(x, y), source)&&myGrid[x][y]!=BLOCKED)
					myGrid[x][y]=FULL;
			}
		}
		
}

}
