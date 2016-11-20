/**
 * Cell helper class that creates cell object storing 2 integers that correspond to a cell's row and column.
 * Katrina Zhu
 * kz37
 */
public class Cell
    {
        public int row;
        public int col;
        public Cell(int r, int c) {
            row = r; col =c;
        }
        public int getX(){
        	return row;
        }
        public int getY(){
        	return col;
        }

    }
