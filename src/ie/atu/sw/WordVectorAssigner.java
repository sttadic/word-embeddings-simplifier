package ie.atu.sw;

import java.util.*;

/**
 * The {@code WordVectorAssigner} class provides utility method for assigning
 * vectors from a map of pre-existing word embeddings to a set of common words.
 */
public class WordVectorAssigner {

	/**
	 * Assigns vectors to common words by retrieving their embeddings from the
	 * provided word embeddings map.
	 * 
	 * @param commonWords a set of common words for which embeddings are to be assigned
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
}
