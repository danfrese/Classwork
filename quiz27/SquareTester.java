package quiz27;

public class SquareTester {
	
	public static void main(String[] args) {
		
		Square t = new Square(10);
		int area = t.getArea();
		System.out.println("Area: " + area);
		System.out.println("Expected: 100");
	}
}