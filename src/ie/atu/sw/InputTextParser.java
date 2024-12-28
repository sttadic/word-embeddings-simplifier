package ie.atu.sw;

import java.io.*;
import java.util.*;

public class InputTextParser implements FileParser<List<Map.Entry<String, Boolean>>>{
	
	@Override
	public List<Map.Entry<String, Boolean>> parse(String filePath) throws IOException {
		var tokens = new ArrayList<Map.Entry<String, Boolean>>();
		
		try(var br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while((line = br.readLine()) != null) {
				tokens.addAll(tokenize(line));
			}
		}
		return tokens;
	}
	
	private List<Map.Entry<String, Boolean>> tokenize(String line) {
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
