package Chess;

import java.util.ArrayList;

public class CaptureTracker {
	private boolean whiteTurn;
	private ArrayList<Piece> blackCaptures;
	private ArrayList<Piece> whiteCaptures;
	
	public CaptureTracker() {
		whiteTurn = true;
		blackCaptures = new ArrayList<Piece>();
		whiteCaptures = new ArrayList<Piece>();	
	}
	
	public void addCapture(Piece p) {
		if (p.getIsWhite()) {
			whiteCaptures.add(p);
		} else {
			blackCaptures.add(p);
		}

	}
	
	public String getWhiteCaptures() {
		String result = "";
		
		for (Piece p: sortAlphOrder(whiteCaptures)) {
			result += p.getName().charAt(0) + p.getName().charAt(p.getName().length() - 1) + ", ";
		}
		
		if (result.length() > 2) {
			return result.substring(0, result.length() - 2);
			
		} else {
			return result;
			
		}
		
	}
	
	public String getBlackCaptures() {
		String result = "";
		
		for (Piece p: sortAlphOrder(blackCaptures)) {
			result += "" + p.getName().charAt(0) + p.getName().charAt(p.getName().length() - 1) + ", ";
		}
		
		if (result.length() > 2) {
			return result.substring(0, result.length() - 2);
			
		} else {
			return result;
			
		}
		
		
	}
	
	public boolean isWhiteTurn() {
		return whiteTurn;
	}
	
	public void switchTurn() {
		whiteTurn = !whiteTurn;
	}
	
	public ArrayList<Piece> sortAlphOrder(ArrayList<Piece> list){
		ArrayList<Piece> result = list;
		System.out.println(result);
		
		for (int i = 0; i < result.size(); i++) {
			for (int j = i + 1; j < result.size(); j++) {
				if (result.get(j).getOrder() < result.get(i).getOrder()) {
					Piece temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j, temp);
					
				}
				
			}
			
		}
		System.out.println(result);
		return result;
	}
	

	
}
