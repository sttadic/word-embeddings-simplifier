package ie.atu.sw;

import java.util.*;

public class SimplificationCoordinator {
	private final Map<String, double[]> embedMap;
	private final Map<String, double[]> commonEmbedMap;
	private final List<Map.Entry<String, Boolean>> toSimplifyList;
	private final String comparisonAlg;

	public SimplificationCoordinator(Map<String, double[]> embedMap, Map<String, double[]> commonEmbedMap,
			List<Map.Entry<String, Boolean>> toSimplifyList, String comparisonAlg) {
		this.embedMap = embedMap;
		this.commonEmbedMap = commonEmbedMap;
		this.toSimplifyList = toSimplifyList;
		this.comparisonAlg = comparisonAlg;
	}
	
	
}
