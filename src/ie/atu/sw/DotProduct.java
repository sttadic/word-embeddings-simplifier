package ie.atu.sw;

/**
 * The {@code DotProduct} class implements the {@link VectorSimilarityAlgorithm}
 * interface to calculate the dot product of two vectors.
 * 
 * <p>
 * Dot product is a scalar value that represents the sum of the products of
 * corresponding elements in two vectors.
 * </p>
 */
public class DotProduct implements VectorSimilarityAlgorithm {

	/**
	 * Computes dot product of two vectors.
	 * 
	 * @param vector1 the first vector
	 * @param vector2 the second vector
	 * @return the dot product score
	 */
	// O(n) iterates once over both vectors where n is length of a vector
	@Override
	public double calculateSimilarity(double[] vector1, double[] vector2) {
		double dotProd = 0.0;
		// Iterate over embeddings dimension and multiply elements of vectors
		for (int i = 0; i < vector1.length; i++) {
			dotProd += vector1[i] * vector2[i];
		}
		return dotProd;
	}

}
