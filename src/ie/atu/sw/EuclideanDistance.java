package ie.atu.sw;

public class EuclideanDistance implements VectorSimilarity {

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
