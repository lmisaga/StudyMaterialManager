package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import functions.InsufficientPermissionsException;
import functions.StudentHandler;
import users.Student;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Main menu, window opened after successful login.
 * @author Lukáš
 *
 */
public class MainMenu {

	private JFrame formMainMenu;

	/**
	 * Launch the application.
	 * @param s Logged user
	 */
	public static void main(Student s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu(s);
					window.formMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the application window.
	 * @param s Logged user
	 */
	public MainMenu(Student s) {
		initialize(s);
	}

	/**
	 * Initialize the contents of the window.
	 * @param s Logged user
	 */
	private void initialize(Student s) {
		formMainMenu = new JFrame();
		StudentHandler sh = new StudentHandler();
		formMainMenu.setTitle("BookKeeper");
		formMainMenu.setBounds(100, 100, 521, 317);
		formMainMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formMainMenu.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchWindow.main(s);
			}
		});
		btnNewButton.setBounds(21, 21, 145, 105);
		formMainMenu.getContentPane().add(btnNewButton);
		
		JButton btnMyProfile = new JButton("My Profile");
		btnMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProfileWindow.main(s);
		
			}
		});
		btnMyProfile.setBounds(178, 21, 145, 105);
		formMainMenu.getContentPane().add(btnMyProfile);
		
		JButton btnAdministration = new JButton("Administration");
		btnAdministration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					sh.verifyAndPass(s);
					formMainMenu.dispose();
				} catch (InsufficientPermissionsException e) {
					JOptionPane.showMessageDialog(null, "Insufficient permissions.", " ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdministration.setBounds(335, 21, 145, 105);
		formMainMenu.getContentPane().add(btnAdministration);
		
		JTextArea txtrSearchInDatabase = new JTextArea();
		txtrSearchInDatabase.setBackground(UIManager.getColor("background"));
		txtrSearchInDatabase.setEditable(false);
		txtrSearchInDatabase.setLineWrap(true);
		txtrSearchInDatabase.setWrapStyleWord(true);
		txtrSearchInDatabase.setText("Search in database of stored study materials. You can search by title, author, keywords...");
		txtrSearchInDatabase.setBounds(31, 144, 134, 99);
		formMainMenu.getContentPane().add(txtrSearchInDatabase);
		
		JTextArea txtrViewYourProfile = new JTextArea();
		txtrViewYourProfile.setWrapStyleWord(true);
		txtrViewYourProfile.setText("View your profile, which also contains a list of owned materials.");
		txtrViewYourProfile.setLineWrap(true);
		txtrViewYourProfile.setEditable(false);
		txtrViewYourProfile.setBackground((Color) null);
		txtrViewYourProfile.setBounds(188, 144, 134, 69);
		formMainMenu.getContentPane().add(txtrViewYourProfile);
		
		JTextArea txtrOpensUpAdministration = new JTextArea();
		txtrOpensUpAdministration.setWrapStyleWord(true);
		txtrOpensUpAdministration.setText("Opens up administration window. Admin-only.");
		txtrOpensUpAdministration.setLineWrap(true);
		txtrOpensUpAdministration.setEditable(false);
		txtrOpensUpAdministration.setBackground((Color) null);
		txtrOpensUpAdministration.setBounds(345, 144, 134, 69);
		formMainMenu.getContentPane().add(txtrOpensUpAdministration);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formMainMenu.dispose();
				LoginWindow.main(null);
			}
		});
		btnLogOut.setBounds(382, 240, 98, 26);
		formMainMenu.getContentPane().add(btnLogOut);
	}
}
