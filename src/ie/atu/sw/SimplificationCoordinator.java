package ie.atu.sw;

import java.util.*;

public class SimplificationCoordinator {
	private final Map<String, double[]> embedMap;
	private final Map<String, double[]> commonEmbedMap;
	private final List<Map.Entry<String, Boolean>> toSimplifyList;

	public SimplificationCoordinator(Map<String, double[]> embedMap, Map<String, double[]> commonEmbedMap,
			List<Map.Entry<String, Boolean>> toSimplifyList) {
		this.embedMap = embedMap;
		this.commonEmbedMap = commonEmbedMap;
		this.toSimplifyList = toSimplifyList;
	}

	public void coordinateSimplification(String comparisonAlg) {
		toSimplifyList.forEach(e -> {
			if (e.getValue()) {
				if (commonEmbedMap.containsKey(e.getKey())) {
					// write e.getKey as it is since it is a common word
				} else {
					// run similarity analysis (pass in comparisonAlg)
					// write most similar word from commonEmbedMap instead
				}
			} else {
				// write e.getKey as it is since it's not a word (punctuation or space)
			}
		});
	}

}
