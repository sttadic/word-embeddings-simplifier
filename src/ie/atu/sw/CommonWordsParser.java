package ie.atu.sw;

import java.io.*;
import java.util.*;

public class CommonWordsParser implements FileParser<Set<String>> {
	
	@Override
	public Set<String> parse(String filePath) throws IOException {
		var commonWords = new HashSet<String>();
		
		try(var br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while((line = br.readLine()) != null) {
				commonWords.add(line.trim());
			}
		} catch (FileNotFoundException e) {
			throw new IOException("Common Words file file not found: " + filePath);
		} catch (IOException e) {
			throw new IOException("Error reading common words from file: " + filePath);
		}
		return commonWords;
	}
}
