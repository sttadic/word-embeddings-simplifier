package ie.atu.sw;

import java.io.IOException;
import java.util.Map;

public class SimplifierManager {
	private static SimplifierManager instance;
	private SimplifierConfig config;
	private final Tokenizer tokanizer;

	public SimplifierManager(SimplifierConfig config) {
		this.config = config;
		this.tokanizer = new Tokenizer();
	}

	public static SimplifierManager getInstance(SimplifierConfig config) {
		if (instance == null) {
			instance = new SimplifierManager(config);
		} else {
			instance.setConfig(config);
		}
		return instance;
	}
	
	private void setConfig(SimplifierConfig newConfig) {
		this.config = newConfig;
	}

	public void simplify() throws Exception {
		var wordEmbeddingsMap = new WordEmbeddingsParser().parse(config.embeddingsFilePath());
		var commonWordsEmbeddings = generateCommonEmbed(wordEmbeddingsMap);
		var inputTextList = new InputTextParser(tokanizer).parse(config.inputFilePath());
		
	}

	private Map<String, double[]> generateCommonEmbed(Map<String, double[]> embeddings) throws IOException {
		var commonWordsSet = new CommonWordsParser().parse(config.commonWordsFilePath());
		return WordVectorAssigner.assignVectors(commonWordsSet, embeddings);
	}
}
