package ie.atu.sw;

public class DotProduct implements VectorSimilarity {

	@Override
	public double compare(double[] vector1, double[] vector2) {
		double dotProd = 0.0;
		// Iterate over embeddings dimension and multiply elements of vectors
		for (int i = 0; i < vector1.length; i++) {
			dotProd += vector1[i] * vector2[i];
		}
		// Return dot product
		return dotProd;
	}

}
