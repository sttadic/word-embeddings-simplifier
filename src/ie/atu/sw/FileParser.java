package ie.atu.sw;

import java.io.IOException;

/**
 * The FileParser interface defines a contract for parsing files.
 * Implementations of this interface are responsible for handling the parsing
 * logic specific to the format of the processed file.
 * 
 * @param <T> the type of object (collection) that will store parsed data
 */
public interface FileParser<T> {
	/**
	 * Parses the content of a file and returns data in specified format.
	 * 
	 * @param filePath the path to the file to be parsed
	 * @return an object of type T which represents parsed content
	 * @throws IOException in case of an error during reading of a file
	 */
	T parse(String filePath) throws IOException;
}
