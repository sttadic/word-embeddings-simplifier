package ie.atu.sw;

import java.util.*;

public class SimilarityMatcher {
	private  VectorSimilarityAlgorithm similarityAlgorithm;
	
	

	private VectorSimilarityAlgorithm setVectorSimilarityAlgorithm(String alg) {
		return switch (alg.toLowerCase()) {
		case "cosine similarity"  -> similarityAlgorithm = new CosineSimilarity();
		case "eculidean distance" -> similarityAlgorithm = new EuclideanDistance();
		case "dot product"        -> similarityAlgorithm = new DotProduct();
		case "combine all"        -> similarityAlgorithm = 
			new CompositeSimilarity(List.of(new CosineSimilarity(), new EuclideanDistance(), new DotProduct()));
		default                   -> throw new IllegalArgumentException("Invalid similarity algorithm: " + alg);
		};
	}
	
	public String similaritySearch(double[] wordVector, Map<String, double[]> commonEmbedMap, String alg) {
		
		similarityAlgorithm = setVectorSimilarityAlgorithm(alg);
		for (Map.Entry<String, double[]> entry : commonEmbedMap.entrySet()) {
			double topScore = 0.0d;
			double similarityScore = similarityAlgorithm.calculateSimilarity(wordVector, entry.getValue());
			
		}
		
		
		return null;
	}
}
