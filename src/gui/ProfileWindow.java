package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import functions.BookHandler;
import functions.StudentHandler;
import users.Student;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import books.Material;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
/**
 * Profile window - displays the info of an user and also list of owned materials.
 * @author Lukáš
 *
 */
public class ProfileWindow {

	private JFrame frmMyprofile;
	private JTextField NameField;
	private JTextField UsernameField;
	private JTextField YearOfStudyField;
	/**
	 * Create the application
	 * @param s Logged user
	 */
	public static void main(Student s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileWindow window = new ProfileWindow(s);
					window.frmMyprofile.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Initialize the window
	 * @param s Logged user
	 */
	public ProfileWindow(Student s) {
		initialize(s);
	}
	/**
	 * Initialize the contents
	 * @param s Logged user
	 */
	private void initialize(Student s) {
		StudentHandler StudHandInst = new StudentHandler();
		BookHandler BookHandInst = new BookHandler();
		//displays info of currently logged user
		frmMyprofile = new JFrame();
		frmMyprofile.setTitle("My BookKeeper Profile");
		frmMyprofile.setBounds(100, 100, 560, 427);
		frmMyprofile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMyprofile.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(24, 22, 57, 16);
		frmMyprofile.getContentPane().add(lblName);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(24, 49, 67, 14);
		frmMyprofile.getContentPane().add(lblUsername);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 156, 346, 187);
		frmMyprofile.getContentPane().add(scrollPane);
		
		JList<String> MaterialList = new JList<String>();
		scrollPane.setViewportView(MaterialList);
		DefaultListModel<String> Mdl = new DefaultListModel<String>();
		for (int i = 0; i < s.OwnedMatsCollection.size(); i++) {
			Mdl.addElement(s.OwnedMatsCollection.get(i).getTitle());
			} 
		MaterialList.setModel(Mdl); 
		
		
		JLabel lblMyMaterials = new JLabel("My materials (click to select):");
		lblMyMaterials.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMyMaterials.setBounds(24, 126, 195, 14);
		frmMyprofile.getContentPane().add(lblMyMaterials);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmMyprofile.dispose();
			}
		});
		btnBack.setBounds(429, 354, 89, 23);
		frmMyprofile.getContentPane().add(btnBack);
		
		NameField = new JTextField();
		NameField.setEditable(false);
		NameField.setBackground(SystemColor.control);
		NameField.setBounds(123, 21, 247, 20);
		frmMyprofile.getContentPane().add(NameField);
		NameField.setColumns(10);
		NameField.setText(s.getName());
		
		UsernameField = new JTextField();
		UsernameField.setEditable(false);
		UsernameField.setBackground(SystemColor.control);
		UsernameField.setBounds(123, 47, 247, 20);
		frmMyprofile.getContentPane().add(UsernameField);
		UsernameField.setColumns(10);
		UsernameField.setText(s.getUsername());
		
		JLabel lblYearOfStudy = new JLabel("Year of study :");
		lblYearOfStudy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYearOfStudy.setBounds(24, 74, 95, 14);
		frmMyprofile.getContentPane().add(lblYearOfStudy);
		
		YearOfStudyField = new JTextField();
		YearOfStudyField.setEditable(false);
		YearOfStudyField.setBackground(SystemColor.control);
		YearOfStudyField.setBounds(123, 72, 247, 20);
		frmMyprofile.getContentPane().add(YearOfStudyField);
		YearOfStudyField.setColumns(10);
		YearOfStudyField.setText(s.getYear());
		
		JButton btnRemoveMaterial = new JButton("Remove material");
		btnRemoveMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(frmMyprofile, "Remove this item from your collection?") == 0) {
					StudHandInst.RemoveFromCollection(s, MaterialList.getSelectedValue());
					Mdl.removeElement(MaterialList.getSelectedValue());
				}
			}
		});
		btnRemoveMaterial.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemoveMaterial.setBounds(380, 154, 138, 37);
		frmMyprofile.getContentPane().add(btnRemoveMaterial);
		
		JButton btnNewButton = new JButton("Show details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Material m = BookHandInst.SearchSpecificMaterial(MaterialList.getSelectedValue());
				BookDetailWindow.main(m,s);
			
			}
		});
		btnNewButton.setBounds(380, 202, 138, 33);
		frmMyprofile.getContentPane().add(btnNewButton);
		
		JLabel lblChooseAction = new JLabel("Choose action");
		lblChooseAction.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChooseAction.setBounds(380, 127, 136, 14);
		frmMyprofile.getContentPane().add(lblChooseAction);

	}
}
