package Chess;

public class ChessDriver {

	public static void main(String[] args) {
		ChessBoard myBoard = new ChessBoard();
		
		myBoard.printBoard();
		myBoard.displayBoard();
		myBoard.movePiece(0, 0, 2, 0);
		myBoard.displayBoard();

	}

}
