package functions;
/**
 * Throwable when user tries to add already owned Material to his 
 * Material ArrayList.
 * @author Lukáš
 *
 */
public class MatAlreadyOwnedException extends Exception {

	private static final long serialVersionUID = 1L;
	public MatAlreadyOwnedException() {}
	public MatAlreadyOwnedException(String message) {
		super(message);
	}
}
