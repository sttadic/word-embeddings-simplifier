package ie.atu.sw;

import static java.lang.System.out;

public class Menu {
	private InputHandler inputHandler;
	private ConfigHandler configHandler;
	private boolean keepRunning = true;
	// Error message displayed within the options menu
	private String errorMsg;

	public Menu() {
		this.inputHandler = new InputHandler();
		this.configHandler = new ConfigHandler(this.inputHandler);
	}

	
	// Start the application
	public void startApplication() {
		while (keepRunning) {
			showOptions();
			handleChoice(inputHandler.processMenuSelection());
		}
		inputHandler.closeScanner();
		out.println("Thank you for using Simplifying Text with Word Embeddings!");
	}
	
	private void handleChoice(int choice) {
		switch (choice) {
		case 1 -> configHandler.setEmbeddingsPath();
		case 2 -> configHandler.setInputPath();
		case 3 -> configHandler.setOutputPath();
		case 4 -> configHandler.setCommonWordsPath();
		case 5 -> comparisonAlgorithmSelection();
		case 6 -> keepRunning = false;   // Start of processing to be implemented
		case 7 -> keepRunning = false;
		default -> errorMsg = "Invalid Selection! Please use one of the options above";
		}
	}

	// Define an algoritham used to calculate distance between vectors
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

	// Menu options
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
		out.println("(1) Specify Embedding File  ---> " + ConsoleColour.GREEN + configHandler.getEmbeddingsFilePath() + ConsoleColour.WHITE);
		// Display input file path
		out.println("(2) Text File to Simplify   ---> " + ConsoleColour.GREEN + configHandler.getInputFilePath() + ConsoleColour.WHITE);
		// Display output file path
		out.println("(3) Specify an Output file  ---> " + ConsoleColour.GREEN + configHandler.getOutputFilePath() + ConsoleColour.WHITE);
		// Display common words file path
		out.println("(4) Most Common Words File  ---> " + ConsoleColour.GREEN + configHandler.getCommonWordsFilePath() + ConsoleColour.WHITE);
		// Display algorithm used for comparison
		out.println("(5) Set Comparison Method   ---> " + ConsoleColour.GREEN + configHandler.getVectorComparisonAlgo() + ConsoleColour.WHITE);
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

	/*
	 * Source: https://intellipaat.com/community/294/java-clear-the-console 
	 * Clears terminal window (doesn't work for IDE console)
	 */
	public static void clearScreen() {
		out.print("\033[H\033[2J");
		out.flush();
	}
}