package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import functions.BookHandler;
import functions.IncorrectInputException;
/**
 * Form for adding/editing material.
 * @author Lukáš
 *
 */
public class EditBookWindow {

	private JFrame frmEditMaterial;
	private JTextField textFieldTitle;
	private JTextField textFieldPages;
	private JTextField textFieldAuthor;
	private JLabel lblTitle;
	private JLabel lblPageCount;
	private JLabel lblAuthor;
	private JTextField textFieldLib;
	private JLabel lblLibrary;

	/**
	 * Launch the application.
	 * @param index Index of edited material.
	 * @param MatToEditTitle Title of material which is going to be edited
	 * @param args abstract parameter
	 */
	public static void main(String[] args, String MatToEditTitle, int index) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditBookWindow window = new EditBookWindow(MatToEditTitle,index);
					window.frmEditMaterial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Initialize the window
	 * @param MatToEditTitle Title of the material to be edited
	 * @param index Index of the material to be edited
	 */
	public EditBookWindow(String MatToEditTitle, int index) {
		initialize(MatToEditTitle,index);
	}

	/**
	 * Initialize the contents
	 * @param MatToEditTitle Title of the material to be edited
	 * @param index Index of the material to be edited
	 */
	private void initialize(String MatToEditTitle, int index) {
		frmEditMaterial = new JFrame();
		frmEditMaterial.setTitle("Edit Material");
		frmEditMaterial.setBounds(100, 100, 349, 500);
		frmEditMaterial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditMaterial.getContentPane().setLayout(null);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(10, 36, 272, 20);
		frmEditMaterial.getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		textFieldPages = new JTextField();
		textFieldPages.setColumns(10);
		textFieldPages.setBounds(10, 92, 63, 20);
		frmEditMaterial.getContentPane().add(textFieldPages);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setBounds(10, 148, 272, 20);
		frmEditMaterial.getContentPane().add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		
		JRadioButton btnYes = new JRadioButton("Yes");
		btnYes.setBounds(10, 369, 54, 23);
		frmEditMaterial.getContentPane().add(btnYes);
		
		JRadioButton btnNo = new JRadioButton("No");
		btnNo.setBounds(66, 369, 54, 23);
		frmEditMaterial.getContentPane().add(btnNo);

		lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 11, 46, 14);
		frmEditMaterial.getContentPane().add(lblTitle);
		
		lblPageCount = new JLabel("Page count");
		lblPageCount.setBounds(10, 67, 82, 14);
		frmEditMaterial.getContentPane().add(lblPageCount);
		
		lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(10, 123, 46, 14);
		frmEditMaterial.getContentPane().add(lblAuthor);
		
		lblLibrary = new JLabel("In library");
		lblLibrary.setBounds(10, 178, 130, 14);
		frmEditMaterial.getContentPane().add(lblLibrary);
		
		JLabel lblKeywords = new JLabel("Keywords");
		lblKeywords.setBounds(10, 237, 95, 14);
		frmEditMaterial.getContentPane().add(lblKeywords);
		
		JLabel lblAvailable = new JLabel("Is available?");
		lblAvailable.setBounds(10, 344, 82, 14);
		frmEditMaterial.getContentPane().add(lblAvailable);
	
		JTextArea KeywordsTextArea = new JTextArea();
		KeywordsTextArea.setLineWrap(true);
		KeywordsTextArea.setWrapStyleWord(true);
		KeywordsTextArea.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		KeywordsTextArea.setToolTipText("Insert some keywords which are connected to the added material.");
		KeywordsTextArea.setBounds(10, 262, 272, 71);
		frmEditMaterial.getContentPane().add(KeywordsTextArea);
		
		JButton btnNewButton = new JButton("Confirm changes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int strany = 0;
				BookHandler BookHandInst = new BookHandler();
				try {
						strany = Integer.parseInt(textFieldPages.getText()) ; 
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Incorrect number in pages!", "Error", JOptionPane.ERROR_MESSAGE);				
						}
				try {
					if (BookHandInst.EditMaterial(MatToEditTitle, textFieldTitle.getText(), strany, 
						textFieldAuthor.getText(), textFieldLib.getText(),KeywordsTextArea.getText(), btnYes.isSelected(), index) == false) {
						throw new IncorrectInputException();
					}
				} catch (IncorrectInputException e) {
					JOptionPane.showMessageDialog(null, "Error in editing material. Please, try again.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				frmEditMaterial.dispose();
			}
		});
		btnNewButton.setBounds(66, 427, 144, 23);
		frmEditMaterial.getContentPane().add(btnNewButton);
		
		textFieldLib = new JTextField();
		textFieldLib.setBounds(10, 203, 272, 20);
		frmEditMaterial.getContentPane().add(textFieldLib);
		textFieldLib.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmEditMaterial.dispose();
			}
		});
		btnBack.setBounds(236, 427, 89, 23);
		frmEditMaterial.getContentPane().add(btnBack);
	}
}