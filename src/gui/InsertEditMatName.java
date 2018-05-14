package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import books.Material;
import functions.BookHandler;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Dialog window in which administrator can enter the title of the material to be edited.
 * @author Lukáš
 *
 */
public class InsertEditMatName extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Create the dialog window.
	 * @param args abstract parameter
	 */
	public static void main(String[] args) {
		try {
			InsertEditMatName dialog = new InsertEditMatName();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertEditMatName() {
		setTitle("Edit Material");
		setBounds(100, 100, 450, 151);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 48, 414, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblInsertAnExact = new JLabel("Insert an EXACT title of the Material you want to edit:");
		lblInsertAnExact.setBounds(10, 11, 354, 14);
		contentPanel.add(lblInsertAnExact);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						BookHandler BookHandInst = new BookHandler();
						Material TestMat = BookHandInst.SearchSpecificMaterial(textField.getText());
						EditBookWindow.main(null, textField.getText(), BookHandler.Lib.indexOf(TestMat));
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
			}
		}
	}
}
