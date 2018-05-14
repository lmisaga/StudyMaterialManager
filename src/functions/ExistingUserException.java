package functions;

/**
 * 
 * Throwable during registration, if the user is trying to register with already used username.
 * @author Lukáš
 *
 */
public class ExistingUserException extends Exception {
	private static final long serialVersionUID = 1L;
	 /**
	  * ExistingUserException default constructor
	  */
	public ExistingUserException() {
		
	}
	/**
	 * ExistingUserException constructor with message option
	 * @param message Message to be displayed
	 */
	public ExistingUserException(String message) {
		super(message);
	}
}
