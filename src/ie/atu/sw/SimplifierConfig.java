package ie.atu.sw;

/**
 * Record to encapsulate configuration settings for the application.
 * 
 * @param embeddingsFilePath  the file path to the word embeddings file
 * @param inputFilePath       the file path to the text file that needs to be
 *                            simplified
 * @param outputFilePath      the file path where simplified text will be saved
 * @param commonWordsFilePath the file path to most common words file
 * @param vectorSimilarityAlg the name of the vector similarity algorithm to be
 *                            used
 */
public record SimplifierConfig(String embeddingsFilePath, String inputFilePath, String outputFilePath,
		String commonWordsFilePath, String vectorSimilarityAlg) {
}
