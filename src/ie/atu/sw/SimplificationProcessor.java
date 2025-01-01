package ie.atu.sw;

import java.io.IOException;
import java.util.*;

/**
 * The {@code SimplificationProcessor} class orchestrates the simplification of
 * input text using word embeddings.
 * 
 * It identifies words requiring simplification, delegates finding of most
 * similar word using vector similarity algorithms and writing of the results to
 * the output file.
 */
public class SimplificationProcessor {
	private final SimplifierConfig config;
	private final List<Map.Entry<String, Boolean>> toSimplifyList;
	private final SimilarityMatcher similarityMatcher;

	/**
	 * Constructs a {@code SimplificationProcessor} with the specified common words
	 * embeddings map and list of tokens to simplify.
	 * 
	 * @param config         the configuration setting for the simplification
	 *                       process
	 * @param toSimplifyList a list of tokens to process, paired with the boolean
	 *                       values indicating whether they are words or not
	 */
	public SimplificationProcessor(SimplifierConfig config, List<Map.Entry<String, Boolean>> toSimplifyList) {
		this.config = config;
		this.toSimplifyList = toSimplifyList;
		this.similarityMatcher = new SimilarityMatcher();
	}

	/**
	 * Iterates over the tokens list generated from input text file, compares tokens
	 * with common words if necessary, and coordinates the search for most similar
	 * word and writing of results to an output file.
	 * 
	 * @param commonEmbedMap a map of most common words in Engish with their
	 *                       corresponding vectors
	 * @param embedMap       the full set of word embeddings
	 * 
	 * @throws IOException if an error occures while writing to the output file
	 */
	// O(n^2) loops over n number of tokens and performs lookup in a map of size m
	public void simplify(Map<String, double[]> commonEmbedMap, Map<String, double[]> embedMap) throws IOException {
		try (OutputWriter outputWriter = new OutputWriter(config.outputFilePath())) {
			for (Map.Entry<String, Boolean> entry : toSimplifyList) {
				String token = entry.getKey();
				// Token is a word but not a common words
				if (entry.getValue() && !commonEmbedMap.containsKey(token)) {
					// Get word embedding map entry for a word to simplifay
					var toSimplifyEmbedding = VectorUtils.assignVector(token, embedMap);

					token = similarityMatcher.similaritySearch(toSimplifyEmbedding.getValue(), commonEmbedMap,
							config.vectorSimilarityAlg());
				}
				outputWriter.write(token);
			}
		} catch (IOException e) {
			throw new IOException("Error writing to the file: " + config.outputFilePath());
		}
	}
}