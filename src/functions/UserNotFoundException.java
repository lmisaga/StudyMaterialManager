package functions;

/**
 * Custom exception to dislay an error when user is not found amongst saved registered users
 * @author Lukáš
 *
 */

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public UserNotFoundException() {}
	public UserNotFoundException(String message) {
		super(message);
	}
	
}
