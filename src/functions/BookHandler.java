package functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import books.EBookMaterial;
import books.Material;
import users.Student;


/**
 * Class BookHandler is used as a controller for every Material operation, such as adding materials to 
 * Serialized ArrayList of Material type, as well as removing and (de)serializing and using the
 * list itself.
 * @author Lukáš
 *
 */
public class BookHandler implements Serializable {
	
	private static final long serialVersionUID = 0;
	static File f = new File("Materials.txt");
	public static ArrayList<Material> Lib = new ArrayList<Material>();
	
	/**
	 * Loads and returns an Array List of saved Materials (Material + E-Book type) 
	 * @return ArrayList including every saved study material
	 * @throws IOException if the material list is empty
	 * @throws ClassNotFoundException if the desired classes are not visible/not available
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Material> LoadMaterialLib() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Lib = (ArrayList<Material>) ois.readObject();	
			fis.close();
			ois.close();
		} catch (IOException ioe) {
				ioe.printStackTrace();
			} 
		catch (ClassNotFoundException e) {
				System.out.println("Class not found");
			}
		return Lib;
	}
	
	/**
	 * Saves the Material Lib to a file
	 */
	public void saveMaterialLib() {
		try {
			FileOutputStream fileOut = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Lib);
			out.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * Adds Material M to Serializable ArrayList of type Material
	 * @param title Title of added material
	 * @param pages PageCount of added material
	 * @param available Availability of added material
	 * @param author Author of added material
	 * @param lib Library (storage place) of added material
	 * @param keywords Keywords of added material
	 * @throws IncorrectInputException if the provided parameters are of size 0
	 * @return boolean true if succeeded
	 */
	public boolean AddToMaterialList(String title, int pages, boolean available, String author, String lib, String keywords) throws IncorrectInputException {
		try {
			LoadMaterialLib();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		if (title.isEmpty() || author.isEmpty() || lib.isEmpty() || keywords.isEmpty()) {
			throw new IncorrectInputException();
			}
		Material M = new Material(title, pages, available, author, lib, keywords);
		Lib.add(M);
		saveMaterialLib();
		return true;
	}
	
	/**
	 * Adds E-Book Material E to Material ArrayList
	 * @param E EBook which is going to be added to serialized ArrayList
	 * @return boolean true if succeeded
	 * @throws IncorrectInputException if the attribute(s) of EBookMaterial to be added
	 * are empty
	 */
	public boolean AddToMaterialList(EBookMaterial E) throws IncorrectInputException {
		try {
			LoadMaterialLib();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		if (E.getTitle().isEmpty() || E.getAuthor().isEmpty() || E.getLibrary().isEmpty()) {
			throw new IncorrectInputException();
		}
		Lib.add(E);
		saveMaterialLib();
		return true;
	}
	
	/**
	 * 
	 * Search and remove title with provided String
	 * @param S Actual (logged) user
	 */
	public void RemoveFromMaterialList(String S) {
		try {
			LoadMaterialLib();
		} catch (ClassNotFoundException | IOException e) {
			
			e.printStackTrace();
		}
		Material toDelete = SearchSpecificMaterial(S);
		Lib.remove(toDelete);
		saveMaterialLib();
	}
	
	/**
	 * Default constructor of the class BookHandler.
	 */
	public BookHandler() {
		super();
	}
	
	/**
	 * Used for test purposes, this method returns 
	 * titles of materials in ArrayList of type Material
	 */
	public static void writeLibToSysout() {
		try {
			BookHandler O = new BookHandler();
			O.LoadMaterialLib();
		} 
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		for (Material m : Lib) {
			System.out.println(m.getTitle() + "\n");
		}
	}
	
	/**
	 * Used for test purposes and available in Administration mode; writes the contents of user's Material ArrayList to 
	 * System output
	 * @param s Actual user
	 */
	public static void writeUserMatLibToSysout(Student s) {
		for (Material m : s.OwnedMatsCollection) {
			System.out.println(m.getTitle());
		}
	}

	/**
	 * Returns an integer of found books under the (sub)string S
	 * @param S Search query
	 * @return Sum of found materials
	 */
	public int sumOfFoundMaterials(String S) {		
		int i = 0;
		try {
			LoadMaterialLib();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Material m : Lib) {
			if (m.getTitle().contains(S) || m.getKeywords().contains(S) || m.getAuthor().contains(S)) {
				i++;
			}
		}
		return i;
	}
	
		/**
		 * Returns Title array of found materials, array can be used as a source of data for JList's model
		 * @param S Search query
		 * @return String Array of found titles
		 */
		public String[] SearchForMaterial(String S) {	
			int FoundArraySize = sumOfFoundMaterials(S);
			String[] foundTitles = new String[FoundArraySize];
			int j = 0;
			try {
				LoadMaterialLib();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < Lib.size() ; i++) {
				if (Lib.get(i).getKeywords().contains(S) || Lib.get(i).getTitle().contains(S)) {
					foundTitles[j] = Lib.get(i).getTitle();
					j++;
				}
			}
			return foundTitles;
		} 

		/**
		 * Founds a material matching its title with string S
		 * @param S Search query
		 * @return selectedTitle Material which is found 
		 */
		public Material SearchSpecificMaterial(String S) {
		Material selectedTitle = new Material();
		try {
			LoadMaterialLib();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < Lib.size() ; i++) {
			if (Lib.get(i).getTitle().equals(S)) {
				selectedTitle = Lib.get(i);
			}
		}
		return selectedTitle;
	}
		
	/**
	 * Finds selected Material with index of "index" (parameter), then replaces this material with new,
	 * edited one, with attributes matching the parameters with "new" prefixes. 
	 * @param S Title of material to be edited.
	 * @param newTitle New title of edited material
	 * @param newPages New pagecount of edited material
	 * @param newAuthor New author of edited material
	 * @param newLibrary New library of edited material
	 * @param newKeywords New keywords for edited material
	 * @param newAvailability New availability for edited material
	 * @param index Index of the material to be edited
	 * @return boolean true if succeeded
	 */
		public boolean EditMaterial(String S, String newTitle, int newPages, String newAuthor, String newLibrary, 
		String newKeywords, boolean newAvailability, int index) {
			try {
				LoadMaterialLib();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			if (index == -1) {
				return false;
			}
			
			Material EditedMat = new Material();
			EditedMat.setTitle(newTitle);
			EditedMat.setPageCount(newPages);
			EditedMat.setAuthor(newAuthor);
			EditedMat.setLibrary(newLibrary);
			EditedMat.setKeywords(newKeywords);
			EditedMat.setAvailable(newAvailability);
			Lib.set(index, EditedMat);
			saveMaterialLib();
			return true;
	}
	 
}