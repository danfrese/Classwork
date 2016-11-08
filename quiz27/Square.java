package quiz27;

/**
   A square shape composed of stacked unit squares like this:
   [][][]
   [][][]
   [][][]
   . . .
*/
public class Square {
	private int width;

	/**
	 * Constructs a square shape.
	 * 
	 * @param aWidth The width (and height) of the square
	 */
	public Square(int aWidth) {
		width = aWidth;
	}

	/**
	 * Computes the area of the Square.
	 * 
	 * @return the area
	 */
	public int getArea() {
		// Fix the missing "if" to check for the end of recursion.
		
		if (width == 1) {
			return 1;
		} else {
			Square smallerSquare = new Square(width - 1);
			int smallerArea = smallerSquare.getArea();
			return smallerArea + width + width - 1;
		}

	}
}
