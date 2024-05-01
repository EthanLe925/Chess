package Chess;

public class ChessBoard {
	private Tile[][] board = new Tile[8][8];
	
	public ChessBoard() {
		
		for (int row = 0; row < board.length; row++) {
			if (row % 2 == 0) {
				for (int col = 0; col < board[row].length; col++) {
					if(col % 2 == 0) {
						board[row][col] = new Tile(true, false, null);
					} else {
						board[row][col] = new Tile(false, false, null);
					}
				}
			} else {
				for (int col = 0; col < board[row].length; col++) {
					if (col % 2 == 1) {
						board[row][col] = new Tile(true, false, null);
					} else {
						board[row][col] = new Tile(false, false, null);
					}
				}
			}
		}
		
		for (int col = 0; col < board[0].length; col++) {
			board[1][col].addPiece(new Pawn(false, 1, col));
			board[6][col].addPiece(new Pawn(true, 1, col));
		}
		
		
		
	}
	
	public void printBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[row][col].isOccupied()) {
					System.out.print(board[row][col].getPiece());
				} else {
					System.out.print("null");
				}
			}
			System.out.println();
		}
	}
	
	
}
