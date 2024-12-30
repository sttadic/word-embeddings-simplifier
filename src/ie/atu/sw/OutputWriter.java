package ie.atu.sw;

import java.io.*;

public class OutputWriter implements AutoCloseable {
	private final BufferedWriter writer;

	public OutputWriter(String filePath) throws IOException {
		this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
	}
	
	public void write(String token) throws IOException {
		writer.write(token);
	}
	
	@Override
	public void close() throws IOException {
		writer.close();
	}
}
