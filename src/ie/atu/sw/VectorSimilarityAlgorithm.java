package ie.atu.sw;

/**
 * The {@code VectorSimilarityAlgorithm} abstract class defines a blueprint for
 * algorithms that calculate similarity or distance between two vectors.
 * 
 * <p>
 * Subclasses must provide their own implementation of the
 * {@code calculateSimilarity} method for specific vector similarity algorithms.
 * </p>
 * 
 * <p>
 * Additionally, this class provides a default normalization logic through the
 * {@code normalizeScore} method, which can be used or overridden by subclasses
 * to adjust scores, such as converting distance into similarity.
 * </p>
 */
public abstract class VectorSimilarityAlgorithm {
	/**
	 * Compares two vectors and returns similarity or distance score.
	 * 
	 * @param vector1 the first vector
	 * @param vector2 the second vector
	 * @return the similarity or distance score between two vectors
	 */
	public abstract double calculateSimilarity(double[] vector1, double[] vector2);

	/**
	 * Normalizes score by converting distance-based measures into similarity.
	 * 
	 * @param originalScore the raw score to normalize
	 * @return the normalized score
	 */
	public double normalizeScore(double originalScore) {
		return 1 / (1 + originalScore);
	}
}
