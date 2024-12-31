package ie.atu.sw;

import java.io.*;
import java.util.*;

/**
 * The {@code WordEmbeddingsParser} class implements the {@link FileParser} to 
 * parse a file containing word embeddings and stores them in a map. Each line 
 * in the file represents a word followed by its vector.
 */
public class WordEmbeddingsParser implements FileParser<Map<String, double[]>>{

	/**
	 * Parses a file containing word embeddings. Each line is expected to have
	 * a word followed by its embedding values, separated by comma.
	 * 
	 * @param filePath the path to the embeddings file
	 * @return a map where the key is a word and value is an array of emmedding 
	 * 		   values
	 * @throws IOException if the file cannot be found or read
	 */
	// O(n) loops over n number of lines
	@Override
	public Map<String, double[]> parse(String filePath) throws IOException {
		Map<String, double[]> embeddingsMap = new HashMap<>();
		
		try(var br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while ((line = br.readLine()) != null) {
				processLine(line, embeddingsMap);
			}
		} catch (FileNotFoundException e) {
			throw new IOException("Embeddings file not found: " + filePath);
		} catch (IOException e) {
			throw new IOException("Error reading embeddings from file: " + filePath);
		}
		return embeddingsMap;
	}
	
	/**
	 * Processes a single line from the embeddings ile and adds it to the map.
	 * 
	 * @param line the line to process
	 * @param embeddingsMap the map to store processed word and its embedding vector
	 */
	// O(n) where n is number of elements (words and vector values) within a line
	private void processLine(String line, Map<String, double[]> embeddingsMap) {
		String[] parts = line.trim().split(",");
		if (parts.length > 1) {
			try {
				// Convert string to double for each element of parts in range 1-parts.length
				double[] vector = Arrays.stream(parts, 1, parts.length)
										.mapToDouble(Double::parseDouble)
										.toArray();
				embeddingsMap.put(parts[0], vector);
			} catch (NumberFormatException e) {
				System.err.println("Invalid line not added to map: " + line);
			}
		}
	}
}
