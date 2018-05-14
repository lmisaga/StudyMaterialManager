package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import users.Student;
import functions.StudentHandler;
import functions.UserNotFoundException;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.UIManager;
/**
 * Login window, welcome screen - the first screen after the program launch.
 * @author Lukáš
 *
 */
public class LoginWindow {

	private JFrame frmLogScreen;
	private JTextField txtLogin;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					LoginWindow window = new LoginWindow();
					window.frmLogScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginWindow() {
		initialize();
	}
	
	private void initialize() {
		StudentHandler StudentInst = new StudentHandler();
		frmLogScreen = new JFrame();
		frmLogScreen.setTitle("BookKeeper Welcome Screen");
		frmLogScreen.setBounds(100, 100, 354, 400);
		frmLogScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogScreen.getContentPane().setLayout(null);
		
		txtLogin = new JTextField();
		txtLogin.setText("admin1");
		txtLogin.setBounds(73, 11, 86, 20);
		frmLogScreen.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblLogin = new JLabel("Username");
		lblLogin.setBounds(10, 14, 68, 14);
		frmLogScreen.getContentPane().add(lblLogin);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("heslo");
		passwordField.setBounds(73, 42, 86, 20);
		frmLogScreen.getContentPane().add(passwordField);
		passwordField.setText("admin1");
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 39, 68, 14);
		frmLogScreen.getContentPane().add(lblPassword);
		
		JButton btnPrihlas = new JButton("Log in");
		btnPrihlas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					@SuppressWarnings("deprecation")
					Student S = StudentInst.findMatch(txtLogin.getText(), passwordField.getText());
					if (S == null) {
						throw new UserNotFoundException();
					} else {
						MainMenu.main(S);
					}
					frmLogScreen.dispose();
				
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (UserNotFoundException e3) {
					JOptionPane.showMessageDialog(null, "Username or password may be incorrect!", "Error",JOptionPane.ERROR_MESSAGE);

				}
			}
		});
 		
		btnPrihlas.setBounds(217, 10, 111, 52);
		frmLogScreen.getContentPane().add(btnPrihlas);
		frmLogScreen.getRootPane().setDefaultButton(btnPrihlas);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterWindow.main(null);
				frmLogScreen.dispose();
			}
		});
		
		btnRegister.setBounds(217, 63, 111, 23);
		frmLogScreen.getContentPane().add(btnRegister);
		
		JTextPane textPaneInfo = new JTextPane();
		textPaneInfo.setBackground(UIManager.getColor("Button.background"));
		textPaneInfo.setFont(new Font("Calibri", Font.PLAIN, 12));
		textPaneInfo.setText("Welcome to BookKeeper!\r\n\r\nThis application is a study material manager. You can search throughout the database of various materials, and add them to your collection, so you can view them later and see where are they stored (in case you forgot).\r\n\r\nIn case you're an Administrator, you can choose the Administration tab in the Main Menu window, where you can edit the material library and do any necessary tests with the database.\r\n");
		textPaneInfo.setEditable(false);
		textPaneInfo.setBounds(10, 95, 318, 241);
		frmLogScreen.getContentPane().add(textPaneInfo);
	}
}