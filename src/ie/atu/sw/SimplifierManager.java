package ie.atu.sw;

public class SimplifierManager {
	private static SimplifierManager instance;
	private final SimplifierConfig config;
	private final WordEmbeddingsParser wordEmbeddingsParser;
	private final InputTextParser inputTextParser;
	private final CommonWordsParser commonWordsParser;
	private final Tokenizer tokanizer;
	
	
	public SimplifierManager(SimplifierConfig config) {
		this.config = config;
		this.tokanizer = new Tokenizer();
		this.wordEmbeddingsParser = new WordEmbeddingsParser();
		this.inputTextParser = new InputTextParser(tokanizer);
		this.commonWordsParser = new CommonWordsParser();
	}
	
	public static SimplifierManager getInstance(SimplifierConfig config) {
		if (instance == null) instance = new SimplifierManager(config);
		return instance;
	}
	
	public void startProcessing() {
		
	}
}
