package ie.atu.sw;

import java.io.IOException;
import java.util.*;

public class SimplificationCoordinator {
	private final Map<String, double[]> commonEmbedMap;
	private final List<Map.Entry<String, Boolean>> toSimplifyList;

	public SimplificationCoordinator(Map<String, double[]> commonEmbedMap,
			List<Map.Entry<String, Boolean>> toSimplifyList) {
		this.commonEmbedMap = commonEmbedMap;
		this.toSimplifyList = toSimplifyList;
	}

	public void coordinateSimplification(Map<String, double[]> embedMap, SimplifierConfig config) throws IOException {
		try (OutputWriter outputWriter = new OutputWriter(config.outputFilePath())) {
			for (Map.Entry<String, Boolean> entry : toSimplifyList) {
				String token = entry.getKey();
				if (entry.getValue() && !commonEmbedMap.containsKey(token)) {
					// token = get the most similar word by running vector comparison algorithms
					// (embedMap, config.vectorComparisonAlgo())
				}
				outputWriter.write(token);
			}
		} catch (IOException e) {
			throw new IOException("Error writing to the file: " + config.outputFilePath());
		}
	}
}