package ie.atu.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * The {@code WordEmbeddingsParser} class implements the {@link FileParser} to
 * parse a file containing word embeddings and stores them in a map. Each line
 * in the file consists of a word followed by its vector representation, with
 * values seperated by commas.
 * 
 * <p>
 * This implementation uses virtual threads to process each line concurrently.
 * </p>
 */
public class WordEmbeddingsParser implements FileParser<Map<String, double[]>> {

	/**
	 * Parses a file containing word embeddings. Each line is expected to have a
	 * word followed by its embedding values, separated by commas. The file is read
	 * line by line, and each line is processed concurrently using virtual threads.
	 * 
	 * @param filePath the path to the embeddings file
	 * @return a map where the key is a word and value is an array of emmedding
	 *         values
	 * @throws IOException if the file cannot be found or read
	 */
	// O(n) loops over n number of lines
	@Override
	public Map<String, double[]> parse(String filePath) throws IOException {
		Map<String, double[]> embeddingsMap = new ConcurrentHashMap<>();

		try (var pool = Executors.newVirtualThreadPerTaskExecutor();
				var br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {

			String line;
			while ((line = br.readLine()) != null) {
				String text = line;
				pool.execute(() -> {
					var entry = processLine(text);
					embeddingsMap.put(entry.getKey(), entry.getValue());
				});
			}
		} catch (FileNotFoundException e) {
			throw new IOException("Embeddings file not found: " + filePath);
		} catch (IOException e) {
			throw new IOException("Error reading embeddings from file: " + filePath);
		}

		return embeddingsMap;
	}

	/**
	 * Processes a single line from the embeddings file and returns an entry
	 * containing the word and its vector representation.
	 * If the line contains invalid data, the method logs the issue and skips the
	 * line.
	 * 
	 * @param line the line to process
	 * @return the map entry consisting of a word and its corresponding vector
	 */
	// O(n) where n is number of vector values within a line
	private Map.Entry<String, double[]> processLine(String line) {
		double[] vector = null;
		String[] parts = line.trim().split(",");
		if (parts.length > 1) {
			try {
				// Convert string values to doubles from second to last element of parts
				vector = Arrays.stream(parts, 1, parts.length).mapToDouble(Double::parseDouble).toArray();
			} catch (NumberFormatException e) {
				System.err.println("Invalid line not added to map: " + line);
			}
		}
		return Map.entry(parts[0], vector);
	}
}
