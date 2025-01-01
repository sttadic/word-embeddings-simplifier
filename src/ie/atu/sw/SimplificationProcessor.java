package ie.atu.sw;

import java.util.*;

public class SimplificationProcessor {

	public SimplificationProcessor(String vectorSimAlgorithm) {
		
	}
	
	
	public String calculateSimilarity(String word, Map<String, double[]> commonWords, Map<String, double[]> embeddings) {
		// Find vector for word in embeddings
		double[] wordVector = embeddings.get(word);
					
		// Calculate similarity between vector of word and all vectors of commonWords
		
		Map.Entry<String, Double> topMatchResult;
		for (double[] vector : commonWords.values()) {
			
		}
		
		// Retrun top matching word
		return null;
	}
}
