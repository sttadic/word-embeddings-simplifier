package ie.atu.sw;

import java.util.*;

public class WordVectorAssigner {
	
	public static Map<String, double[]> assignVectors(Set<String> commonWords, Map<String, double[]> wordEmbeddings) {
		var commonWordEmbeddings = new HashMap<String, double[]>();
		
		for (String word : commonWords) {
			if (wordEmbeddings.containsKey(word)) {
				commonWordEmbeddings.put(word, wordEmbeddings.get(word));
			}
		}
		return commonWordEmbeddings;
	}
}
