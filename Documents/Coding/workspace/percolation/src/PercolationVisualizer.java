import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

import princeton.*;

/**
 * Animates the results of opening sites in a percolation system
 * 
 * From Princeton COS 226, Kevin Wayne 
 * Modified by Owen Astrachan, January 2008
 * Modified by Jeff Forbes, October 2008
 */

public class PercolationVisualizer {
    public static int RANDOM_SEED = 1234; 
    public static Random ourRandom = new Random(RANDOM_SEED);
    public static void drawGrid(IPercolate perc, int size){
    	for(int x = 0; x<size; x++){
    		for(int y = 0; y<size; y++){
    			if(perc.isFull(x, y))
    				draw(x, y, size, Color.CYAN);	
    			if(perc.isOpen(x, y))
    				draw(x, y, size, Color.WHITE);	
    		}
    	}
    }
    /**
     * Draws a square of color c at (row,col) on a N*N grid
     */
    public static void draw(int row, int col, int N, Color c) {
       StdDraw.setPenColor(c);
       StdDraw.filledSquare(col + .5, N - row - .5, .45);
    }
    
	public static void main(String[] args) {
		// Animate 20 times a second if possible
		final int DEFAULT_DELAY = 1000 / 20; // in milliseconds
		String input = "20";   //default
	    if (args.length == 1)  // use command-line arguments for testing/grading
	    	input = args[0];
	    else
	    	input = JOptionPane.showInputDialog("Enter N", "20");
		int N = Integer.parseInt(input); // N-by-N lattice

		// set x- and y-scale
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, N);
		// draw a black box
		StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledSquare(N / 2.0, N / 2.0, N / 2.0);
        //this arraylist uses helper Cell class to store coordinates of the grid
        ArrayList<Cell> coordinates = new ArrayList<Cell>();
        
		//IPercolate perc = new PercolationDFS(N);
		for(int x = 0; x<N; x++){
        	for(int y = 0; y<N; y++){
        		coordinates.add(new Cell(x, y));
        	}
        }
		//IPercolate perc = new PercolationUF(N, new QuickUWPC(N+2));
		IPercolate perc = new PercolationUF(N, new QuickFind(N*N+2));
		//randomizes the coordinates of the grid
		Collections.shuffle(coordinates, ourRandom);
		for(int x = 0; x<coordinates.size(); x++){
			//opens a random coordinate of the grid
			perc.open(coordinates.get(x).row, coordinates.get(x).col);
			drawGrid(perc, N);
			StdDraw.show(DEFAULT_DELAY);
			//checks to see if the grid percolates
			if(perc.percolates()){	
				drawGrid(perc, N);
				StdDraw.show(DEFAULT_DELAY);
				//if the grid percolates, stop the loop
				break;
			}
			drawGrid(perc, N);
			StdDraw.show(DEFAULT_DELAY);

		}
	}
}
