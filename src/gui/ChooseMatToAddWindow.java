package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Switch between opening AddBookWindow or AddEBookWindow
 * @author Lukáš
 *
 */
public class ChooseMatToAddWindow {

	private JFrame frmChooseTheMaterial;

	/**
	 * Launch the application.
	 * @param args Abstract argument
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseMatToAddWindow window = new ChooseMatToAddWindow();
					window.frmChooseTheMaterial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChooseMatToAddWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChooseTheMaterial = new JFrame();
		frmChooseTheMaterial.setTitle("Choose the material type");
		frmChooseTheMaterial.setBounds(100, 100, 450, 128);
		frmChooseTheMaterial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChooseTheMaterial.getContentPane().setLayout(null);
		
		JLabel lblPleaseChooseMaterial = new JLabel("Please choose material type :");
		lblPleaseChooseMaterial.setBounds(9, 11, 177, 14);
		frmChooseTheMaterial.getContentPane().add(lblPleaseChooseMaterial);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Book", "E-Book (with source URL)"}));
		comboBox.setBounds(196, 8, 175, 20);
		frmChooseTheMaterial.getContentPane().add(comboBox);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (comboBox.getSelectedItem().toString() == "Book" ) {
				AddBookWindow.main(null);
				}
			else 
				AddEBookWindow.main(null);
			
			frmChooseTheMaterial.dispose();	
				}
			});
		
		btnConfirm.setBounds(196, 35, 89, 23);
		frmChooseTheMaterial.getContentPane().add(btnConfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmChooseTheMaterial.dispose();
			}
		});
		btnBack.setBounds(9, 55, 89, 23);
		frmChooseTheMaterial.getContentPane().add(btnBack);
	}
}
