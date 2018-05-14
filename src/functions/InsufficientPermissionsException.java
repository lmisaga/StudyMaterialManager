package functions;
/**
 * Throwable when user tries to perform an action which requires administrator privileges.
 * @author Luk�
 *
 */
public class InsufficientPermissionsException extends Exception {
	private static final long serialVersionUID = 1L;
	public InsufficientPermissionsException() {
		}
	
	public InsufficientPermissionsException(String message) {
		super(message);
	}
}
