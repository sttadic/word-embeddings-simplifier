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
	 * @throws IllegalArgumentException if vectors have different lengths or if they
	 * 									are null
	 */
	// O(1) preforms constant number of checks on vectors length
	public static void validateEqualLength(double[] vector1, double[] vector2) {
		if (vector1 == null || vector2 == null) {
			throw new IllegalArgumentException(
					"Vector similarity calculation failed! One or both vectors are null.");
		}
		if (vector1.length != vector2.length) {
			throw new IllegalArgumentException(
					"Vector similarity calculation failed! Vectors must have the same length.");
		}
	}

	/**
	 * Assigns vectors to a set of words by retrieving their embeddings from the
	 * provided word embeddings map.
	 * 
	 * @param wordsSet       a set of words for which embeddings are to be assigned
	 * @param wordEmbeddings a map of word embeddings
	 * @return a map of words with thier corresponding vectors
	 */
	// O(n^2) loops over set of size n and performs lookup in a map of size m
	public static Map<String, double[]> assignVectors(Set<String> wordsSet, Map<String, double[]> wordEmbeddings) {
		var newWordEmbeddings = new HashMap<String, double[]>();

		for (String word : wordsSet) {
			if (wordEmbeddings.containsKey(word)) {
				newWordEmbeddings.put(word, wordEmbeddings.get(word));
			}
		}
		return newWordEmbeddings;
	}

	/**
	 * Assigns vector to a word by retrieving its embedding from the provided word
	 * embeddings map.
	 * 
	 * @param word           a word for which embedding is to be assigned
	 * @param wordEmbeddings a map of word embeddings
	 * @return a map entry containing a word and its corresponding vector, or a null
	 *         if word is not found in word embeddings map
	 */
	public static Map.Entry<String, double[]> assignVector(String word, Map<String, double[]> wordEmbeddings) {
		double[] wordVector;

		wordVector = wordEmbeddings.get(word);
		if (wordVector == null) return null;

		return Map.entry(word, wordVector);
	}
}
