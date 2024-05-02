package Chess;
import java.awt.*;
import javax.swing.*;

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
		
		board[0][0].addPiece(new Rook(false, 0, 0));
		board[0][7].addPiece(new Rook(false, 0, 7));
		board[7][0].addPiece(new Rook(true, 7, 0));
		board[7][7].addPiece(new Rook(true, 7, 7));
		
		board[0][1].addPiece(new Knight(false, 0, 1));
		board[0][6].addPiece(new Knight(false, 0, 6));
		board[7][1].addPiece(new Knight(true, 7, 1));
		board[7][6].addPiece(new Knight(true, 7, 6));
		
		board[0][2].addPiece(new Bishop(false, 0, 2));
		board[0][5].addPiece(new Bishop(false, 0, 5));
		board[7][2].addPiece(new Bishop(true, 7, 2));
		board[7][5].addPiece(new Bishop(true, 7, 5));
		
		board[0][3].addPiece(new Queen(false, 0, 3));
		board[7][3].addPiece(new Queen(true, 7, 3));
		
		board[0][4].addPiece(new King(false, 0, 4));
		board[7][4].addPiece(new King(true, 7, 4));
		
		
		
		
		
		
	}
	
	public void printBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				System.out.print(board[row][col].getColor());
//				if (board[row][col].isOccupied()) {
//					System.out.print(board[row][col].getPiece());
//				} else {
//					System.out.print("null");
//				}
			}
			System.out.println();
		}
	}
	
	public void displayBoard() {
		JPanel chessBoard = new JPanel();
		chessBoard.setBounds(0, 0, 200, 100);
		
		GridLayout layout = new GridLayout(8,8);
		chessBoard.setLayout(layout);
		
		Icon black = new ImageIcon("black_square.jpg");
		Icon white = new ImageIcon("white_square.jpg");
		
//		JButton test1 = new JButton(black);
//		board.add(test1);
//		
//		JButton test2 = new JButton(white);
//		board.add(test2);
//		
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {		
				JButton button = new JButton(board[row][col].getIcon());
				button.setPreferredSize(new Dimension(10,10));
				chessBoard.add(button, row, col);
			}
		}

		JFrame frame = new JFrame("Test Frame");
		frame.setSize(1000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.add(chessBoard);
		
		
	}
	
	
}
