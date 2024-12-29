package ie.atu.sw;

import java.io.*;
import java.util.*;

/**
 * The class CommonWordsParser parses a file containing most common words in
 * English and stores them in a set. Each line in a file is expected to contain
 * a single word.
 */
public class CommonWordsParser implements FileParser<Set<String>> {

	/**
	 * Parses a file containing common words.
	 * 
	 * @param filePath the path to the common words file
	 * @return a set of common words
	 * @throws IOException if the file cannot be found or read
	 */
	// O(n) - loops over n lines of a file
	@Override
	public Set<String> parse(String filePath) throws IOException {
		var commonWords = new HashSet<String>();

		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while ((line = br.readLine()) != null) {
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
