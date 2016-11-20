import java.util.ArrayList;
import java.util.List;


public class GoodWordOnBoardFinder implements IWordOnBoardFinder{
	/**
	 * returns true iff a word can be created on this board
	 * starting from (row,col) for all the substring of word
	 * beginning at index
	 */
	public boolean findHelper(String word, int index, int row, int col, BoggleBoard board, List<BoardCell> list){
		if(index>=word.length())
			return true;
		if(row<0||col<0||row>=board.size()||col>=board.size())
			return false;
		String current = board.getFace(row, col);
		if(!word.substring(index, index+current.length()).equals(current))
			return false;
		if(list.contains(new BoardCell(row, col)))
			return false;
		list.add(new BoardCell(row, col));
		if(findHelper(word, index+current.length(), row-1, col-1, board, list)||findHelper(word, index+current.length(), row-1, col, board, list)
				||findHelper(word, index+current.length(), row-1, col+1, board, list)||findHelper(word, index+current.length(), row, col-1, board, list)
				||findHelper(word, index+current.length(), row, col+1, board, list)||findHelper(word, index+current.length(), row+1, col-1, board, list)
				||findHelper(word, index+current.length(), row+1, col, board, list)||findHelper(word, index+current.length(), row+1, col+1, board, list)){
			return true;
		}
		else{
			list.remove(new BoardCell(row, col));
			return false;
		}
	}
	
	@Override
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
	
		ArrayList<BoardCell> list = new ArrayList<BoardCell>();
		for(int r=0; r < board.size(); r++){
			for(int c=0; c < board.size(); c++){ 
				if (findHelper(word, 0, r, c, board, list)){
					return list;
				}
			}
		}
		list.clear();
		return list;	
	}
}
