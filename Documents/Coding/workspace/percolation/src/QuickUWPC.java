	/**
     * Class that improves on the efficiency of QuickFind by incorporating weighted quick union with path compression
     */
public class QuickUWPC implements IUnionFind{
	int[]id = null;
	int[]sz = null;
	int myComponents;
	/**
     * calls on initialize method to create QuickUWPC object of size n
     */
	public QuickUWPC(int N){
		initialize(N);
	}
	/**
     * initializes QuickUWPC object
     */
	public void initialize(int n) {
		id = new int[n*n];
		sz = new int[n*n];
		for (int i = 0; i < n*n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
		myComponents = n*n;
		
	}

	/**
     * returns number of components in grid
     */
	public int components() {
		return myComponents;
	}

	/**
     * finds the root of the array id and sets it to be the root of given value x
     * this is responsible for the path compression
     */
	public int find(int x) {
		int root = x;
		while (root!=id[root])
			root=id[root];
		//root is the root
		while(x!= root){
			int temp = id[x];
			id[x] = root;
			x = temp;
		}
		return root;

	}

	/**
     * calls find to see if the objects have the same root
     */
	public boolean connected(int p, int q) {
		if(find(p)==find(q))
			return true;
		return false;
	}

	/**
     * unions two objects so they now refer to the same thing. 
     */
	public void union(int p, int q) {
		int rootP = find(p);
        int rootQ = find(q);
        if (connected(rootP, rootQ)) 
        	return;
        //this is responsible for the weighted quick union. Unions them by having the smaller object point to the bigger one
        if (sz[rootP] < sz[rootQ]) { 
        	id[rootP] = rootQ; 
        	sz[rootQ] += sz[rootP]; 
        	}
        else { 
        	id[rootQ] = rootP; 
        	sz[rootP] += sz[rootQ]; 
        	}
        myComponents--;
		
	}
	
}
