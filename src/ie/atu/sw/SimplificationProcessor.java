package ie.atu.sw;

import java.io.IOException;
import java.util.*;

/**
 * The {@code SimplificationProcessor} class orchestrates the simplification of
 * input text using word embeddings.
 * 
 * It identifies words requiring simplification, delegates the process of
 * finding the most similar word using vector similarity algorithms, and writes
 * the results to the output file.
 */
public class SimplificationProcessor {
	private final SimplifierConfig config;
	private final List<Map.Entry<String, Boolean>> toSimplifyList;
	private final SimilarityFinder similarityFinder;

	/**
	 * Constructs a {@code SimplificationProcessor} with specified configuration and
	 * list of tokens to simplify.
	 * 
	 * @param config         the configuration setting for the simplification
	 *                       process
	 * @param toSimplifyList a list of tokens to process, paired with the boolean
	 *                       values indicating whether they are words or separators
	 */
	public SimplificationProcessor(SimplifierConfig config, List<Map.Entry<String, Boolean>> toSimplifyList) {
		this.config = config;
		this.toSimplifyList = toSimplifyList;
		this.similarityFinder = new SimilarityFinder();
	}

	/**
	 * Iterates over the tokens list generated from input text file, compares tokens
	 * with most common words if necessary, and coordinates the search for most
	 * similar word and writing of results to an output file.
	 * 
	 * @param commonEmbedMap a map of most common words in Engish with their
	 *                       corresponding embeddings
	 * @param embedMap       the full set of word embeddings
	 * 
	 * @throws IOException if an error occures while writing to the output file
	 */
	// O(n) loops over n number of tokens
	public void simplify(Map<String, double[]> commonEmbedMap, Map<String, double[]> embedMap) throws IOException {
		try (OutputWriter outputWriter = new OutputWriter(config.outputFilePath())) {
			for (Map.Entry<String, Boolean> entry : toSimplifyList) {
				String token = entry.getKey();

				if (shouldSimplify(entry, commonEmbedMap)) {
					token = processTokenAndSimplify(token, commonEmbedMap, embedMap);
				}

				outputWriter.write(token);
			}
		} catch (IOException e) {
			throw new IOException("Error writing to the file: " + config.outputFilePath());
		}
	}

	/**
	 * Determines if a token should be simplified. A token is simplified if it is a
	 * word (not a separator such is space or punctuation) and is not a common word
	 * in English (not found in common word embedding map).
	 * 
	 * @param entry          the token entry and its boolean indication if it is a
	 *                       word or not
	 * @param commonEmbedMap the map of common word embeddings
	 * @return true if the token should be simplified, false otherwise
	 */
	// O(n) performs lookup in the map of size n
	private boolean shouldSimplify(Map.Entry<String, Boolean> entry, Map<String, double[]> commonEmbedMap) {
		return entry.getValue() && !commonEmbedMap.containsKey(entry.getKey().toLowerCase());
	}

	/**
	 * Prepares a word for simplification by extracting its corresponding vector
	 * from embeddings map and delegates simplification process taking into account
	 * case sensitivity.
	 * 
	 * @param word           the word to be simplified
	 * @param commonEmbedMap the map of common word embeddings
	 * @param embedMap       the map consisting of a whole set of word embeddings
	 * @return the simplified word
	 */
	// O(1) all constant time operations
	private String processTokenAndSimplify(String word, Map<String, double[]> commonEmbedMap,
			Map<String, double[]> embedMap) {
		boolean isLowerCase = Character.isLowerCase(word.charAt(0));

		var toSimplifyEmbedding = VectorUtils.assignVector(word.toLowerCase(), embedMap);
		word = similarityFinder.similaritySearch(toSimplifyEmbedding.getValue(), commonEmbedMap,
				config.vectorSimilarityAlg());

		if (!isLowerCase)
			word = capitalizeFirstLetter(word);

		return word;
	}

	/**
	 * Capitalizes the first letter of the simplified word if its original version
	 * before simplification was uppercase.
	 * 
	 * @param word the word to capitalize
	 * @return the word with the first letter capitalized
	 */
	// O(1) all constant time operations
	private String capitalizeFirstLetter(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
}