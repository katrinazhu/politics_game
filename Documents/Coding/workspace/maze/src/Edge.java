import java.awt.Color;
import java.awt.Dimension;

import princeton.StdDraw;

/**
 * Represents an internal edge in the 
 * @author forbes
 *
 */
public class Edge
{
    public static final Color GRID_COLOR = Color.BLACK;
    public static final Color BG_COLOR = Color.WHITE;

    public int row;
    public int col;
    public boolean isHoriz;

    private Dimension myGridSize;
    private Cell myBorder1, myBorder2;

    public Edge(int r, int c, boolean horz, int gridWidth, int gridHeight)
    {
        row = r;
        col = c;
        isHoriz = horz;
        myGridSize = new Dimension(gridWidth, gridHeight);

        if (isHoriz) // edge is on top of (row,col)
            myBorder1 = new Cell(row - 1, col, gridWidth);
        else
            // edge is to left of (row, col)
            myBorder1 = new Cell(row, col - 1, gridWidth);
        myBorder2 = new Cell(row, col, gridWidth);

    }
   
    // Interior Edges have two bordering cells
    public Cell borderCell1() {
        return myBorder1;
    }
    
    public Cell borderCell2() {
        return myBorder2;
    }
    
    public String toString()
    {
        return (isHoriz ? "H" : "V") + ":(" + row + "," + col + ")";
    }
    
    /*
     * Draw this edge in c Color
     */
    public void draw(Color c)
    {
        StdDraw.setPenColor(c);
        if (isHoriz)
            StdDraw.line(col, myGridSize.getHeight() - row, col + 1, myGridSize
                    .getHeight()
                    - row);
        else
            StdDraw.line(col, myGridSize.getHeight() - row, col, myGridSize
                    .getHeight()
                    - (row + 1));
    }
    
    // draw edge with default color
    public void draw()
    {
        draw(GRID_COLOR);
    }

    // erase edge by drawing over it in the backgroundcolor
    public void erase()
    {
        draw(BG_COLOR);

    }

}
