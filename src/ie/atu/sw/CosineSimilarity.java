package ie.atu.sw;

/**
 * The {@code CosineSimilarity} class extends the
 * {@link VectorSimilarityAlgorithm} abstract class to calculate cosine
 * similarity between two vectors.
 * 
 * <p>
 * Cosine similarity measures the cosine of the angle between two vectors in a
 * multi-dimensional space, resulting in a score between -1 (opposite direction)
 * and 1 (identical direction).
 * </p>
 */
public class CosineSimilarity extends VectorSimilarityAlgorithm {

	/**
	 * Computes cosine similarity between two vectors.
	 * 
	 * @param vector1 the first vector
	 * @param vector2 the second vector
	 * @return the cosine similarity score; NaN if two vectors have zero magnitude
	 */
	// O(n) iterates once over both vectors where n is length of a vector
	@Override
	public double calculateSimilarity(double[] vector1, double[] vector2) {
		double dotProd = 0.0;
		double sumInputVec = 0.0;
		double sumEmbedVec = 0.0;
		// Calculate dot product and sum of squares of vector elements
		for (int i = 0; i < vector1.length; i++) {
			dotProd += vector1[i] * vector2[i];
			sumInputVec += Math.pow(vector1[i], 2);
			sumEmbedVec += Math.pow(vector2[i], 2);
		}
		// Square root of product of vectors (magnitude)
		double sqRootOfProd = Math.sqrt(sumInputVec * sumEmbedVec);
		// Avoid possible division by zero by returning 'NaN' as a flag
		if (sqRootOfProd == 0.0)
			return Double.NaN;
		// Return cosine similarity (quotient of dot product and 'sqRootOfProd')
		return dotProd / sqRootOfProd;
	}

}
