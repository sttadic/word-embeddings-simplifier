package ie.atu.sw;

import static java.lang.System.out;
import java.util.Scanner;

public class Menu {
	private Scanner scan;
	private PathHandler pathHandler;
	private boolean keepRunning = true;
	// App configuration instance variables with default values
	private String embeddingsFilePath = "../word-embeddings.txt";
	private String outputFilePath = "../output.txt";
	private String inputFilePath = "../input.txt";
	private String commonWordsFilePath = "../google-1000.txt";
	private String vectorComparisonAlgo = "Cosine Similarity";
	// Error message displayed within the options menu
	private String errorMsg;

	public Menu() {
		this.scan = new Scanner(System.in);
		this.pathHandler = new PathHandler();
	}

	// Start the application
	public void startApplication() {
		while (keepRunning) {
			int choice;
			// Display an error message and reprompt for invalid input
			while (true) {
				showOptions();
				try {
					choice = Integer.parseInt(scan.nextLine());
					if (choice >= 1 && choice <= 7) {
						break;
					}
					errorMsg = "Invalid Selection! Please use one of the options above";
				} catch (Exception e) {
					errorMsg = "Invalid Selection! Please use one of the options above";
				}
			}
			switch (choice) {
			case 1 -> setEmbeddingsPath();
			case 2 -> setInputPath();
			case 3 -> setOutputPath();
			case 4 -> setCommonWordsPath();
			case 5 -> setComparisonAlgo();
			case 6 -> startProcessing();
			case 7 -> keepRunning = false;
			default -> errorMsg = "Unexpected value: " + choice;
			}
		}
		// Application closed - display goodbye message
		out.println("Thank you for using Simplifying Text with Word Embeddings!");
		// Close instance of Scanner
		scan.close();
	}

	// Prompt for a file path to the word embeddings file
	private void setEmbeddingsPath() {
		clearScreen();
		out.print("Please specify the file path and name of the word embeddings file > ");
		embeddingsFilePath = pathHandler.setPath(embeddingsFilePath);
	}

	// Prompt for an input file path that holds text to be simplified
	private void setInputPath() {
		clearScreen();
		out.print("Please enter the file path and name of a text file to be simplified > ");
		inputFilePath = pathHandler.setPath(inputFilePath);
	}

	// Prompt for an output file path that stores simplified text
	private void setOutputPath() {
		clearScreen();
		out.print("Please enter the file path and name of a file where the results should be saved > ");
		outputFilePath = pathHandler.setPath(outputFilePath);
	}

	// Prompt for a file that holds most common words in English
	private void setCommonWordsPath() {
		clearScreen();
		out.print(
				"Please enter the file path and name of a file that holds a list of most common words used in English > ");
		commonWordsFilePath = pathHandler.setPath(commonWordsFilePath);
	}

	// Define an algoritham used to calculate distance between vectors
	private void setComparisonAlgo() {
		clearScreen();
		out.println(ConsoleColour.WHITE_BOLD);
		out.println("Select Vector Comparison Algorithm");
		out.print("**********************************");
		out.println(ConsoleColour.WHITE);
		out.println("(1) Dot Product");
		out.println("(2) Euclidean Distance");
		out.println("(3) Cosine Similarity");
		out.println("(4) Combine All");
		out.println();

		int choice = 0;
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
		// Set comparison algorithm based on user input
		switch (choice) {
		case 1 -> vectorComparisonAlgo = "Dot Product";
		case 2 -> vectorComparisonAlgo = "Euclidean Distance";
		case 3 -> vectorComparisonAlgo = "Cosine Similarity";
		default -> vectorComparisonAlgo = "Combine All (Cosine Similarity, Euclidean Distance, Dot Product)";
		}
	}

	private void startProcessing() {
		out.println("Processing...");
		// code to start processing
		this.keepRunning = false;
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
		out.println("(1) Specify Embedding File  ---> " + ConsoleColour.GREEN + embeddingsFilePath + ConsoleColour.WHITE);
		// Display input file path
		out.println("(2) Text File to Simplify   ---> " + ConsoleColour.GREEN + inputFilePath + ConsoleColour.WHITE);
		// Display output file path
		out.println("(3) Specify an Output file  ---> " + ConsoleColour.GREEN + outputFilePath + ConsoleColour.WHITE);
		// Display common words file path
		out.println("(4) Most Common Words File  ---> " + ConsoleColour.GREEN + commonWordsFilePath + ConsoleColour.WHITE);
		// Display algorithm used for comparison
		out.println("(5) Set Comparison Method   ---> " + ConsoleColour.GREEN + vectorComparisonAlgo + ConsoleColour.WHITE);
		// Start simplifying text
		out.println("(6) START");
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
	private void clearScreen() {
		out.print("\033[H\033[2J");
		out.flush();
	}
}