package Chess;
/**
 * Coordinate.java
 * @author Andy Nguyen, Ethan Le, Pranit Agrawal, Krish Tandon, Ajay Saravanan
 * @since 5/14/2024
 * This class keeps track of and sets up the coordinates for the pieces and their movements
 */
public class Coordinate {
	private int rowCoord;
	private int colCoord;
	
	/**
     * Constructor method for the Coordinate and sets the row coordinate and column coordinate to the inputs
     * 
     * Postcondition: The row coordinate and the column coordinate are set to the inputs.
     * @param rowCoord - the coordinate of the row
     * @param colCoord - the coordinate of the column
	 */
	public Coordinate(int rowCoord, int colCoord) {
		this.rowCoord = rowCoord;
		this.colCoord = colCoord;
	}
	
	/**
     * Method that returns the row coordinate of the coordinate
     * 
     * @return rowCorod - the int value of the current row Coordinate
	 */
	public int getRowCoord() {
		return rowCoord;
	}
	
	/**
     * Method that returns the col coordinate of the coordinate
     * 
     * @return colCorod - the int value of the current column Coordinate
	 */
	public int getColCoord() {
		return colCoord;
	}
	
	/**
     * Method that checks whether or not the input coordinate is the same as the current coordinate
     * 
     * @param c - the Coordinate that is being compared
     * @return result - the boolean value that checks if the rowCoord and the colCoord are the same for both objects
	 */
	public boolean equals(Coordinate c) {
		boolean result = true;
		
		if (c.getRowCoord() != rowCoord || c.getColCoord() != colCoord) {
			result = false;
		}
		
		return result;
	}
	
	/**
     * Method that returns the coordinates in a ( , ) format
     * 
     * @return the string that represents the row coordinate and the column coordinate in a ( , ) format
	 */
	public String toString() {
		return "(" + rowCoord + ", " + colCoord + ")";
	}
	
}
