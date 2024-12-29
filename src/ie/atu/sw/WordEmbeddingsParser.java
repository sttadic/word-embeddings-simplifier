package ie.atu.sw;

import java.io.*;
import java.util.*;

public class WordEmbeddingsParser implements FileParser<Map<String, double[]>>{

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
