package ie.atu.sw;

public class SimplifierManager {
	private static SimplifierManager instance;
	private final SimplifierConfig config;
	private final Tokenizer tokanizer;
	
	
	public SimplifierManager(SimplifierConfig config) {
		this.config = config;
		this.tokanizer = new Tokenizer();
	}
	
	public static SimplifierManager getInstance(SimplifierConfig config) {
		if (instance == null) instance = new SimplifierManager(config);
		return instance;
	}
	
	public void simplify() throws Exception {
		var wordEmbeddingsMap = new WordEmbeddingsParser().parse(config.embeddingsFilePath());
		var commonWordsSet = new CommonWordsParser().parse(config.commonWordsFilePath());
		var inputTextList = new InputTextParser(tokanizer).parse(config.inputFilePath());
	}
}
