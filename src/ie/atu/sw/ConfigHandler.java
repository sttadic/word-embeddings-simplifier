package ie.atu.sw;

import static java.lang.System.out;

/**
 * The ConfigHandler class manages configuration settings for the application.
 * It initializes configuration with default values for file paths and vector
 * similarity algorithm and provides methods for setting and retrieving
 * configuration values.
 */
public class ConfigHandler {
	private InputHandler inputHandler;
	private String embeddingsFilePath = "../embeddings.txt";
	private String outputFilePath = "../output.txt";
	private String inputFilePath = "../input.txt";
	private String commonWordsFilePath = "../google-1000.txt";
	private String vectorSimilarityAlg = "Cosine Similarity";

	/**
	 * Instantiates ConfigHandler with the specified InputHandler instance.
	 * 
	 * @param inputHandler instance used for input and path validation
	 */
	public ConfigHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	/**
	 * Retrieves the file path for the word embeddings file.
	 * 
	 * @return current file path for the word embeddings file
	 */
	// O(1) return is constant time operation
	public String getEmbeddingsFilePath() {
		return embeddingsFilePath;
	}

	/**
	 * Retrieves the file path for the output file.
	 * 
	 * @return current file path for the output file
	 */
	// O(1) return is constant time operation
	public String getOutputFilePath() {
		return outputFilePath;
	}

	/**
	 * Retrieves the file path for the input file.
	 * 
	 * @return current file path for the input file
	 */
	// O(1) return is constant time operation
	public String getInputFilePath() {
		return inputFilePath;
	}

	/**
	 * Retrieves the file path for the common words file.
	 * 
	 * @return current file path for the common words file
	 */
	// O(1) return is constant time operation
	public String getCommonWordsFilePath() {
		return commonWordsFilePath;
	}

	/**
	 * Retrieves selected vector similarity algorithm.
	 * 
	 * @return the name of the currently selected vector similarity algorithm
	 */
	// O(1) return is constant time operation
	public String getVectorSimilarityAlg() {
		return vectorSimilarityAlg;
	}

	/**
	 * Prompts user to specify the file path for the word embedding file. Updates
	 * <b>embeddingsFilePath</b> with the user's input.
	 */
	// O(1) all operations are constant time
	public void setEmbeddingsPath() {
		embeddingsFilePath = promptForPath("Please specify the file path and name of the word embeddings file > ",
				embeddingsFilePath);
	}

	/**
	 * Prompts user to specify the file path for the input file. Updates
	 * <b>inputFilePath</b> with the user's input.
	 */
	// O(1) all operations are constant time
	public void setInputPath() {
		inputFilePath = promptForPath("Please enter the file path and name of a text file to be simplified > ",
				inputFilePath);
	}

	/**
	 * Prompts user to specify the file path for the output file. Updates
	 * <b>outputFilePath</b> with the user's input.
	 */
	// O(1) all operations are constant time
	public void setOutputPath() {
		outputFilePath = promptForPath(
				"Please enter the file path and name of a file where the results should be saved > ", outputFilePath);
	}

	/**
	 * Prompts user to specify the file path for the common words file. Updates
	 * <b>commonWordsFilePath</b> with the user's input.
	 */
	// O(1) all operations are constant time
	public void setCommonWordsPath() {
		commonWordsFilePath = promptForPath(
				"Please enter the file path and name of a file that holds a list of most common words used in English > ",
				commonWordsFilePath);
	}

	/**
	 * Allows selection of a vector similarity algorithm. Updates
	 * <b>vectorSimilarityAlg</b> based on user's choice.
	 */
	// O(1) all operations are constant time
	public void setSimilarityAlgorithm() {
		int choice = inputHandler.processAlgorithmSelection();

		switch (choice) {
		case 1 -> {
			vectorSimilarityAlg = "Cosine Similarity";
		}
		case 2 -> {
			vectorSimilarityAlg = "Euclidean Distance";
		}
		case 3 -> {
			vectorSimilarityAlg = "Dot Product";
		}
		default -> {
			vectorSimilarityAlg = "Combined Average";
		}
		}
	}

	/**
	 * Prompts user for a new file path and validates the input.
	 * 
	 * @param promptMessage the message displayed to the user
	 * @param currentPath   currently set file path
	 * @return new file path if user input is valid, or the currentPath otherwise
	 */
	// O(1) all operations are constant time
	private String promptForPath(String promptMessage, String currentPath) {
		Menu.clearScreen();
		out.print(promptMessage);
		return inputHandler.setPath(currentPath);
	}

	/**
	 * Creates and returns a SimplifierConfig object with current configuration
	 * values.
	 * 
	 * @return SimplifierConfig instance that encapsulates current configuration
	 */
	// O(1) creating an instance and returning are constant time operations
	public SimplifierConfig generateConfig() {
		return new SimplifierConfig(
				embeddingsFilePath, 
				inputFilePath, 
				outputFilePath, 
				commonWordsFilePath,
				vectorSimilarityAlg
		);
	}
}
