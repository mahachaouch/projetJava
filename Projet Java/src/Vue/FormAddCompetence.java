package Vue;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Modele.CSV;

public class FormAddCompetence extends JFrame {

	private static JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;

	public void run() {

		try {

			FormAddCompetence window = new FormAddCompetence();

			window.frame.setVisible(true);

			window.frame.setTitle("Ajout d'une compétence");

			JLabel lblCode = new JLabel("Code");
			lblCode.setBounds(80, 65, 60, 14);
			frame.getContentPane().add(lblCode);

			textField = new JTextField();
			textField.setBounds(148, 62, 86, 20);
			frame.getContentPane().add(textField);
			textField.setColumns(10);

			JLabel lblDomaine = new JLabel("Domaine");
			lblDomaine.setBounds(80, 115, 80, 14);
			frame.getContentPane().add(lblDomaine);

			textField_2 = new JTextField();
			textField_2.setBounds(148, 112, 247, 20);
			frame.getContentPane().add(textField_2);
			textField_2.setColumns(10);

			JLabel lblTraduction = new JLabel("Traduction");
			lblTraduction.setBounds(80, 162, 72, 14);
			frame.getContentPane().add(lblTraduction);

			textField_1 = new JTextField();
			textField_1.setBounds(148, 157, 247, 20);
			frame.getContentPane().add(textField_1);
			textField_1.setColumns(10);

			JButton btnClear = new JButton("Clear");
			btnClear.setBounds(312, 387, 89, 23);
			frame.getContentPane().add(btnClear);

			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					textField_2.setText(null);
					textField.setText(null);
					textField_1.setText(null);
				}

			});

			JButton btnSubmit = new JButton("submit");

			btnSubmit.setBounds(65, 387, 89, 23);
			frame.getContentPane().add(btnSubmit);

			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (textField.getText().isEmpty() || (textField_2.getText().isEmpty())
							|| (textField_1.getText().isEmpty()))
						JOptionPane.showMessageDialog(null, "Veuillez remplir le formulaire svp");
					else {
						JOptionPane.showMessageDialog(null, "Compétence ajoutée");
						// ajouter la nouvelle mission dans le fichier
						// liste_mission
						String seperator = ";";

						// préparer la line à ajouter dans le fichier
						String line = textField.getText() + seperator + textField_2.getText() + seperator
								+ textField_1.getText();
						try {
							CSV.addRawCsv("liste_competences.csv", line);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public FormAddCompetence() {

		initialize();

	}

	/**
	 * 
	 * Initialize the contents of the frame.
	 * 
	 */

	void initialize() {

		frame = new JFrame();

		frame.setBounds(100, 100, 730, 489);

		frame.getContentPane().setLayout(null);

	}

}
