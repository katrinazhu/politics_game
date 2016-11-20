import java.awt.*;
import princeton.*;
public class Maze {
     public static final Color GRID_COLOR = Color.BLACK;
    
    public int mySize;
    
    public static double OFFSET = 1.0;
    
    // create maze of size cells by cells
    public Maze(int cells)
    {
        mySize = cells;
        StdDraw.setXscale(-OFFSET, cells + OFFSET);
        StdDraw.setYscale(-OFFSET, cells + OFFSET);
    }
    
    // draw a horizontal line at the upper edge of cell (row, col)
    public void drawHorizontal(int row, int col)
    {
        StdDraw.setPenColor(GRID_COLOR);
        StdDraw.line(col ,  mySize - row,
               (col+1),  mySize - row);
    }
    // draw a vertical line at the left edge of cell (row, col)
    public void drawVertical(int row, int col)
    {
        StdDraw.setPenColor(GRID_COLOR);
        StdDraw.line(col, mySize - row,
                col , mySize - (row + 1));
    }
    
    public static void main(String[] args)
    {
        final int CELLS = 10;
        Maze maze = new Maze(CELLS);
        for (int i=0; i < CELLS; i++) {
            for (int j=0; j < CELLS; j++)
            {
                maze.drawHorizontal(i, j);
                maze.drawVertical(i, j);
            }
            
        }
        for(int i=0; i<CELLS; i++){
        	maze.drawHorizontal(CELLS, i);
        	maze.drawVertical(i, CELLS);
        }

    }
 }