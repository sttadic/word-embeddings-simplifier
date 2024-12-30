package ie.atu.sw;

/**
 * The {@code VectorSimilarity} interface defines a contract for all vector
 * similarity search algorithms to follow.
 */
public interface VectorSimilarity {
	/**
	 * Compares two vectors and returns similarity or distance score.
	 * 
	 * @param vector1 the first vector
	 * @param vector2 the second vector
	 * @return the similarity or distance score
	 */
	double compare(double[] vector1, double[] vector2);
}
