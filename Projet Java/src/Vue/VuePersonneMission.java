package Vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Modele.CSV;
import Modele.CSVModele;

public class VuePersonneMission extends JFrame {
	public JTable tableview=new JTable();
	private int numMission;
	
	public VuePersonneMission(int numMission) {
		this.numMission = numMission;
		ArrayList<String> personnes = new ArrayList<String>();
		ArrayList<String[]> personnesAssigne = new ArrayList<String []>();
		
		//Ajouter les donn�es CSV	
		String Directory = System.getProperty("user.dir");
		String pathMP = Directory + "\\src\\Bd\\missions_personnel.csv";
     	File fileMP = new File(pathMP);
							
		CSV csvMP = new CSV();
		
		ArrayList<String[]> donneeCSVMP = csvMP.ReadCSVfile(fileMP);
		for(String[] mission : donneeCSVMP) {
			if (mission[0].equals(Integer.toString(this.numMission))) {
				for(int j=1; j<mission.length;j++) {
					personnes.add(mission[j]);
				}
			}
		}
		
		//System.out.println(personnes);
		
		String pathP = Directory + "\\src\\Bd\\liste_personnel.csv";
		File fileP = new File(pathP);
		CSV csvP = new CSV();
		
		ArrayList<String[]> donneeCSVP = csvP.ReadCSVfile(fileP);
		for(String[] all : donneeCSVP) {
			for (String p : personnes) {
				if (all[0].equals(p)) {
					personnesAssigne.add(all);
				}
			}
		}
		
		if (personnesAssigne.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Il n'y a aucune personne associ�e � la mission n�" + this.numMission);
		}
		else {
			personnesAssigne.add(0,donneeCSVP.get(0));
	
			CSVModele modele = new CSVModele();
			modele.ajouterDonnee(personnesAssigne);
			tableview.setModel(modele);
			JPanel panelWest= new JPanel();
			panelWest.setLayout(new BorderLayout());
			this.setTitle("Personnes intervenants dans la mission n�" + this.numMission);
			panelWest.add(tableview.getTableHeader(), BorderLayout.PAGE_START);
			panelWest.add(tableview, BorderLayout.WEST);
			JScrollPane scrollPane = new JScrollPane(tableview);
			panelWest.add(scrollPane);
			this.add(panelWest, "West");
			
			JPanel panelEast = new JPanel();
			JButton boutonAjouter = new JButton("Ajouter personne");
			boutonAjouter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						boutonAjouterActionPerformed(e);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				};
			});
			JButton boutonSupprimer = new JButton("Supprimer personne");
			boutonSupprimer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						boutonSupprimerActionPerformed(e);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				};
			});
			panelEast.add(boutonSupprimer);
			panelEast.add(boutonAjouter);
			this.add(panelEast,"East");
			this.pack();
			this.setVisible(true);
		}
	}
	
	public void boutonAjouterActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "A faire");
	}
	
	public void boutonSupprimerActionPerformed(ActionEvent e) throws IOException {
		int row = tableview.getSelectedRow();
		System.out.println(row);
		CSVModele modele = (CSVModele) tableview.getModel();
		
		String Directory = System.getProperty("user.dir");
		String pathMP = Directory + "\\src\\Bd\\missions_personnel.csv";
     	File fileMP = new File(pathMP);
		
		CSV csvMP = new CSV();
		
		ArrayList<String[]> donneeCSVMP = csvMP.ReadCSVfile(fileMP);
		for(String [] lines : donneeCSVMP) {
			if (lines[0].equals(Integer.toString(this.numMission))) {
				for(int i=1;i<lines.length;i++) {
					if(lines[i].equals(tableview.getValueAt(row, 0))) {
						lines[i] = "";
					}
					System.out.println(lines[i]);
				}
			}
		}
		
		CSVModele nouveau = new CSVModele();
		nouveau.ajouterDonnee(donneeCSVMP);
		JTable nouveauT = new JTable();
		nouveauT.setModel(nouveau);
		FileWriter writerF = new FileWriter(fileMP.getAbsoluteFile());
		BufferedWriter writerB = new BufferedWriter(writerF);
		writerB.write("Code;Liste personnel\n");
		// Boucle ligne
		for (int i = 0; i < nouveauT.getRowCount(); i++) {
			// Boucle colonne
			for (int j = 0; j < nouveau.getColumnCount(); j++) {
				if(!(nouveauT.getModel().getValueAt(i, j).equals(""))) {
					writerB.write(nouveauT.getModel().getValueAt(i, j) + ";");
				}
			}
			writerB.write("\r\n");
		}
		writerB.close();// On ferme le BufferedWriter
		writerF.close();// On ferme le FileWriter
		
		if (row != -1) modele.deleteRow(row);
	}
}
