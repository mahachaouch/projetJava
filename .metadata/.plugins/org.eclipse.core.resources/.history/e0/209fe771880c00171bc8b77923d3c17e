package Vue;

import Modele.CSV;
import Modele.CSVModele;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class FormAddMission extends JFrame {
	VueMissions vm;
	public FormAddMission(VueMissions vm) {
		this.vm=vm;
		this.setTitle("Ajout d'une mission");

		JTextField textField = new JTextField();
		textField.setBounds(162, 62, 86, 20);
		this.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblCode = new JLabel("Code Mission");
		lblCode.setBounds(80, 65, 90, 14);
		this.getContentPane().add(lblCode);

		JLabel lblDate = new JLabel("Date début");
		lblDate.setBounds(80, 115, 80, 14);
		this.getContentPane().add(lblDate);

		JTextField textField_2 = new JTextField();
		textField_2.setBounds(162, 112, 247, 20);
		this.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblDuree = new JLabel("Durée");
		lblDuree.setBounds(80, 162, 46, 14);
		this.getContentPane().add(lblDuree);

		JTextField textFiled_1 = new JTextField();
		textFiled_1.setBounds(162, 157, 247, 20);
		this.getContentPane().add(textFiled_1);
		textFiled_1.setColumns(10);

		JButton btnClear = new JButton("Clear");

		btnClear.setBounds(312, 387, 89, 23);
		this.getContentPane().add(btnClear);

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(null);
				textField.setText(null);
				textFiled_1.setText(null);
			}
		});

		JButton btnSubmit = new JButton("submit");
		
		btnSubmit.setBounds(65, 387, 89, 23);
		this.getContentPane().add(btnSubmit);

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().isEmpty() || (textField_2.getText().isEmpty())
						|| (textFiled_1.getText().isEmpty()))
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs svp");
				else {
					JOptionPane.showMessageDialog(null, "Mission ajoutée");
					// ajouter la nouvelle mission dans le fichier liste_mission
					String seperator = ";";
					String line = textField.getText() + seperator + textField_2.getText() + seperator
							+ textFiled_1.getText();
					//
					try {
						CSV.addRawCsv("liste_mission.csv", line);
						String Directory = System.getProperty("user.dir");
						Directory+="\\src\\Bd\\liste_mission.csv";
						File fichier=new File(Directory);
						CSV csv = new CSV();
						CSVModele modele = new CSVModele();
						ArrayList<String[]> donneeCSV = csv.ReadCSVfile(fichier);
						modele.ajouterDonnee(donneeCSV);
						vm.tableview.setModel(modele);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		this .setBounds(100, 100, 730, 489);
		
		this.getContentPane().setLayout(null);
		
		this.setVisible(true);
	}
}
