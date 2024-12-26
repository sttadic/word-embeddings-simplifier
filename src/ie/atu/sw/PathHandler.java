package ie.atu.sw;

import java.util.Scanner;

public class PathHandler {
	private String input;
	private Scanner scan;

	public PathHandler() {
		this.scan = new Scanner(System.in);
	}

	public String setPath(String currentPath) {
		int counter = 3;
		while (true) {
			input = scan.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println(ConsoleColour.RED + "Invalid input! Please try again! " + (counter - 1)
						+ " tries left." + ConsoleColour.WHITE);
				if (--counter <= 0) return currentPath;
				continue;
			}
			scan.close();
			return input;
		}
	}
}
