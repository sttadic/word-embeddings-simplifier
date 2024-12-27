package ie.atu.sw;

import static java.lang.System.out;

import java.util.Scanner;

public class InputHandler {
	private String input;
	private Scanner scan;

	public InputHandler() {
		this.scan = new Scanner(System.in);
	}

	
	public int processMenuSelection() {
		int choice;
		try {
			choice = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			return -1;
		}
		return choice;
	}

	public String setPath(String currentPath) {
		int counter = 3;
		while (true) {
			input = scan.nextLine().trim();
			if (input.isEmpty()) {
				out.println(ConsoleColour.RED + "Invalid input! Please try again. Path will reset to "
						+ ConsoleColour.GREEN + currentPath + ConsoleColour.RED + " after " + (counter - 1)
						+ " more invalid attempt(s)." + ConsoleColour.WHITE);
				if (--counter <= 0) return currentPath;
				continue;
			}
			return input;
		}
	}

	public String processAlgorithmSelection() {
		int choice = validateAlgoSelection();
		switch (choice) {
		case 1 -> {
			return "Cosine Similarity";
		}
		case 2 -> {
			return "Euclidean Distance";
		}
		case 3 -> {
			return "Dot Product";
		}
		default -> {
			return "Combine All (Cosine Similarity, Euclidean Distance, Dot Product)";
		}
		}
	}

	private int validateAlgoSelection() {
		int choice = 0;
		int counter = 3;
		while (counter > 0) {
			var errorMessage = ConsoleColour.RED + "Invalid input! Please try again. Option 4," + ConsoleColour.GREEN
					+ " Combine All Algorithms" + ConsoleColour.RED + " will be selected automatically after "
					+ --counter + " more invalid attempt(s).";
			out.print(ConsoleColour.WHITE_BOLD + "Select Option (1-4) > ");
			try {
				choice = Integer.parseInt(scan.nextLine());
				if (choice >= 1 && choice <= 4)
					break;
				out.println(errorMessage);
			} catch (NumberFormatException e) {
				out.println(errorMessage);
			}
		}
		return choice;
	}

}
