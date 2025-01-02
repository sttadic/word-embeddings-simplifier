package ie.atu.sw;

import java.util.*;

public class SimilarityMatcher {
	private VectorSimilarityAlgorithm similaritySearchMethod;

	public String similaritySearch(double[] wordVector, Map<String, double[]> commonEmbedMap,
			String vectorSimilarityAlg) {
		similaritySearchMethod = selectSimilaritySearchMethod(vectorSimilarityAlg);
		String mostSimilarWord = null;
		double bestSimilairtyScore = Double.NEGATIVE_INFINITY;
		
		for (Map.Entry<String, double[]> entry : commonEmbedMap.entrySet()) {
			double similarityScore = similaritySearchMethod.calculateSimilarity(wordVector, entry.getValue());
			if (similarityScore > bestSimilairtyScore) {
				bestSimilairtyScore = similarityScore;
				mostSimilarWord = entry.getKey();
			}
		}
		return mostSimilarWord;
	}
	
	private VectorSimilarityAlgorithm selectSimilaritySearchMethod(String vectorSimAlg) {
		return switch (vectorSimAlg.toLowerCase()) {
		case "cosine similarity" -> similaritySearchMethod = new CosineSimilarity();
		case "euclidean distance" -> similaritySearchMethod = new EuclideanDistance();
		case "dot product" -> similaritySearchMethod = new DotProduct();
		case "combined average" -> similaritySearchMethod = new CompositeSimilarity(
				List.of(new CosineSimilarity(), new EuclideanDistance(), new DotProduct()));
		default -> throw new IllegalArgumentException("Invalid similarity algorithm: " + vectorSimAlg);
		};
	}
}
