package ie.atu.sw;

import static java.lang.System.out;
import java.util.Scanner;

public class Menu {
	private Scanner scan;
	private InputHandler inputHandler;
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
		this.inputHandler = new InputHandler();
	}

	
	// Start the application
	public void startApplication() {
		while (keepRunning) {
			showOptions();
			int choice = inputHandler.processMenuSelection();
			
			switch (choice) {
			case 1 -> setEmbeddingsPath();
			case 2 -> setInputPath();
			case 3 -> setOutputPath();
			case 4 -> setCommonWordsPath();
			case 5 -> setComparisonAlgorithm();
			case 6 -> startProcessing();
			case 7 -> keepRunning = false;
			default -> errorMsg = "Invalid Selection! Please use one of the options above";
			}
			
		}
		out.println("Thank you for using Simplifying Text with Word Embeddings!");
		scan.close();
	}

	// Prompt for a file path to the word embeddings file
	private void setEmbeddingsPath() {
		clearScreen();
		out.print("Please specify the file path and name of the word embeddings file > ");
		embeddingsFilePath = inputHandler.setPath(embeddingsFilePath);
	}

	// Prompt for an input file path that holds text to be simplified
	private void setInputPath() {
		clearScreen();
		out.print("Please enter the file path and name of a text file to be simplified > ");
		inputFilePath = inputHandler.setPath(inputFilePath);
	}

	// Prompt for an output file path that stores simplified text
	private void setOutputPath() {
		clearScreen();
		out.print("Please enter the file path and name of a file where the results should be saved > ");
		outputFilePath = inputHandler.setPath(outputFilePath);
	}

	// Prompt for a file that holds most common words in English
	private void setCommonWordsPath() {
		clearScreen();
		out.print(
				"Please enter the file path and name of a file that holds a list of most common words used in English > ");
		commonWordsFilePath = inputHandler.setPath(commonWordsFilePath);
	}

	// Define an algoritham used to calculate distance between vectors
	private void setComparisonAlgorithm() {
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

		vectorComparisonAlgo = inputHandler.processAlgorithmSelection();
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