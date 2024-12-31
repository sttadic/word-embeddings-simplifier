package ie.atu.sw;

/**
 * The {@code VectorSimilarity} interface defines a contract for algorithms that
 * calculate similarity or distance between two vectors.
 * 
 * <p>
 * Implementing classes must provide their own definition of the {@code compare}
 * method for specific vector comparison algorithms. Vectors must be of equal
 * length.
 * </p>
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
