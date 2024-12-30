package ie.atu.sw;

import java.io.*;

/**
 * The class {@code OutputWriter} provides utility methods for writing text
 * output to a file. It encapsulates BufferedReader and implements AutoCloseable
 * to ensure proper resource management.
 */
public class OutputWriter implements AutoCloseable {
	private final BufferedWriter writer;

	/**
	 * Constructs an {@code OutputWriter} to write to the specified file path.
	 * 
	 * @param filePath the path of the file to write to
	 * @throws IOException if an error occurs while opening the file
	 */
	public OutputWriter(String filePath) throws IOException {
		this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
	}

	/**
	 * Writes a single token to the output file.
	 * 
	 * @param token the token to write
	 * @throws IOException in case an error occurs while writing to the file
	 */
	// O(1) - invoking a method is constant time operation
	public void write(String token) throws IOException {
		writer.write(token);
	}

	/**
	 * Closes writer and releases associated resources.
	 * 
	 * @throws IOException if an error occurs while closing the writer
	 */
	// O(1)
	@Override
	public void close() throws IOException {
		writer.close();
	}
}
