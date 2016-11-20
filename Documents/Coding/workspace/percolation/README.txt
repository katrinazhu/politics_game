Name: Katrina Zhu
NetID: kz37
Hours to complete assignment: 10
Collaborators:

Resources Used: Percolation video and links on assignment page

/**********************************************************************
 *  Describe how you implemented PercolationUF.java. How did you check
 *  whether the system percolates?
 **********************************************************************/
I set the top cells to point to a virtual source, the bottom cells to point to a virtual sink, and if the source and sink
were connected, I knew the system percolated.


/**********************************************************************
 *  Using Percolation with DFS, give a formula (using Big-Oh 
 *  notation) for the running time (in seconds) of PercolationStats.java
 *  as a function of N and T. Estimate the largest experiment you could
 *  perform in 1 { minute, day, year } assuming your compute has
 *  enough memory.
 **********************************************************************/

measurements:

 N          time (seconds)
------------------------------
20			.091/T
40			.417/T
100			12.152/T


running time as a function of N and T:  ~ O(N) (approximately)

1 minute:
1 day:
1 year:


/**********************************************************************
 *  Repeat the previous question, but use quick find 
 **********************************************************************/

measurements:

 N          time (seconds)
------------------------------
20			.188/T
40			1.019/T
100			17.9/T


running time as a function of N and T:  O(N^2)

1 minute: N = 200
1 day: N = 12,000
1 year: N = 720,000



/**********************************************************************
 *  Repeat the previous question, but use weighted quick union with
 *  path compression.
 **********************************************************************/

measurements:

 N          time (seconds)
------------------------------
20			.085/T
40			.57/T
60			5.779/T s
100			55.412/T s



running time as a function of N and T:  O(N^3)

1 minute: N = 100
1 day: N = 200
1 year: N = 2,000


/**********************************************************************
 *  How many bytes does your Percolation.java object use as a function
 *  of N? Use big-Oh notation to simplify your answer.
 **********************************************************************/

using dfs: O(N)

using quick find: O(N^2)

using weighted quick union with path compression: O(N^3)



/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/
Weighted quick union with path compression is not as efficient as it should be.



/**********************************************************************
 *  List whatever help (if any) that you received, including help
 *  from other students, staff members or UTAs.
 **********************************************************************/
Shelley Vohr helped me a little bit with PercolationVisualizer.

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/
The source/sink was difficult for me, as I was unsure what type of objects they were and where I should put them;
I kept getting an indexoutofbounds or nullpointer exception.



/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
I actually enjoyed this assignment; I would have liked a little more guidance but I thought it was a good critical thinking exercise.
