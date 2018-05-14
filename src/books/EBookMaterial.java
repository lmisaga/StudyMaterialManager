package books;

import java.io.Serializable;

import users.DisplayInfo;
/**
 * E-Book Material extends the Material type. Default Material parameter "Library" is morphed into "URL" parameter.
 * @author Lukáš
 *
 */
public class EBookMaterial extends Material implements Serializable,DisplayInfo{	
	
	private static final long serialVersionUID = 0;
	private String URL;
	
	public EBookMaterial(String title, int pageCount, boolean available, String author, String library, String keywords) {
		super(title, pageCount, available, author, library, keywords);
		this.URL = library;
	}
	/**
	 * Sets an URL (link) to the Material.
	 * @param url URL (link) to the material
	 */
	public void setURL(String url) {
		this.URL = url;
	}
	/**
	 * Returns an URL (link) to the Material.
	 * @return URL of the material
	 */
	public String getURL() {
		return this.URL;
	}
	
	@Override
	public void ObjectInfo() {
		System.out.println("Title of book: "+this.getTitle());
		System.out.println("Number of pages: "+this.getPageCount());
		System.out.println("Name of author: "+this.getAuthor());
		System.out.println("URL: "+this.getURL());
	}
}