package ie.atu.sw;

import java.util.*;

public class Tokanizer {
	
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
