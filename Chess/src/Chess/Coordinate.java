package Chess;

public class Coordinate {
	private int rowCoord;
	private int colCoord;
	
	public Coordinate(int rowCoord, int colCoord) {
		this.rowCoord = rowCoord;
		this.colCoord = colCoord;
	}
	
	public int getRowCoord() {
		return rowCoord;
	}
	
	public int getColCoord() {
		return colCoord;
	}
	
	public boolean equals(Coordinate c) {
		boolean result = true;
		
		if (c.getRowCoord() != rowCoord || c.getColCoord() != colCoord) {
			result = false;
		}
		
		return result;
	}
	
	public String toString() {
		return "(" + rowCoord + ", " + colCoord + ")";
	}
	
}
