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
		}
		return embeddingsMap;
	}
	
	private void processLine(String line, Map<String, double[]> embeddingsMap) {
		String[] parts = line.trim().split(",");
		if (parts.length > 1) {
			// Convert string to double for each element of parts in range 1-parts.length
			double[] vector = Arrays.stream(parts, 1, parts.length)
									.mapToDouble(Double::parseDouble)
									.toArray();
			embeddingsMap.putIfAbsent(line, vector);
		}
	}
}
