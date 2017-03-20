package Vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.TableView;
import javax.swing.text.html.ListView;

import Controleur.ControleurGestionMission;
import Modele.CSV;
import Modele.CSVModele;
import Modele.Personne;

public class VueGestionMission extends JPanel implements Serializable {
	final int MAX = 100;
	public JList list = new JList();
	public JTable tableview = new JTable();
	JButton Bajouter;

	public VueGestionMission() {
		this.setLayout(new BorderLayout());

		tableview.setFillsViewportHeight(true);

		// Ajouter les données CSV
		String Directory = System.getProperty("user.dir");
		Directory += "\\src\\Bd\\liste_personnel.csv";
		File fichier = new File(Directory);
		CSV csv = new CSV();
		CSVModele modele = new CSVModele();
		ArrayList<String[]> donneeCSV = csv.ReadCSVfile(fichier);
		modele.ajouterDonnee(donneeCSV);
		tableview.setModel(modele);
		tableview.setAutoCreateRowSorter(true);
		ListSelectionModel listSelectionModel = tableview.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ControleurGestionMission(this));

		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BorderLayout());
		westPanel.add(tableview.getTableHeader(), BorderLayout.PAGE_START);
		westPanel.add(tableview, BorderLayout.WEST);
		JScrollPane scrollPane = new JScrollPane(tableview);
		westPanel.add(scrollPane);

		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BorderLayout());
		JLabel comp = new JLabel("Compétences :");
		JScrollPane Scroll = new JScrollPane(list);
		Bajouter = new JButton("Ajouter");
		Bajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openAjouter(e);
			}
		});
		JButton BModifier = new JButton("Modifier");
		BModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openModifier(e);
			}
		});
		JButton BSupprimer = new JButton("Supprimer");
		BSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					supprimerPers(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		eastPanel.add(Scroll, BorderLayout.NORTH);
		eastPanel.add(comp, BorderLayout.NORTH);
		eastPanel.add(list);

		// SouthEast Pane
		JPanel SouthEast = new JPanel();
		SouthEast.setLayout(new FlowLayout());
		SouthEast.add(Bajouter);
		SouthEast.add(BModifier);
		SouthEast.add(BSupprimer);
		eastPanel.add(SouthEast, BorderLayout.SOUTH);
		eastPanel.add(list);

		this.add(eastPanel);
		this.add(westPanel, "West");
	}

	// Action Event
	private void openAjouter(ActionEvent e) {
		VueAjoutPersonne Va = new VueAjoutPersonne(this);
	}

	// Ouvrir modifier
	private void openModifier(ActionEvent e) {
		int row = tableview.getSelectedRow();
		if (row != -1) {
			VueModifierPersonne Vm = new VueModifierPersonne(this);
		} else {
			JOptionPane.showMessageDialog(null, "Veuillez selectionner une personne à modifier.");
		}
	}
	
	protected void supprimerPers(ActionEvent e) throws IOException {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Etes vous sur de vouloir supprimer cette personne ?","Warning",dialogButton);
	 	if(dialogResult == JOptionPane.YES_OPTION){

			CSVModele modeleP = (CSVModele) tableview.getModel();
			int row = tableview.getSelectedRow();
			if (row != -1)
				modeleP.deleteRow(row);
	
			String Directory = System.getProperty("user.dir");
			String pathMP = Directory + "\\src\\Bd\\liste_personnel.csv";
			File fileMP = new File(pathMP);
	
			JTable nouveauT = new JTable();
			nouveauT.setModel(modeleP);
			FileWriter writerF = new FileWriter(fileMP.getAbsoluteFile());
			BufferedWriter writerB = new BufferedWriter(writerF);
			writerB.write("ID;Prénom;Nom;Date\n");
			// Boucle ligne
			for (int i = 0; i < nouveauT.getRowCount(); i++) {
				// Boucle colonne
				for (int j = 0; j < nouveauT.getColumnCount(); j++) {
					if (!(nouveauT.getModel().getValueAt(i, j).equals(""))) {
						writerB.write(nouveauT.getModel().getValueAt(i, j) + ";");
					}
				}
				writerB.write("\r\n");
			}
			writerB.close();// On ferme le BufferedWriter
			writerF.close();// On ferme le FileWriter
     	}
	}

}
