package ie.atu.sw;

/**
 * The {@code Runner} class serves as an entry point, initializes the Menu and
 * starts the application workflow.
 */
public class Runner {

	/**
	 * Creates an instance of the Menu class and starts the application by invoking
	 * startApplication() mentod.
	 * 
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		new Menu().runApplication();
	}
}
