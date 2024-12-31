package ie.atu.sw;

/**
 * The {@code EuclideanDistance} class implements {@link VectorSimilarity}
 * interface to calculate the Euclidean distance between two vectors.
 * 
 * <p>
 * Euclidean distance is the straight-line distance between two points in a
 * multi-dimensional space.
 * </p>
 */
public class EuclideanDistance implements VectorSimilarity {
	
	/**
	 * Computes the Euclidean distance between two vectors.
	 * 
	 * @param vector1 the first vector
	 * @param vector2 the second vector
	 * @return the Euclidean distance score
	 */
	// O(n) iterates once over both vectors where n is length of a vector
	@Override
	public double compare(double[] vector1, double[] vector2) {
		double sum = 0.0;
		// Calculate sum of squared differences between vectors
		for (int i = 0; i < vector1.length; i++) {
			sum += Math.pow(vector1[i] - vector2[i], 2);
		}
		// Return euclidean distance (square root of sum)
		return Math.sqrt(sum);
	}

}
