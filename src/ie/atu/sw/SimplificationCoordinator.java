package ie.atu.sw;

import java.util.*;

public class SimplificationCoordinator {
	private final Map<String, double[]> commonEmbedMap;
	private final List<Map.Entry<String, Boolean>> toSimplifyList;

	public SimplificationCoordinator(Map<String, double[]> commonEmbedMap,
			List<Map.Entry<String, Boolean>> toSimplifyList) {
		this.commonEmbedMap = commonEmbedMap;
		this.toSimplifyList = toSimplifyList;
	}

	public void coordinateSimplification(Map<String, double[]> embedMap, SimplifierConfig config) {
		toSimplifyList.forEach(e -> {
			String token = e.getKey();
			if (e.getValue() && !commonEmbedMap.containsKey(token)) {
				// token = get the most similar word by running vector comparison algorithms
				// (embedMap, config.vectorComparisonAlgo())
			}
			// write the token (token, config.getOutputFilePath())
		});
	}
}