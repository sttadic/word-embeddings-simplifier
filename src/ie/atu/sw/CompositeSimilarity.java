package ie.atu.sw;

import java.util.*;

/**
 * The {@code CompositeSimilarity} class extends
 * {@link VectorSimilarityAlgorithm} abstract class to calculate an average
 * similarity score using multiple vector similarity algorithms.
 * 
 * <p>
 * Combining the results of various similarity algorithms can be particularly
 * useful when a composite metric is desired for a more comprehensive similarity
 * measure.
 * </p>
 */
public class CompositeSimilarity extends VectorSimilarityAlgorithm {
	private List<VectorSimilarityAlgorithm> algorithms;

	/**
	 * Constructs a {@code CompositeSimilarity} object with a list of vector
	 * similarity algorithms.
	 * 
	 * @param algorithms a list of {@link VectorSimilarityAlgorithm} instances to
	 *                   combine
	 * @throws IllegalArgumentException if the list of algorithms is null or empty
	 */
	public CompositeSimilarity(List<VectorSimilarityAlgorithm> algorithms) {
		if (algorithms == null || algorithms.isEmpty()) {
			throw new IllegalArgumentException("The list of algorithms cannot be null or empty!");
		}
		this.algorithms = algorithms;
	}

	/**
	 * Computes the average similarity score by applying all algorithms in the list
	 * to the given vectors and averaging their results.
	 * 
	 * @param vector1 the first vector
	 * @param vector2 the second vector
	 * @return the average similarity score across all algorithms
	 */
	// O(n) looping over n number of algorithms
	@Override
	public double calculateSimilarity(double[] vector1, double[] vector2) {
		var combinedScore = 0.0d;

		for (VectorSimilarityAlgorithm algorithm : algorithms) {
			combinedScore += algorithm.calculateSimilarity(vector1, vector2);
		}
		return combinedScore / algorithms.size();
	}

}
