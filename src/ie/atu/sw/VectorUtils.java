package ie.atu.sw;

import java.util.*;

/**
 * The {@code VectorUtils} class provides utility methods for word vectors and
 * embeddings, including vector length validation and vector assignment
 * operations.
 */
public class VectorUtils {

	/**
	 * Validates that two vectors have the same length.
	 * 
	 * @param vector1 the first vector
	 * @param vector2 the second vector
	 * @throws IllegalArgumentException if vectors have different lengths
	 */
	// O(1) preforms constant number of checks on vectors length
	public static void validateEqualLength(double[] vector1, double[] vector2) {
		if (vector1.length != vector2.length) {
			throw new IllegalArgumentException(
					"Vector similarity calculation failed! Vectors must have the same length.");
		}
	}

	/**
	 * Assigns vectors to common words by retrieving their embeddings from the
	 * provided word embeddings map.
	 * 
	 * @param commonWords    a set of common words for which embeddings are to be
	 *                       assigned
	 * @param wordEmbeddings a map of word embeddings
	 * @return a map of common words with thier corresponding vectors
	 */
	// O(n^2) loops over set of size n and performs lookup in a map of size m
	public static Map<String, double[]> assignVectors(Set<String> commonWords, Map<String, double[]> wordEmbeddings) {
		var commonWordEmbeddings = new HashMap<String, double[]>();

		for (String word : commonWords) {
			if (wordEmbeddings.containsKey(word)) {
				commonWordEmbeddings.put(word, wordEmbeddings.get(word));
			}
		}
		return commonWordEmbeddings;
	}
	
	public static double[] assignVector(String word, Map<String, double[]> wordEmbeddings) {
		return wordEmbeddings.get(word);
	}
}
