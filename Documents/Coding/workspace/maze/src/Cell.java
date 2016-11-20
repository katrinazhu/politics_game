/**
 * Represents a Grid Cell in a Maze
 * @author forbes
 *
 */
public class Cell
{
    public int row;
    public int col;
    
    // Width of the grid in number of cells
    private int myWidth; 
    
    public Cell(int r, int c, int size) {
        row = r; col =c; myWidth= size;
    }
    
    public int getIndex() {
        return row*myWidth + col;
    }
}
