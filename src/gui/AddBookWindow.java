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
 * Window which opens when new Material (book) is being added.
 * @author Lukáš
 *
 */
public class AddBookWindow {

	private JFrame frame;
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
	 * @param args Abstract argument
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookWindow window = new AddBookWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddBookWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("AddBook");
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(10, 36, 250, 20);
		frame.getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		textFieldPages = new JTextField();
		textFieldPages.setColumns(10);
		textFieldPages.setBounds(10, 92, 63, 20);
		frame.getContentPane().add(textFieldPages);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setBounds(10, 148, 250, 20);
		frame.getContentPane().add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		
		JRadioButton btnYes = new JRadioButton("Yes");
		btnYes.setBounds(10, 369, 54, 23);
		frame.getContentPane().add(btnYes);
		
		JRadioButton btnNo = new JRadioButton("No");
		btnNo.setBounds(66, 369, 54, 23);
		frame.getContentPane().add(btnNo);
		/******************labels********************/
		lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblTitle);
		
		lblPageCount = new JLabel("Page count");
		lblPageCount.setBounds(10, 67, 82, 14);
		frame.getContentPane().add(lblPageCount);
		
		lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(10, 123, 46, 14);
		frame.getContentPane().add(lblAuthor);
		
		lblLibrary = new JLabel("In library");
		lblLibrary.setBounds(10, 178, 130, 14);
		frame.getContentPane().add(lblLibrary);
		
		JLabel lblKeywords = new JLabel("Keywords");
		lblKeywords.setBounds(10, 237, 95, 14);
		frame.getContentPane().add(lblKeywords);
		
		JLabel lblAvailable = new JLabel("Is available?");
		lblAvailable.setBounds(10, 344, 82, 14);
		frame.getContentPane().add(lblAvailable);
		/******************labels********************/
		JTextArea KeywordsTextArea = new JTextArea();
		KeywordsTextArea.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		KeywordsTextArea.setToolTipText("Insert some keywords which are connected to the added material.");
		KeywordsTextArea.setBounds(10, 262, 250, 71);
		frame.getContentPane().add(KeywordsTextArea);
		
		JButton btnNewButton = new JButton("Confirm");
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
					 BookHandInst.AddToMaterialList(textFieldTitle.getText(), strany, btnYes.isSelected(), 
						textFieldAuthor.getText(), textFieldLib.getText(),KeywordsTextArea.getText());  
				} catch (IncorrectInputException e) {
					JOptionPane.showMessageDialog(null, "No textfield can remain empty!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				frame.dispose();
			}
		});
		btnNewButton.setBounds(66, 427, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		textFieldLib = new JTextField();
		textFieldLib.setBounds(10, 203, 250, 20);
		frame.getContentPane().add(textFieldLib);
		textFieldLib.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(271, 427, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
