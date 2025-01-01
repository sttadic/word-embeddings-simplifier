package ie.atu.sw;

import java.util.*;

public class SimilarityMatcher {
	private VectorSimilarityAlgorithm similaritySearchMethod;

	private VectorSimilarityAlgorithm selectSimilaritySearchMethod(String alg) {
		return switch (alg.toLowerCase()) {
		case "cosine similarity" -> similaritySearchMethod = new CosineSimilarity();
		case "eculidean distance" -> similaritySearchMethod = new EuclideanDistance();
		case "dot product" -> similaritySearchMethod = new DotProduct();
		case "combine all" -> similaritySearchMethod = new CompositeSimilarity(
				List.of(new CosineSimilarity(), new EuclideanDistance(), new DotProduct()));
		default -> throw new IllegalArgumentException("Invalid similarity algorithm: " + alg);
		};
	}

	public String similaritySearch(double[] wordVector, Map<String, double[]> commonEmbedMap,
			String vectorSimilarityAlg) {

		similaritySearchMethod = selectSimilaritySearchMethod(vectorSimilarityAlg);
		for (Map.Entry<String, double[]> entry : commonEmbedMap.entrySet()) {
			double topScore = 0.0d;
			double similarityScore = similaritySearchMethod.calculateSimilarity(wordVector, entry.getValue());

		}

		return null;
	}
}
