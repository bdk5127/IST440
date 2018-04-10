package ist.barline.input;

import java.util.Scanner;

public class UserInput {
	public static String getBarName() {
		Scanner scanner = new Scanner(System.in);
		String barName;
		
		System.out.println("Select a bar: ");
		barName = scanner.nextLine();
		
		return barName;
		
	}
	
	public static String selectMethod() {
		
		return "";
	}
}
