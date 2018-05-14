package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import books.EBookMaterial;
import functions.BookHandler;
import functions.IncorrectInputException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Window that opens when new EBook is being added.
 * @author Lukáš
 *
 */
public class AddEBookWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 * @param args Abstract argument
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEBookWindow window = new AddEBookWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddEBookWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		BookHandler BookHandInst = new BookHandler();
		frame = new JFrame();
		frame.setTitle("AddEBook");
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField textFieldTitle = new JTextField();
		textFieldTitle.setBounds(10, 36, 312, 20);
		frame.getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JTextField textFieldPages = new JTextField();
		textFieldPages.setColumns(10);
		textFieldPages.setBounds(10, 92, 70, 20);
		frame.getContentPane().add(textFieldPages);
		
		JTextField textFieldAuthor = new JTextField();
		textFieldAuthor.setBounds(10, 148, 312, 20);
		frame.getContentPane().add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		/******************labels********************/
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblPageCount = new JLabel("Page count");
		lblPageCount.setBounds(10, 67, 82, 14);
		frame.getContentPane().add(lblPageCount);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(10, 123, 46, 14);
		frame.getContentPane().add(lblAuthor);
		
		JLabel lblURL = new JLabel("Source URL");
		lblURL.setBounds(10, 178, 130, 14);
		frame.getContentPane().add(lblURL);
		
		JLabel lblKeywords = new JLabel("Keywords");
		lblKeywords.setBounds(10, 237, 95, 14);
		frame.getContentPane().add(lblKeywords);
		
		/******************labels********************/
		JTextArea KeywordsTextArea = new JTextArea();
		KeywordsTextArea.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		KeywordsTextArea.setToolTipText("Insert keywords which resemble the material and its topic");
		KeywordsTextArea.setBounds(10, 262, 312, 71);
		frame.getContentPane().add(KeywordsTextArea);
		
		textField = new JTextField();
		textField.setBounds(10, 203, 312, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int strany = 0;
				try {
					strany = Integer.parseInt(textFieldPages.getText()); 
					EBookMaterial E = new EBookMaterial(textFieldTitle.getText(), strany, true, textFieldAuthor.getText(), 
							textField.getText(), KeywordsTextArea.getText());
					BookHandInst.AddToMaterialList(E);
					frame.dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Incorrect number in pages!", "Error", JOptionPane.ERROR_MESSAGE);				
				} catch (IncorrectInputException e1) {
					JOptionPane.showMessageDialog(null, "Incorrect input!", "Error", JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		btnConfirm.setBounds(233, 396, 89, 23);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
			}
		});
		btnBack.setBounds(10, 396, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
