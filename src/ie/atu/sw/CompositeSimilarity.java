package ie.atu.sw;

import java.util.*;

public class CompositeSimilarity extends VectorSimilarityAlgorithm{
	private List<VectorSimilarityAlgorithm> algorithms;
	
	public CompositeSimilarity(List<VectorSimilarityAlgorithm> algorithms) {
		this.algorithms = algorithms;
	}

	@Override
	public double calculateSimilarity(double[] vector1, double[] vector2) {
		var combinedScore = 0.0d;
		
		for (VectorSimilarityAlgorithm algorithm : algorithms) {
			combinedScore += algorithm.calculateSimilarity(vector1, vector2);
		}
		return combinedScore / algorithms.size();
	}
	
}
