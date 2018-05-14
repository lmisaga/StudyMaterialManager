package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import functions.BookHandler;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Window in which user enters the title of the book to be deleted.
 * @author Lukáš
 *
 */
public class DeleteBookWindow {

	private JFrame frmRemoveBook;
	private JTextField textField;

	/**
	 * Launch the application.
	 * @param args Abstract argument
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBookWindow window = new DeleteBookWindow();
					window.frmRemoveBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteBookWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRemoveBook = new JFrame();
		frmRemoveBook.setTitle("Remove material");
		frmRemoveBook.setBounds(100, 100, 450, 135);
		frmRemoveBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRemoveBook.getContentPane().setLayout(null);
		
		JLabel lblInsertTitle = new JLabel("Insert an EXACT title of a material you want to remove below");
		lblInsertTitle.setBounds(10, 11, 334, 14);
		frmRemoveBook.getContentPane().add(lblInsertTitle);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 315, 20);
		frmRemoveBook.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookHandler inst = new BookHandler();
				inst.RemoveFromMaterialList(textField.getText());
				frmRemoveBook.dispose();
			}
		});
		btnConfirm.setBounds(335, 35, 89, 23);
		frmRemoveBook.getContentPane().add(btnConfirm);
	}
}
