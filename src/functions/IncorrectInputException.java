package functions;
/**
 * Exception which occurs when the form is not correctly filled in,
 * causing the malfunctions with saved data
 * @author Lukáš
 *
 */
public class IncorrectInputException extends Exception {
	private static final long serialVersionUID = 1L;
	public IncorrectInputException() {}
	public IncorrectInputException(String message) {
		super(message);
	}
}
