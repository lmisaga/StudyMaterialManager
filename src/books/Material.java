package books;

import java.io.Serializable;

import users.DisplayInfo;
/**
 * Default class for a *book* 
 * @author Lukáš
 *
 */
public class Material implements Serializable, DisplayInfo {
	
	private static final long serialVersionUID = 0;
	private String Title;
	private int PageCount;
	private boolean Available;
	private String Author;
	private String Library;
	private String Keywords;
	/**
	 * Returns title of the Material.
	 * @return Title of the material
	 */
	public String getTitle() {
		return this.Title;
	}
	/**
	 * Sets the title of the Material.
	 * @param title Title to be set to the material
	 */
	public void setTitle(String title) {
		this.Title = title;
	}
	/**
	 * Returns pagecount of the material.
	 * @return Page count of the material
	 */
	public int getPageCount() {
		return this.PageCount;
	}
	/**
	 * Sets pagecount of a Material.
	 * @param pageCount Page count to be set to the material.
	 */
	public void setPageCount(int pageCount) {
		this.PageCount = pageCount;
	}
	/**
	 * Returns true if Material is available.
	 * @return Boolean value of material availability
	 */
	public boolean isAvailable() {
		return this.Available;
	}
	/**
	 * Sets availability of the Material.
	 * @param available Availability to be set to the material
	 */
	public void setAvailable(boolean available) {
		this.Available = available;
	}
	/**
	 * Returns name of the author of the Material.
	 * @return Author of this material
	 */
	public String getAuthor() {
		return this.Author;
	}
	/**
	 * Sets author of the Material
	 * @param author Author to be set to the material
	 */
	public void setAuthor(String author) {
		this.Author = author;
	}
	/**
	 * Returns a place where the Material is stored.
	 * @return Library (storage) of the desired material
	 */
	public String getLibrary() {
		return this.Library;
	}
	/**
	 * Sets the location of the Material.
	 * @param library Library (location) to be set to the material
	 */
	public void setLibrary(String library) {
		this.Library = library;
	}
	/**
	 * Returns keywords of chosen Material.
	 * @return Keywords of the material
	 */
	public String getKeywords() {
		return this.Keywords;
	}
	/**
	 * Sets keywords of actual Material.
	 * @param keywords Keywords to be set to the material
	 */
	public void setKeywords(String keywords) {
		this.Keywords = keywords;
	}
	/**
	 * Default parameter-less constructor of Material type.
	 */
	public Material() {
		super();
	}
	/**
	 * Constructor, which creates Material with selected parameters.
	 * @param title Title of the material
	 * @param pageCount Page count of the material	
	 * @param available Availability of the material
	 * @param author Author of the material
	 * @param library Library of the material
	 * @param keywords Keywords of the material
	 */
	public Material(String title, int pageCount, boolean available, String author, String library, String keywords) {
		super();
		this.Title = title;
		this.PageCount = pageCount;
		this.Available = available;
		this.Author = author;
		this.Library = library;
		this.Keywords = keywords;
	}
	
	@Override
	public void ObjectInfo() {
		System.out.println("Title of book: "+this.getTitle());
		System.out.println("Number of pages: "+this.getPageCount());
		System.out.println("Name of author: "+this.getAuthor());
		System.out.println("Stored in: "+this.getLibrary());
	}
		
}