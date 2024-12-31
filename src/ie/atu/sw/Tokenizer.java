package ie.atu.sw;

import java.util.*;

/**
 * The {@code Tokenizer} class tokenizes lines of text into individual tokens.
 * Each token is classified as either a text token or a non-text token (e.g.
 * punctuation).
 */
public class Tokenizer {

	/**
	 * Tokenizes a single line of text.
	 * 
	 * @param line the line to tokenize
	 * @return a list of tokens paired with a boolean indicating if it's a text or
	 *         non-text
	 */
	// O(n) iterates over n characters of a char array (line)
	public List<Map.Entry<String, Boolean>> tokenize(String line) {
		var lineTokens = new ArrayList<Map.Entry<String, Boolean>>();
		var token = new StringBuilder();

		for (char c : line.toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				token.append(c);
			} else {
				if (token.length() > 0) {
					lineTokens.add(Map.entry(token.toString(), true));
					token.setLength(0);
				}
				lineTokens.add(Map.entry(String.valueOf(c), false));
			}
		}
		if (token.length() > 0) {
			lineTokens.add(Map.entry(token.toString(), true));
		}
		return lineTokens;
	}
}
