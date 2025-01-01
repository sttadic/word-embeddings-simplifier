package ie.atu.sw;

import java.util.*;

public class SimplificationProcessor {
	private final String vectorSimilarityAlg;
	
	public SimplificationProcessor(String vectorSimilarityAlg) {
		this.vectorSimilarityAlg = vectorSimilarityAlg;
	}

	public String calculateSimilarity(double[] wordVector, Map<String, double[]> commonEmbedMap) {


		// Calculate similarity between vector of word and all vectors of commonWords

		Map.Entry<String, Double> topMatchResult;
		for (double[] vector : commonEmbedMap.values()) {

		}

		// Retrun top matching word
		return null;
	}
}
