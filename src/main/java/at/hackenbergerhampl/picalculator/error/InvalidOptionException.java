package at.hackenbergerhampl.picalculator.error;

/**
 * InvalidOptionException
 *
 * @author Hackenberger Christoph
 * @version 1.0
 */
public class InvalidOptionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8300134005275346183L;

	/**
	 * Constructs a new {@link InvalidOptionException} with the specified detail message.
	 * @param message the detail message.
	 */
	public InvalidOptionException(String message) {
		super(message);
	}

}
