package users;

import java.io.Serializable;
/**
 * This type of user can change the main Material ArrayList, and can perform
 * test actions. 
 * @author Lukáš
 *
 */
public class Administrator extends Student implements Serializable{

	protected boolean isAdmin;
	private static final long serialVersionUID = 1L;
	/**
	 * Sets administrator privileges to the user
	 */
	public void setAdmin() {
		this.isAdmin = true;
	}
	/**
	 * Returns true if the object is an Administrator
	 * @return Boolean value true when user is an administrator
	 */
	public boolean getAdmin() {
		return this.isAdmin;
	}
	/**
	 * Writes info of the current Administrator on the System output.
	 */
	public void ObjectInfo() {
		System.out.println("ADMINISTRATOR");
		System.out.println("Username: " + this.getUsername());
	}

}
