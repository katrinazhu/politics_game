import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

import princeton.*;

/**
 * Print statistics on Percolation: prompts the user for N and T, performs T
 * independent experiments on an N-by-N grid, prints out the 95% confidence
 * interval for the percolation threshold, and prints mean and std. deviation
 * of timings
 * 
 * @author Kevin Wayne
 * @author Jeff Forbes
 */

public class PercolationStats {
	/**
     * Private helper method that calculates standard deviation given an array, 
     * the mean of the array, and the number of elements in the array.
     */
	private static double stDev(double[]x, double mean, int T){
		double sumOfSquares = 0.0;
		for(int y = 0; y<x.length; y++){
			sumOfSquares += (x[y]-mean)*(x[y]-mean);
		}
		double variance = sumOfSquares/(double)(T-1);
		return (double)Math.sqrt(variance);
	}
	public static void main(String[] args) {
		int N, T;
		if (args.length == 2) { // use command-line arguments for testing/grading
			N = Integer.parseInt(args[0]);
			T = Integer.parseInt(args[1]);
		} 
		//reads the user's input and sets the values of the size of the grid and the number of repetitions of percolation
		else {
			String input = JOptionPane.showInputDialog("Enter N and T",
					"20 100");
			String[]i = input.split(" ");
			N = Integer.parseInt(i[0]);
			T = Integer.parseInt(i[1]);
		}

		// tests percolation T times, recording times and percent of cells opened each time
		ArrayList<Cell> coordinates = new ArrayList<Cell>();	
		double[]time = new double[T];
		double[]count = new double[T];
				
		for(int i = 0; i<T; i++){
			for(int x = 0; x<N; x++){
	        	for(int y = 0; y<N; y++){
	        		coordinates.add(new Cell(x, y));
	        	}
	        }
			
			Collections.shuffle(coordinates);
			//starts the time
			long start = System.currentTimeMillis();
			//IPercolate perc = new PercolationDFS(N);
			//IPercolate perc = new PercolationUF(N, new QuickUWPC(N+2));
			IPercolate perc = new PercolationUF(N, new QuickFind(N*N+2));
			double numSquares = 0;
			for(int x = 0; x<coordinates.size(); x++){
				perc.open(coordinates.get(x).row, coordinates.get(x).col);	
				numSquares++; 
				if(perc.percolates()){
					break;
				}
			}
			//records time after percolation is performed
			long end = System.currentTimeMillis();
			double totalTime = (end - start)/1000.0;
			time[i]=totalTime;
			count[i]=numSquares/(double)(N*N);

		}
		// calculating sums of the arrays
		double wholeTime = 0.0;
		for(int x = 0; x<T; x++){
			wholeTime+=time[x];
		}
		double wholeCount = 0.0;
		for(int x = 0; x<T; x++){
			wholeCount += count[x];
		}
		//calculating means and standard deviations
		double mean1 = (double)wholeCount/(double)T;
		double stDev1 = stDev(count, mean1, T);
		double mean2 = wholeTime/(double)T;
		double stDev2 = stDev(time, mean2, T);
		//printing statistics
		System.out.println("mean percolation threshold = " + mean1);
		System.out.println("stdev = " + stDev1);
		System.out.println("95% confidence interval = [" + (mean1 - (1.96*stDev1)/(double)Math.sqrt(T)) + ", " + (mean1 + (1.96*stDev1)/(double)Math.sqrt(T)) + "]");
		System.out.println();
		System.out.println("total time = " + wholeTime + "s");
		System.out.println("mean time per experiment = " + mean2 + "s");
		System.out.println("stdev = " + stDev2);
	}
}
