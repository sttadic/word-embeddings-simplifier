package ie.atu.sw;

import java.io.*;
import java.util.*;

public class InputTextParser implements FileParser<List<String>>{
	
	@Override
	public List<String> parse(String filePath) throws IOException {
		var inputTextList = new ArrayList<String>();
		
		try(var br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while((line = br.readLine()) != null) {
				String[] wordsArr = line.split("\\W+");
				Collections.addAll(inputTextList, wordsArr);
			}
		}
		return inputTextList;
	}
}
