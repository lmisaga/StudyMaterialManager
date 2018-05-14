package functions;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import books.Material;
import gui.MainWindow1;
import users.Administrator;
import users.Student;

/**
 * Handler for every operation involving user serialized ArrayList.
 * @author Lukáš
 *
 */
public class StudentHandler implements VerifAdmin {

	private static ArrayList<Student> StudentLib = new ArrayList<Student>();
	public static int LoggedUserID;
	public static Student LoggedUser;
	public static Material ChosenMaterial;
	
	/**
	 * Returns an Array List of saved students
	 * @return Serialized ArrayList of students
	 */
	public ArrayList<Student> loadStudentList() {
		try {
			FileInputStream fis = new FileInputStream("CredentialsJ.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			@SuppressWarnings("unchecked")
			ArrayList<Student> readObject = (ArrayList<Student>) ois.readObject();
			StudentLib = readObject;	
			fis.close();
			ois.close();
			
		} catch (IOException ioe) {
				ioe.printStackTrace();
		
		} catch (ClassNotFoundException e) {
				System.out.println("Class not found.");
			} 
	return StudentLib;
	}
	
	/**
	 * Saves Serialized ArrayList of students
	 */
	public void saveStudentList() {
		try {
			FileOutputStream fileOut = new FileOutputStream("CredentialsJ.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(StudentLib);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * Used when logging in; returns value of Student when there is existing user
	 * with the parameters provided with "uname" and "pword" strings
	 * @param uname Inserted username
	 * @param pword Inserted password 
	 * @return Logged (found) user
	 * @throws ClassNotFoundException Throws when the class is not visible/found.
	 * @throws IOException When trying to read from empty serialized list.
	 */
	public Student findMatch(String uname, String pword) throws ClassNotFoundException, IOException {
		loadStudentList();
		for (Student s : StudentLib) {
			if ((s.getUsername().equals(uname)) && (s.getPassword().equals(pword))) {
				LoggedUser = s;
				LoggedUserID = StudentLib.indexOf(s);
				/* LoggedUser.ObjectInfo(); only for testing purposes*/
				return s;
			}
		}
		return null;
	}

	/**
	 * Tests if provided arguments are empty (their length is null).
	 * @param username provided username
	 * @param password provided password
	 * @param name	provided name of user
	 * @return boolean false if provided arguments are empty
	 * @throws IncorrectInputException Throwable when some of the argument(s) are empty.
	 */
	public boolean TestIfEmpty(String username, String password, String name) throws IncorrectInputException {
		if (username.isEmpty() || password.isEmpty() || name.isEmpty()) {
			throw new IncorrectInputException(); 
		} else return true;
	}
	/**
	 * Registers user as a regular student
	 * @param username username of registered user
	 * @param password password of registered user
	 * @param name name of registered user
	 * @param year year of study of registered user
	 * @param ownedMaterialCount ownedmaterialCount for newly registered user is equal to zero
	 * @return true if registration was successful
	 * @throws ExistingUserException Throwable when the user already exists
	 */
	public boolean TestAndRegister(String username, String password, String name, 
	 String year, int ownedMaterialCount) throws ExistingUserException {
		loadStudentList();
		for (Student s : StudentLib)
		{
			if (s.getUsername().equals(username)) {
				throw new ExistingUserException();
			}
		}
		
		Student S = new Student();
			S.setUsername(username);
			S.setPassword(password);
			S.setName(name);
			S.setYear(year);
			S.OwnedMatsCollection = new ArrayList<Material>();
			StudentLib.add(S);
		saveStudentList();
		return true;
	}
	/**
	 * Tries to register user as Administrator, returns true if succeeded. 
	 * @param username provided username
	 * @param password provided password
	 * @param name provided name
	 * @param year provided year of study
	 * @param ownedMaterialCount this parameter is equal to zero to newly registered user/admin
	 * @param admin boolean value, true if user is trying to register as admin
	 * @throws IncorrectInputException Throwable when registration form is not filled in properly
	 * @return boolean true if registration success
	 */
	public boolean TestAndRegister(String username, String password, String name, 
			String year, int ownedMaterialCount, boolean admin) throws IncorrectInputException {
		loadStudentList();
		if (username.isEmpty() || password.isEmpty() || name.isEmpty()) {
			throw new IncorrectInputException();
		}
		Administrator A = new Administrator();
			A.setUsername(username);
			A.setPassword(password);
			A.setName(name);
			A.setYear(year);
			A.OwnedMatsCollection = new ArrayList<Material>();
			A.setAdmin();
			StudentLib.add(A);
		saveStudentList();
		return true;
	}
	
	/**
	 * Adds Material to Student's collection
	 * @param S User account in which the material is going to be added
	 * @param toBeAddedMat Material which is going to be added to collection
	 * @return boolean false if the material is already in users collection
	 */
	public boolean AddToCollection (Student S, Material toBeAddedMat) {
		for (Material m : S.OwnedMatsCollection) {
			if (toBeAddedMat.getTitle().equals(m.getTitle())) {
				return false;
			}
		}
		S.OwnedMatsCollection.add(toBeAddedMat);
		saveStudentList();
		return true;
	}
	
	/**
	 * Removes material from Student's collection with title matching the argument ItemToRemove
	 * @param S Actual student
	 * @param ItemToRemove Item which is going to be removed from users collection.
	 */
	public void RemoveFromCollection (Student S, String ItemToRemove) {
			S.OwnedMatsCollection.removeIf(e -> 
				(e.getTitle().equals(ItemToRemove))
					);
		saveStudentList();
	}

	@Override
	public boolean verifyAdmin(Student S) {
		if (S instanceof Administrator) {
			return true;
		}
		else return false;
	}
	/**
	 * Using method verifyAdmin in Interface VerifAdmin, this method opens the Administration window, if
	 * the user has admin privileges.
	 * @param  S Actual provided student
	 * @throws InsufficientPermissionsException Exception which is thrown when user has insufficient permissions
	 */
	public void verifyAndPass(Student S) throws InsufficientPermissionsException {
		if (verifyAdmin(S)) {
			MainWindow1.main(S);
			}
		else {
			throw new InsufficientPermissionsException();
			}
	}
}