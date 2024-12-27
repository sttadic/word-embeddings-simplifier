package ie.atu.sw;

import static java.lang.System.out;

import java.util.Scanner;

public class InputHandler {
	private String input;
	private Scanner scan;

	public InputHandler() {
		this.scan = new Scanner(System.in);
	}

	public String setPath(String currentPath) {
		int counter = 3;
		while (true) {
			input = scan.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println(ConsoleColour.RED + "Invalid input! Please try again! " + (counter - 1)
						+ " tries left." + ConsoleColour.WHITE);
				if (--counter <= 0)
					return currentPath;
				continue;
			}
			return input;
		}
	}

	public String selectComparisonAlgorithm() {
		int choice = validateAlgoSelection();
		switch (choice) {
			case 1 -> {
				return "Dot Product";
			}
			case 2 -> {
				return "Euclidean Distance";
			}
			case 3 -> {
				return "Cosine Similarity";
			}
			default -> {
				return "Combine All (Cosine Similarity, Euclidean Distance, Dot Product)";
			}
		}
	}
	
	private int validateAlgoSelection() {
		int choice = -1;
		while (true) {
			out.print(ConsoleColour.WHITE_BOLD + "Select Option (1-4) > ");
			try {
				choice = Integer.parseInt(scan.nextLine());
				if (choice >= 1 && choice <= 4)
					break;
				out.println(ConsoleColour.RED + "Invalid input! Please try again.");
			} catch (Exception e) {
				out.println(ConsoleColour.RED + "Invalid input! Please try again.");
			}
		}
		return choice;
	}
	
}
