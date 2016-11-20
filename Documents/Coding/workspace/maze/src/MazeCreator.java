import javax.swing.JOptionPane;

import princeton.StdDraw;

import java.awt.Point;
import java.util.*;

public class MazeCreator
{
    private int myWidth;

    private int myHeight;

    private LinkedList<Edge> myEdges; // List of interior edges

    private IUnionFind myUF;

    public static final int DELAY = 1000 / 20;

    public MazeCreator(int width, int height, IUnionFind uf)
    {
    	for (int i=0; i < width; i++) {
            for (int j=0; j < width; j++)
            {
                Edge e = new Edge(i, j, true, 100, 100);
                Edge f = new Edge (i, j, false, 100, 100);
                e.draw();
                f.draw();
            }
            
        }
        for(int i=0; i<width; i++){
        	Edge e = new Edge(width, i, true, 100, 100);
        	e.draw();
        	Edge f = new Edge(i, width, false, 100, 100);
        	f.draw();
        }

    }

    public void step()
    {
        Collections.shuffle(myEdges);
        Edge e = myEdges.get(1);
        if(!myUF.connected(myUF.find(e.borderCell1().getIndex()), myUF.find(e.borderCell2().getIndex()))){
        	e.erase();
        	myUF.union(myUF.find(e.borderCell1().getIndex()), myUF.find(e.borderCell2().getIndex()));
        }
    	// TODO: complete step

        // Grab random edge

        // If the two cells (call them x and y) adjacent to this wall
        // are in different sets, remove the wall and perform a union
        // on x and y

        // Redraw as necessary

    }

    /*
     * Returns true if there is a path between any grid squares
     */
    public boolean isDone()
    {
        if(myUF.components()==1)
        	return true;
        return false;
    }

    private void createInnerEdges()
    {
        // TODO complete createInnerEdges
    }

    private void drawOuterEdges()
    {
        // TODO complete drawOuterEdges
    }

    public static void main(String[] args)
    {
        int gridSize = Integer.parseInt(JOptionPane.showInputDialog("Enter N",
                "10"));
        MazeCreator c = new MazeCreator(gridSize, gridSize, new QuickFind(
                gridSize * gridSize));
        while (!c.isDone())
            c.step();
    }

}
