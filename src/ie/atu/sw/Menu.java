package ie.atu.sw;

import static java.lang.System.out;

/**
 * The class Menu serves as the main user interface and controls the flow of the
 * application. It displays varoius menu options, selected paths and algorithms
 * to the user and initiates processing based on user's configuration.
 */
public class Menu {
	private InputHandler inputHandler;
	private ConfigHandler configHandler;
	private boolean keepRunning = true;
	// Error message displayed within the options menu
	private String errorMsg;

	/**
	 * Instantiates a Menu object and initializes InputHandler and ConfigHandler.
	 */
	public Menu() {
		this.inputHandler = new InputHandler();
		this.configHandler = new ConfigHandler(this.inputHandler);
	}

	/**
	 * Starts the application by running a loop that displays menu options, reads
	 * user input and handles selected option until user quits or application
	 * completes.
	 */
	// O(n) where n is number of loops based on number of user inputs
	public void runApplication() {
		while (keepRunning) {
			showOptions();
			handleChoice(inputHandler.processMenuSelection());
		}
		inputHandler.closeScanner();
		out.println("\nThank you for using Text Simplifier!\n");
	}

	/**
	 * Processes menu selection and invokes corresponding method based on user
	 * choice. If invalid choice is made, an error message is displayed.
	 * 
	 * @param choice the user's input associated with menu option
	 */
	// O(1) due to constant time operations within each case (invoking methods)
	private void handleChoice(int choice) {
		switch (choice) {
		case 1 -> configHandler.setEmbeddingsPath();
		case 2 -> configHandler.setInputPath();
		case 3 -> configHandler.setOutputPath();
		case 4 -> configHandler.setCommonWordsPath();
		case 5 -> comparisonAlgorithmSelection();
		case 6 -> {
			var config = configHandler.generateConfig();
			new SimplifierManager(config).startProcessing();
			keepRunning = false;
			out.println("\nProcessing complete! Your simplified text has been saved to: " + ConsoleColour.GREEN 
					+ configHandler.getOutputFilePath() + ConsoleColour.WHITE);
		}
		case 7 -> keepRunning = false;
		default -> errorMsg = "Invalid Selection! Please use one of the options above";
		}
	}

	/**
	 * Prompts the user to select a vector comparison algorithm and delegates
	 * selection to the ConfigHandler to set the chosen algorithm.
	 */
	// O(1) - all constant time operations
	private void comparisonAlgorithmSelection() {
		clearScreen();
		out.println(ConsoleColour.WHITE_BOLD);
		out.println("Select Vector Comparison Algorithm");
		out.print("**********************************");
		out.println(ConsoleColour.WHITE);
		out.println("(1) Cosine Similarity");
		out.println("(2) Euclidean Distance");
		out.println("(3) Dot Product");
		out.println("(4) Combine All");
		out.println();

		configHandler.setComparisonAlgorithm();
	}

	/**
	 * Displays the available options to the user, showing current configuration
	 * settings. It also displays an error message if an error occurs during the
	 * application execution."
	 */
	// O(1) since all operations in a method are constant time operations
	private void showOptions() {
		clearScreen();
		out.println(ConsoleColour.WHITE);
		out.println("************************************************************");
		out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		out.println("*                                                          *");
		out.println("*          Simplifying Text with Word Embeddings           *");
		out.println("*                                                          *");
		out.println("************************************************************");
		// Display embeddings file path
		out.println("(1) Specify Embedding File  ---> " + ConsoleColour.GREEN + configHandler.getEmbeddingsFilePath()
				+ ConsoleColour.WHITE);
		// Display input file path
		out.println("(2) Text File to Simplify   ---> " + ConsoleColour.GREEN + configHandler.getInputFilePath()
				+ ConsoleColour.WHITE);
		// Display output file path
		out.println("(3) Specify an Output file  ---> " + ConsoleColour.GREEN + configHandler.getOutputFilePath()
				+ ConsoleColour.WHITE);
		// Display common words file path
		out.println("(4) Most Common Words File  ---> " + ConsoleColour.GREEN + configHandler.getCommonWordsFilePath()
				+ ConsoleColour.WHITE);
		// Display algorithm used for comparison
		out.println("(5) Set Comparison Method   ---> " + ConsoleColour.GREEN + configHandler.getVectorComparisonAlgo()
				+ ConsoleColour.WHITE);
		// Start simplifying text
		out.println("(6) START PROCESSING");
		// Quit
		out.println("(7) Quit");

		// Option selection block
		out.println(ConsoleColour.WHITE_BOLD);
		out.println("");
		out.println("Select Option (1-7) > ");
		// Display error message
		if (errorMsg != null) {
			out.println(ConsoleColour.RED + errorMsg + ConsoleColour.WHITE);
			errorMsg = null;
		}
	}

	/**
	 * Clears the terminal screen. Does not work in all environments like IDE
	 * consoles. Source:
	 * https://intellipaat.com/community/294/java-clear-the-console
	 */
	// O(1) - all constant time operations in a method
	public static void clearScreen() {
		out.print("\033[H\033[2J");
		out.flush();
	}
}