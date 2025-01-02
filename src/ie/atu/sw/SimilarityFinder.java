package ie.atu.sw;

import java.util.*;

/**
 * The {@code SimilarityFinder} class provides functionality to find the most
 * similar word from a map of common word embeddings based on a selected vector
 * similarity algorithm.
 * 
 * The search is performed by comparing the given word vector with the vectors
 * stored in the map and selecting the word with the highest similarity score.
 */
public class SimilarityFinder {
	private VectorSimilarityAlgorithm similaritySearchMethod;

	/**
	 * Searches for the most similar word to the given word vector from the provided
	 * map of most common word embeddings using the specified similarity algorithm.
	 * 
	 * The method iterates over the map of common word embeddings, calculates
	 * similarity score for each entry using selected similairy search method, and
	 * keeps track of the word with the highest similarity score.
	 * 
	 * @param wordVector          the vector representation of the word to search
	 *                            most similary word for
	 * @param commonEmbedMap      a map containing common word embeddings
	 * @param vectorSimilarityAlg the selected vector similarity algorithm
	 * @return the most similar word to the wordVector from the map of common word
	 *         embeddings
	 */
	// O(n) where n is number of entries in the commonEmbedMap
	public String similaritySearch(double[] wordVector, Map<String, double[]> commonEmbedMap,
			String vectorSimilarityAlg) {

		similaritySearchMethod = selectSimilaritySearchMethod(vectorSimilarityAlg);
		String mostSimilarWord = null;
		double bestSimilairtyScore = Double.NEGATIVE_INFINITY;

		for (Map.Entry<String, double[]> entry : commonEmbedMap.entrySet()) {
			VectorUtils.validateEqualLength(wordVector, entry.getValue());    // Check vectors lengths
			double similarityScore = similaritySearchMethod.calculateSimilarity(wordVector, entry.getValue());
			// Skip if cosine similarity returns NaN indicating zero magnitude
			if (Double.isNaN(similarityScore)) continue;
			
			if (similarityScore > bestSimilairtyScore) {
				bestSimilairtyScore = similarityScore;
				mostSimilarWord = entry.getKey();
			}
		}
		return mostSimilarWord;
	}

	/**
	 * Selects similairty search method based on the proviced algorithm name.
	 * 
	 * @param vectorSimAlg the name of vector similairty algorithm to use
	 * @return the corresponding {@link VectorSimilarityAlgorithm} implementation
	 * @throws IllegalArgumentException if the provided algorithm is not supported
	 */
	// O(1) all constant time operations
	private VectorSimilarityAlgorithm selectSimilaritySearchMethod(String vectorSimAlg) {
		return switch (vectorSimAlg.toLowerCase()) {
		case "cosine similarity" -> similaritySearchMethod = new CosineSimilarity();
		case "euclidean distance" -> similaritySearchMethod = new EuclideanDistance();
		case "dot product" -> similaritySearchMethod = new DotProduct();
		case "combined average" -> similaritySearchMethod = new CompositeSimilarity(
				List.of(new CosineSimilarity(), new EuclideanDistance(), new DotProduct()));
		default -> throw new IllegalArgumentException("Invalid similarity algorithm: " + vectorSimAlg);
		};
	}
}
