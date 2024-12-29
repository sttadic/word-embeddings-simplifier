package ie.atu.sw;

import java.io.*;
import java.util.*;

/**
 * The InputTextParser class parses an input text file and tokenizes its
 * content. The tokenizer breaks lines into tokens, distinguishing between text
 * and punctuation.
 */
public class InputTextParser implements FileParser<List<Map.Entry<String, Boolean>>> {
	private final Tokenizer tokenizer;

	/**
	 * Consturcts an InputTextParser with the specified tokenizer.
	 * 
	 * @param tokenizer used to process lines of text
	 */
	public InputTextParser(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	/**
	 * Parses the input text file and tokenizes its content.
	 * 
	 * @param filePath the path to the input file
	 * @return a list of tokens where each token is paried with a boolean indicating
	 *         if it's a word or not
	 * @throws IOException if file cannot be found or read
	 */
	// O(n^2) - iterates over n lines and for each line calles tokenize() which is linear
	@Override
	public List<Map.Entry<String, Boolean>> parse(String filePath) throws IOException {
		var tokens = new ArrayList<Map.Entry<String, Boolean>>();

		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while ((line = br.readLine()) != null) {
				tokens.addAll(tokenizer.tokenize(line));
			}
		} catch (FileNotFoundException e) {
			throw new IOException("Input text file not found: " + filePath);
		} catch (IOException e) {
			throw new IOException("Error reading text from file: " + filePath);
		}
		return tokens;
	}
}
