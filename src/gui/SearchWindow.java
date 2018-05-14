package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import functions.BookHandler;

import users.Student;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;


import books.Material;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
/**
 * Search window for searching through the database of stored materials. Results are
 * displayed on the JList, and its model refreshes after every search attempt.
 * @author Lukáš
 *
 */
public class SearchWindow {					
	private JFrame frmSearchwindow;
	private JTextField SearchTextField;
	JList<String> ResultsList = new JList<String>();


	public static void main(Student s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchWindow window = new SearchWindow(s);
					window.frmSearchwindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public SearchWindow(Student s) {
		initialize(s);
	}

	private void initialize(Student s) {
		BookHandler BookHandInstance = new BookHandler();
		

		frmSearchwindow = new JFrame();
		frmSearchwindow.setTitle("Search for material");
		frmSearchwindow.setBounds(100, 100, 600, 410);
		frmSearchwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSearchwindow.getContentPane().setLayout(null);
		
		SearchTextField = new JTextField();
		SearchTextField.setBounds(95, 26, 366, 20);
		frmSearchwindow.getContentPane().add(SearchTextField);
		SearchTextField.setColumns(10);

		JLabel lblSearchFor = new JLabel("Search for :");
		lblSearchFor.setBounds(10, 29, 75, 14);
		frmSearchwindow.getContentPane().add(lblSearchFor);
		
		JButton btnSearch = new JButton("Search"); 
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultListModel<String> Mdl = new DefaultListModel<String>();
				String[] titles = BookHandInstance.SearchForMaterial(SearchTextField.getText());
				for (int i = 0; i < BookHandInstance.sumOfFoundMaterials(SearchTextField.getText()) ; i++ ) {
					Mdl.addElement(titles[i]);
					} 
				ResultsList.setModel(Mdl); 
			}
		});
		btnSearch.setBounds(486, 25, 89, 23);
		btnSearch.setFocusable(true);
		frmSearchwindow.getContentPane().add(btnSearch);
		frmSearchwindow.getRootPane().setDefaultButton(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 562, 273);
		frmSearchwindow.getContentPane().add(scrollPane);
		scrollPane.setViewportView(ResultsList);
		ResultsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() == 2) {
					Material m = BookHandInstance.SearchSpecificMaterial(ResultsList.getSelectedValue());
					BookDetailWindow.main(m,s);
				}
			}
		});
		
		ResultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnBackBtn = new JButton("Back");
		btnBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSearchwindow.dispose();
			}
		});
		btnBackBtn.setBounds(486, 341, 89, 23);
		frmSearchwindow.getContentPane().add(btnBackBtn);
	}
}
