package users;
import java.io.*;
import java.util.ArrayList;
import books.Material;
/**
 * Regular user class. This type of user can have his own profile, and can search in Material ArrayList.
 * 
 * @author Lukáš
 *
 */
public class Student implements Serializable, DisplayInfo{
	
	
	private static final long serialVersionUID = 1L;
	private String Username; 
	private String Password;
	private String Name;
	private String Year;
	public ArrayList<Material> OwnedMatsCollection; 	
	public int OwnedMaterialCount;
	
	/**
	 * Gets username of actual student
	 * @return Username Actual username of the user
	 */
	public String getUsername() {
		return this.Username;
	}
	/**
	 * Default Constructor for Student class.
	 */
	public Student() {
		super();
	}
	/**
	 * Sets username for the actual student
	 * @param username Username to be set to the user
	 */
	public void setUsername(String username) {
		this.Username = username;
	}
	/**
	 * Gets students password
	 * @return Actual student's password
	 */
	public String getPassword() {
		return this.Password;
	}
	/**
	 * Sets password for actual student
	 * @param password Password to be set to the user
	 */
	public void setPassword(String password) { 
		this.Password = password;
	}
	/**
	 * Gets name of actual student
	 * @return Name of user
	 */
	public String getName() {
		return this.Name;
	}
	/**
	 * Sets name of actual student
	 * @param name Name to be set to the user
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * Gets year of actual student
	 * @return Year of user
	 */
	public String getYear() {
		return this.Year;
	}
	 /**
	  * Sets year of study of actual student
	  @param year Year to be set to the actual user
	  */
	public void setYear(String year) {	
	this.Year = year;
	}
	 
	
	@Override
	/**
	 * Prints out info of actual user on System output. Used mainly for testing.
	 */
	public void ObjectInfo() {
		System.out.println("Name: " + this.Name);
		System.out.println("Username: "+ this.Username);
		System.out.println("Year of study: " + this.Year);
		System.out.println("Owned study material count: " +this.OwnedMaterialCount);
	}
	
}