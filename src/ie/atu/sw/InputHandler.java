package ie.atu.sw;

import static java.lang.System.out;
import java.util.Scanner;

/**
 * The class InputHandler is responsible for managing user input from the
 * console. It provides logic for processing and validating menu options and
 * algorithm selection, file paths, and closing Scanner resource.
 */
public class InputHandler {
	private static final int MAX_ATTEMPTS = 3;
	private String input;
	private Scanner scan;

	/**
	 * Instantiates InputHandler and initializes the Scanner instance for reading
	 * user input from the console.
	 */
	public InputHandler() {
		this.scan = new Scanner(System.in);
	}

	/**
	 * Processes menu's option selection by reading an integer from the console.
	 * 
	 * @return the user's choice as an integer, or -1 for invalid input
	 */
	// O(1) - all operations within method are constant time
	public int processMenuSelection() {	
		int choice;
		try {
			choice = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			return -1;
		}
		return choice;
	}

	/**
	 * Processes and validates user input for a file path. If user provides an
	 * invalid input <b>MAX_ATTEMPTS</b> consecutive times, the method returns
	 * currently selected path to prevent infinite loops.
	 * 
	 * @param currentPath currently set file path used if input is invalid
	 * @return the new file path provided by the user, or the current path for
	 *         invalid input
	 */
	// O(n) where n is MAX_ATTEMPTS. It loops MAX_ATTEMPS in the worst case
	public String setPath(String currentPath) {
		int counter = MAX_ATTEMPTS;
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

	/**
	 * Processes user's algorithm selection by reading an integer from the console.
	 * Allows up to <b>MAX_ATTEMPTS</b> attempts to enter a valid selection (1-4)
	 * before it defaults to the option 4.
	 * 
	 * @return the algorithm choice as an integer wihtin 1-4 range
	 */
	// O(n) where n is MAX_ATTEMPTS. It loops MAX_ATTEMPS in the worst case
	public int processAlgorithmSelection() {
		int choice = 0;
		int counter = MAX_ATTEMPTS;
		while (counter > 0) {
			var errorMessage = ConsoleColour.RED
					+ "Invalid input! Please try again. Option 4 will be selected automatically after " + --counter
					+ " more invalid attempt(s).";
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

	/**
	 * Closes the Scanner to release system resources and prevent resource leaks.
	 * This method should be called when input is no longer needed.
	 */
	// O(1) - all operations within a method are constant time
	public void closeScanner() {
		if (scan != null)
			scan.close();
	}

}
