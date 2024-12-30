package ie.atu.sw;

import java.io.IOException;
import java.util.Map;

public class SimplifierManager {
	private static SimplifierManager instance;
	private final Tokenizer tokanizer;
	private SimplifierConfig config;

	public SimplifierManager(SimplifierConfig config) {
		this.config = config;
		this.tokanizer = new Tokenizer();
	}

	public static SimplifierManager getInstance(SimplifierConfig config) {
		if (instance == null) {
			instance = new SimplifierManager(config);
		} else {
			// Set new configuration (e.g. updated file paths)
			instance.setConfig(config);
		}
		return instance;
	}

	private void setConfig(SimplifierConfig newConfig) {
		this.config = newConfig;
	}

	public void simplify() throws Exception {
		var embedMap = new WordEmbeddingsParser().parse(config.embeddingsFilePath());
		var commonEmbedMap = generateCommonEmbeddings(embedMap);
		var toSimplifyList = new InputTextParser(tokanizer).parse(config.inputFilePath());

		var simCoordinator = new SimplificationCoordinator(commonEmbedMap, toSimplifyList);
		simCoordinator.coordinateSimplification(embedMap, config.vectorComparisonAlgo());
	}

	private Map<String, double[]> generateCommonEmbeddings(Map<String, double[]> embeddings) throws IOException {
		var commonWordsSet = new CommonWordsParser().parse(config.commonWordsFilePath());
		return WordVectorAssigner.assignVectors(commonWordsSet, embeddings);
	}

}
