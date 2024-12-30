package ie.atu.sw;

public class CosineSimilarity implements VectorSimilarity {

	@Override
	public double compare(double[] vector1, double[] vector2) {
		double dotProd = 0.0;
		double sumInputVec = 0.0;
		double sumEmbedVec = 0.0;
		// Calculate dot product and sum of squares of vector elements
		for (int i = 0; i < vector1.length; i++) {
			dotProd += vector1[i] * vector2[i];
			sumInputVec += Math.pow(vector1[i], 2);
			sumEmbedVec += Math.pow(vector2[i], 2);
		}
		// Square root of product of vectors
		double sqRootOfProd = Math.sqrt(sumInputVec * sumEmbedVec);
		// Avoid possible division by zero by returning 'NaN' as a flag
		if (sqRootOfProd == 0.0) return Double.NaN;
		// Return cosine similarity (quotient of dot product and 'sqRootOfProd') 
		return dotProd / sqRootOfProd;
	}

}
