import java.util.ArrayList;

public class BoardFirstAutoPlayer extends AbstractAutoPlayer {
    BoggleBoard b = new BoggleBoard (new String[0]);
    ILexicon l = new BinarySearchLexicon();
    int length = 0;
	ArrayList<String> found = new ArrayList<String>();

    @Override
    public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
    	b = board;
    	l = lex;
    	length = minLength;
		for(int r=0; r < board.size(); r++){
			for(int c=0; c < board.size(); c++){ 
				find(r, c, new StringBuilder(""), new ArrayList<BoardCell>());				
			}
		}
	}    
    
    private void find (int row, int col, StringBuilder prefix, ArrayList<BoardCell> visited){
    	if(row<0||row>=b.size()||col<0||col>=b.size()){
    		return;
    	}
    	StringBuilder current = prefix.append(b.getFace(row, col));
    	System.out.println(current);
    	if(visited.contains(new BoardCell(row, col))){			
			current = prefix;
			return;
    	}
    	if(l.wordStatus(current).equals(LexStatus.NOT_WORD)){    		
    		current = prefix;
    		return;
    	}
    	if(l.wordStatus(current).equals(LexStatus.WORD)&&current.length()>=length){
    		String word = current.toString();
    		add(word);
    	}
    	visited.add(new BoardCell(row, col));
    	ArrayList<BoardCell> temp = visited;
    	find(row+1, col+1, current, visited);
    	find(row+1, col, current, visited);
    	find(row+1, col-1, current, visited);
    	find(row, col+1, current, visited);
    	find(row, col-1, current, visited);
    	find(row-1, col-1, current, visited);
    	find(row-1, col, current, visited);
    	find(row-1, col+1, current, visited);
    	if(temp.equals(visited)){
    		visited.remove(new BoardCell(row, col)); 
    		current = prefix;
    	}
    	//out of bounds and backtrack base cases apply
    	//add thing to prefix, if it's not a prefix, stop?
    	//add it to the list using the add method
    }
}
