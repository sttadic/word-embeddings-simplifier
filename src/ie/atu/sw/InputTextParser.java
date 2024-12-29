package ie.atu.sw;

import java.io.*;
import java.util.*;

public class InputTextParser implements FileParser<List<Map.Entry<String, Boolean>>>{
	private final Tokanizer tokanizer;
	
	public InputTextParser(Tokanizer tokanizer) {
		this.tokanizer = tokanizer;
	}
	
	@Override
	public List<Map.Entry<String, Boolean>> parse(String filePath) throws IOException {
		var tokens = new ArrayList<Map.Entry<String, Boolean>>();
		
		try(var br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while((line = br.readLine()) != null) {
				tokens.addAll(tokanizer.tokenize(line));
			}
		} catch (FileNotFoundException e) {
			throw new IOException("Input text file not found: " + filePath);
		} catch (IOException e) {
			throw new IOException("Error reading text from file: " + filePath);
		}
		return tokens;
	}
}
