package ie.atu.sw;

import java.io.IOException;

/**
 * The {@code FileParser} interface defines a contract for parsing files and
 * extracting specific data types from them. Implementations of this interface
 * are responsible for handling the parsing logic adjusted to the format and
 * structure of the processed file.
 * 
 * @param <T> the type of object (collection) that will store parsed data
 */
public interface FileParser<T> {
	
	/**
	 * Parses the content of a file and returns data in specified format.
	 * 
	 * @param filePath the path to the file to be parsed
	 * @return an object of type T which represents parsed content
	 * @throws IOException in case of an error during file reading
	 */
	T parse(String filePath) throws IOException;
}
