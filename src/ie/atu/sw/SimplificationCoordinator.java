package ie.atu.sw;

import java.io.IOException;
import java.util.*;

/**
 * The {@code SimplificationCoordinator} class orchestrates the simplification
 * of input text using word embeddings.
 * 
 * It identifies words requiring simplification, delegates finding of most
 * similar word using vector similarity algorithms and writing of the results to
 * the output file.
 */
public class SimplificationCoordinator {
	private final Map<String, double[]> commonEmbedMap;
	private final List<Map.Entry<String, Boolean>> toSimplifyList;
	private final SimplificationProcessor simpProcessor;

	/**
	 * Constructs a {@code SimplificationCoordinator} with the specified common
	 * words embeddings map and list of tokens to simplify.
	 * 
	 * @param commonEmbedMap a map of most common words in Engish with their
	 *                       corresponding vectors
	 * @param toSimplifyList a list of tokens to process, paired with the boolean
	 *                       values indicating whether they are words or not
	 */
	public SimplificationCoordinator(Map<String, double[]> commonEmbedMap,
			List<Map.Entry<String, Boolean>> toSimplifyList) {
		this.commonEmbedMap = commonEmbedMap;
		this.toSimplifyList = toSimplifyList;
		this.simpProcessor = new SimplificationProcessor(null);
	}

	/**
	 * Iterates over the tokens list generated from input text file, compares tokens
	 * with common words if necessary, and coordinates the search for most similar
	 * word and writing of results to an output file.
	 * 
	 * @param embedMap the full set of word embeddings
	 * @param config   the configuration setting for the simplification process
	 * @throws IOException if an error occures while writing to the output file
	 */
	// O(n^2) loops over n number of tokens and performs lookup in a map of size m
	public void coordinateSimplification(Map<String, double[]> embedMap, SimplifierConfig config) throws IOException {
		try (OutputWriter outputWriter = new OutputWriter(config.outputFilePath())) {
			for (Map.Entry<String, Boolean> entry : toSimplifyList) {
				String token = entry.getKey();
				if (entry.getValue() && !commonEmbedMap.containsKey(token)) {
					SimplificationProcessor vsManager = new SimplificationProcessor(config.vectorSimilarityAlg());
					vsManager.calculateSimilarity(token, commonEmbedMap, embedMap);
				}
				outputWriter.write(token);
			}
		} catch (IOException e) {
			throw new IOException("Error writing to the file: " + config.outputFilePath());
		}
	}
}