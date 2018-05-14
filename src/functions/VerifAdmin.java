package functions;

import users.Student;

public interface VerifAdmin {
	/**
	 * Verifies if user is an Administrator (returns true if positive)
	 * @param S User to be verified
	 * @return boolean true if success
	 */
	public boolean verifyAdmin(Student S);
}
