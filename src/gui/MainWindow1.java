package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import functions.BookHandler;
import users.Student;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * Administration window. User can choose desired action from the menu on the upward
 * side of the window.
 * @author Lukáš
 *
 */
public class MainWindow1 {

	private JFrame frmBk;

	public static void main(Student s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow1 window = new MainWindow1(s);
					window.frmBk.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Initialize the window.
	 * @param s Logged user
	 */
	public MainWindow1(Student s) {
		initialize(s);
	}
/**
 * Initialize the contents.
 * @param s
 */
	private void initialize(Student s) {
		frmBk = new JFrame();
		frmBk.setTitle("BookKeeper");
		frmBk.setBounds(100, 100, 510, 211);
		frmBk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBk.getContentPane().setLayout(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 11, 754, 21);
		frmBk.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Materials");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Material");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ChooseMatToAddWindow.main(null);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmRemovebook = new JMenuItem("Remove Material");
		mntmRemovebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteBookWindow.main(null);
			}
		});
		mnNewMenu.add(mntmRemovebook);
		
		JMenuItem mntmSearchbook = new JMenuItem("Search Material");
		mntmSearchbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			SearchWindow.main(s);
			}
		});
		mnNewMenu.add(mntmSearchbook);
		
		JMenuItem mntmEditMaterial = new JMenuItem("Edit Material");
		mntmEditMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertEditMatName.main(null);
			}
		});
		mnNewMenu.add(mntmEditMaterial);
		
		JMenu mnSomethingelse = new JMenu("TestFunctions");
		menuBar.add(mnSomethingelse);
		
		JMenuItem mntmVypisBooklib = new JMenuItem("Write BookLib");
		mntmVypisBooklib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			BookHandler.writeLibToSysout();
			}
		});
		mnSomethingelse.add(mntmVypisBooklib);
		
		JMenuItem mntmWriteusermatlib = new JMenuItem("WriteUserMatLib");
		mntmWriteusermatlib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			BookHandler.writeUserMatLibToSysout(s);
			}
		});
		mnSomethingelse.add(mntmWriteusermatlib);
		
		JMenu mnUseractions = new JMenu("UserActions");
		menuBar.add(mnUseractions);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frmBk.dispose();
			LoginWindow.main(null);
			}
		});
		
		JMenuItem mntmMyprofile = new JMenuItem("MyProfile");
		mntmMyprofile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ProfileWindow.main(s);
			}
		});
		mnUseractions.add(mntmMyprofile);
		mnUseractions.add(mntmLogOut);
		
		JLabel lbl1 = new JLabel("This is an administration window (for administrators only).");
		lbl1.setBounds(10, 81, 397, 14);
		frmBk.getContentPane().add(lbl1);
		
		JLabel lblIfYouWish = new JLabel("If you wish to return to Main Menu, click on the button below");
		lblIfYouWish.setBounds(10, 106, 397, 14);
		frmBk.getContentPane().add(lblIfYouWish);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			MainMenu.main(s);	
			frmBk.dispose();
			}
		});
		btnMainMenu.setBounds(10, 131, 111, 23);
		frmBk.getContentPane().add(btnMainMenu);
		
	}
}
