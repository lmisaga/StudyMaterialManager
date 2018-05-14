package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Choice;

import functions.ExistingUserException;
import functions.IncorrectInputException;
import functions.StudentHandler;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;
/**
 * Registration form.
 * @author Lukáš
 *
 */
public class RegisterWindow {

	private JFrame frmBookkeeperRegistration;
	private JTextField textField_meno;
	private JTextField textField_username;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow window = new RegisterWindow();
					window.frmBookkeeperRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterWindow() {
		initialize();
	}

	private void initialize() {
		StudentHandler inst = new StudentHandler();
		
		frmBookkeeperRegistration = new JFrame();
		frmBookkeeperRegistration.setTitle("Bookkeeper Registration");
		frmBookkeeperRegistration.setBounds(100, 100, 350, 420);
		frmBookkeeperRegistration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBookkeeperRegistration.getContentPane().setLayout(null);
		
		JLabel lblFullName = new JLabel("Full name");
		lblFullName.setBounds(40, 66, 76, 14);
		frmBookkeeperRegistration.getContentPane().add(lblFullName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(40, 106, 76, 14);
		frmBookkeeperRegistration.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(40, 139, 76, 14);
		frmBookkeeperRegistration.getContentPane().add(lblPassword);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(40, 182, 76, 14);
		frmBookkeeperRegistration.getContentPane().add(lblYear);
		
		Choice choiceRocnik = new Choice();
		choiceRocnik.setBounds(158, 176, 143, 20);
		frmBookkeeperRegistration.getContentPane().add(choiceRocnik);
		choiceRocnik.add("First year");
		choiceRocnik.add("Second year");
		choiceRocnik.add("Third year");
		choiceRocnik.add("Fourth year");
		choiceRocnik.add("Final year");
		choiceRocnik.add("PhD studies");

		JButton button = new JButton("Registrer");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					inst.TestIfEmpty(textField_username.getText(), 
							passwordField.getText(), textField_meno.getText());
					
					inst.TestAndRegister(textField_username.getText(), passwordField.getText(), 
							textField_meno.getText(), choiceRocnik.getSelectedItem().toString(), 0);
						 
						JOptionPane.showMessageDialog(null, "Registration successful\n "
							+ "Now you may log in", "Success",JOptionPane.INFORMATION_MESSAGE);
						
						frmBookkeeperRegistration.dispose();
						LoginWindow.main(null);
						
				} catch (IncorrectInputException e1) { 		/**CUSTOM exception - throwable only if the form is not filled out properly**/
					JOptionPane.showMessageDialog(null, "Incorrect input in Register form", "Error",JOptionPane.ERROR_MESSAGE);				
				} catch (ExistingUserException e1) {
						JOptionPane.showMessageDialog(null, "This username is already in use!", "Error",JOptionPane.ERROR_MESSAGE);
				}
				}
			
		});
		
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(40, 236, 261, 31);
		frmBookkeeperRegistration.getContentPane().add(button);
		frmBookkeeperRegistration.getRootPane().setDefaultButton(button);
				
		textField_meno = new JTextField();
		textField_meno.setBounds(158, 63, 143, 20);
		frmBookkeeperRegistration.getContentPane().add(textField_meno);
		textField_meno.setColumns(10);
		
		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setBounds(158, 103, 143, 20);
		frmBookkeeperRegistration.getContentPane().add(textField_username);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 136, 143, 20);
		frmBookkeeperRegistration.getContentPane().add(passwordField);
		
		JButton btnSp = new JButton("Back");
		btnSp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmBookkeeperRegistration.dispose();
			}
		});
		btnSp.setBounds(118, 347, 89, 23);
		frmBookkeeperRegistration.getContentPane().add(btnSp);
		
		JButton btnAdmin = new JButton("ADMIN register");
		btnAdmin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					inst.TestAndRegister(textField_username.getText(), passwordField.getText(), 
							textField_meno.getText(), "ADMIN", 0, true); 
						JOptionPane.showMessageDialog(null, "Registration successful" + ", now you may log in.", "Success",JOptionPane.INFORMATION_MESSAGE);
						frmBookkeeperRegistration.dispose();
						LoginWindow.main(null);
				} 
				catch (IncorrectInputException e1) { 	
					JOptionPane.showMessageDialog(null, "Incorrect input in Register form", "Error",JOptionPane.ERROR_MESSAGE);				
				}
			}
		});
		btnAdmin.setBounds(40, 273, 261, 23);
		frmBookkeeperRegistration.getContentPane().add(btnAdmin);
	}
}
