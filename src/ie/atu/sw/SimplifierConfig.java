package ie.atu.sw;

/**
 * Record to encapsulate configuration settings for the application.
 */
public record SimplifierConfig(String embeddingsFilePath, String inputFilePath, String outputFilePath,
		String commonWordsFilePath, String vectorComparisonAlgo) {
}
