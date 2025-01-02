package ie.atu.sw;

import java.io.IOException;
import java.util.Map;

/**
 * The {@code SimplifierManager} class serves as the central coordinator for the
 * simplification process. It is responsible for initializing required resources
 * in order to prepare data for simplification by delegating work to parsers,
 * tokenizers, and finally to {@code SimplificationProcessor}.
 * 
 * <p>
 * This class uses the singleton pattern to ensure a single instance manages the
 * process, preventing creation of a new instance if the process is restarted
 * after an error occures, which returns control to the Menu.
 * </p>
 */
public class SimplifierManager {
	private static SimplifierManager instance;
	private final Tokenizer tokanizer;
	private SimplifierConfig config;

	/**
	 * Constructs an instance of {@code SimplifierManager} using the specified
	 * configuration and initializes the tokenizer.
	 * 
	 * This constructor is private to enforce the singleton design pattern.
	 * 
	 * @param config the configuration object containing paths and selected vector
	 *               similarity algorithm for simplification
	 */
	private SimplifierManager(SimplifierConfig config) {
		this.config = config;
		this.tokanizer = new Tokenizer();
	}

	/**
	 * Returns the singleton instance of {@code SimplifierManager}, initializing it
	 * if necessary.
	 * 
	 * @param config the configuration settings for the simplification process
	 * @return the singleton instance of {@code SimplifierManager}
	 */
	// O(1) all constatnt time operations
	public static SimplifierManager getInstance(SimplifierConfig config) {
		if (instance == null) {
			instance = new SimplifierManager(config);
		} else {
			// Set new configuration (e.g. updated file paths)
			instance.setConfig(config);
		}
		return instance;
	}

	// O(1)
	private void setConfig(SimplifierConfig newConfig) {
		this.config = newConfig;
	}

	/**
	 * Initiates the simplification process that includes parsing word embeddings
	 * and input text, generating a mapping of common word embeddings and delegating
	 * simplification to the {@code SimplificationProcessor}.
	 * 
	 * @throws Exception if an error occures during simplification process
	 */
	// O(1) all constatnt time operations
	public void startSimplification() throws Exception {
		var embedMap = new WordEmbeddingsParser().parse(config.embeddingsFilePath());
		var commonEmbedMap = generateCommonEmbeddings(embedMap);
		var toSimplifyList = new InputTextParser(tokanizer).parse(config.inputFilePath());

		var simProcessor = new SimplificationProcessor(config, toSimplifyList);
		simProcessor.simplify(commonEmbedMap, embedMap);
	}

	/**
	 * Generates a map of common word embeddings by extracting relevant vectors from
	 * the map of word emgeddings where key is the word and value is its vector
	 * representation.
	 * 
	 * @param embeddings the full set of word embeddings
	 * @return a map of common word embeddings
	 * @throws IOException if an error occurs while reading common words
	 */
	// O(1) all constatnt time operations
	private Map<String, double[]> generateCommonEmbeddings(Map<String, double[]> embeddings) throws IOException {
		var commonWordsSet = new CommonWordsParser().parse(config.commonWordsFilePath());
		return VectorUtils.assignVectors(commonWordsSet, embeddings);
	}

}
