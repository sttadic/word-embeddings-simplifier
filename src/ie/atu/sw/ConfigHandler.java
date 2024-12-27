package ie.atu.sw;

import static java.lang.System.out;

public class ConfigHandler {
	private InputHandler inputHandler;
	// App configuration instance variables with default values
	private String embeddingsFilePath = "../word-embeddings.txt";
	private String outputFilePath = "../output.txt";
	private String inputFilePath = "../input.txt";
	private String commonWordsFilePath = "../google-1000.txt";
	private String vectorComparisonAlgo = "Cosine Similarity";
	
	public ConfigHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}
	
	public String getEmbeddingsFilePath() {
		return embeddingsFilePath;
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public String getCommonWordsFilePath() {
		return commonWordsFilePath;
	}

	public String getVectorComparisonAlgo() {
		return vectorComparisonAlgo;
	}
	
	private String promptForPath(String promptMessage, String currentPath) {
		Menu.clearScreen();
		out.print(promptMessage);
		return inputHandler.setPath(currentPath);
	}
	
	// Prompt for a file path to the word embeddings file
	public void setEmbeddingsPath() {
		embeddingsFilePath = promptForPath("Please specify the file path and name of the word embeddings file > ", embeddingsFilePath);
	}

	// Prompt for an input file path that holds text to be simplified
	public void setInputPath() {
		inputFilePath = promptForPath("Please enter the file path and name of a text file to be simplified > ", inputFilePath);
	}

	// Prompt for an output file path that stores simplified text
	public void setOutputPath() {
		outputFilePath = promptForPath("Please enter the file path and name of a file where the results should be saved > ", outputFilePath);
	}

	// Prompt for a file that holds most common words in English
	public void setCommonWordsPath() {
		commonWordsFilePath = promptForPath("Please enter the file path and name of a file that holds a list of most common words used in English > ", commonWordsFilePath);
	}
	
	// Define an algoritham used to calculate distance between vectors
	public void setComparisonAlgorithm() {
		int choice = inputHandler.processAlgorithmSelection();
		
		switch (choice) {
		case 1 -> {
			vectorComparisonAlgo = "Cosine Similarity";
		}
		case 2 -> {
			vectorComparisonAlgo = "Euclidean Distance";
		}
		case 3 -> {
			vectorComparisonAlgo ="Dot Product";
		}
		default -> {
			vectorComparisonAlgo = "Combine All (Cosine Similarity, Euclidean Distance, Dot Product)";
		}
		}
	}
}
