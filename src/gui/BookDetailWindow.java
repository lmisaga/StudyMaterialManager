package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;

import books.Material;
import functions.MatAlreadyOwnedException;
import functions.StudentHandler;
import users.Student;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
/**
 * Window which displays details of the selected book (material).
 * @author Lukáš
 *
 */
public class BookDetailWindow {

	private JFrame frmMaterialDetails;
	private JTextField textFieldTitle;
	private JTextField textFieldPages;
	private JTextField textFieldAuthor;
	private JTextField textFieldKeywords;
	private JTextField textFieldLibrary;

	/**
	 * Launch the application.
	 * @param m Material of which the details are displayed
	 * @param s Logged user
	 */
	public static void main(Material m, Student s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookDetailWindow window = new BookDetailWindow(m,s);
					window.frmMaterialDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param M Material of which the details are displayed
	 * @param S Logged user
	 */
	public BookDetailWindow(Material M,Student S) {
		initialize(M,S);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Material m, Student s) {		
		StudentHandler inst = new StudentHandler();
		frmMaterialDetails = new JFrame();
		frmMaterialDetails.setTitle("Material Details");
		frmMaterialDetails.setBounds(100, 100, 450, 497);
		frmMaterialDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMaterialDetails.getContentPane().setLayout(null);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setEditable(false);
		textFieldTitle.setText(m.getTitle());
		textFieldTitle.setBounds(43, 48, 333, 20);
		frmMaterialDetails.getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		textFieldPages = new JTextField();
		textFieldPages.setEditable(false);
		textFieldPages.setText(String.valueOf(m.getPageCount()));
		textFieldPages.setBounds(43, 106, 70, 20);
		frmMaterialDetails.getContentPane().add(textFieldPages);
		textFieldPages.setColumns(10);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setEditable(false);
		textFieldAuthor.setText(m.getAuthor());
		textFieldAuthor.setBounds(43, 164, 333, 20);
		frmMaterialDetails.getContentPane().add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		
		textFieldLibrary = new JTextField();
		textFieldLibrary.setEditable(false);
		textFieldLibrary.setText(m.getLibrary());
		textFieldLibrary.setBounds(43, 222, 333, 20);
		frmMaterialDetails.getContentPane().add(textFieldLibrary);
		textFieldLibrary.setColumns(10);
		
		textFieldKeywords = new JTextField();
		textFieldKeywords.setEditable(false);
		textFieldKeywords.setText(m.getKeywords());
		textFieldKeywords.setBounds(43, 280, 333, 45);
		frmMaterialDetails.getContentPane().add(textFieldKeywords);
		textFieldKeywords.setColumns(10);
		
		JButton btnAddToCol = new JButton("Add to collection");
		btnAddToCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
					try {
						if (inst.AddToCollection(s, m) == false) 
							throw new MatAlreadyOwnedException();
						else { 
							JOptionPane.showMessageDialog(frmMaterialDetails, "Successfully added to your collection.",
									"Success", JOptionPane.INFORMATION_MESSAGE);
							frmMaterialDetails.dispose();
						}
					} 
					catch (MatAlreadyOwnedException e1) {
						JOptionPane.showMessageDialog(frmMaterialDetails, "This material is already in your collection!", 
								"Error", JOptionPane.ERROR_MESSAGE);
						}
				}
			});
		btnAddToCol.setBounds(231, 384, 145, 31);
		frmMaterialDetails.getContentPane().add(btnAddToCol);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(43, 21, 55, 16);
		frmMaterialDetails.getContentPane().add(lblTitle);
		
		JLabel lblPageCount = new JLabel("Page Count");
		lblPageCount.setBounds(43, 79, 82, 16);
		frmMaterialDetails.getContentPane().add(lblPageCount);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(43, 137, 55, 16);
		frmMaterialDetails.getContentPane().add(lblAuthor);
		
		JLabel lblKeywords = new JLabel("Keywords");
		lblKeywords.setBounds(43, 253, 82, 16);
		frmMaterialDetails.getContentPane().add(lblKeywords);
		
		JLabel lblNotAvailable = new JLabel("Material not available!");
		lblNotAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotAvailable.setBounds(231, 362, 145, 16);
		lblNotAvailable.setVisible(false);
		if(!m.isAvailable()) {
			lblNotAvailable.setVisible(true);
		}
		frmMaterialDetails.getContentPane().add(lblNotAvailable);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(43, 195, 70, 16);
		frmMaterialDetails.getContentPane().add(lblLocation);
		
	}
}
