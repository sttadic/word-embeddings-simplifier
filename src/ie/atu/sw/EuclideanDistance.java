package ie.atu.sw;

/**
 * The {@code EuclideanDistance} class extends the
 * {@link VectorSimilarityAlgorithm} abstract class to calculate the Euclidean
 * distance between two vectors. This implementation normalizes the distance to
 * produce a similarity score.
 * 
 * <p>
 * Euclidean distance is the straight-line distance between two points in a
 * multi-dimensional space.
 * </p>
 */
public class EuclideanDistance extends VectorSimilarityAlgorithm {

	/**
	 * Computes the Euclidean distance between two vectors and normalizes the score
	 * to produce distance-based similarity.
	 * 
	 * @param vector1 the first vector
	 * @param vector2 the second vector
	 * @return the normalized similarity score derived from Euclidean distance
	 */
	// O(n) iterates once over both vectors where n is length of a vector
	@Override
	public double calculateSimilarity(double[] vector1, double[] vector2) {
		double sum = 0.0;
		// Calculate sum of squared differences between vectors
		for (int i = 0; i < vector1.length; i++) {
			sum += Math.pow(vector1[i] - vector2[i], 2);
		}
		double distance = Math.sqrt(sum);
		// Return normalized score (inversed from distance to similarity)
		return normalizeScore(distance);
	}
}
