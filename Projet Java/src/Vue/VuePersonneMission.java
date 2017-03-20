package Vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Modele.CSV;
import Modele.CSVModele;
import Modele.Competence;

public class VuePersonneMission extends JFrame {
	public JTable tableview=new JTable();
	public JTable tablePersonne=new JTable();
	private int numMission;
	
	public VuePersonneMission(int numMission) throws  IOException {
		this.numMission = numMission;
		ArrayList<String> personnes = new ArrayList<String>();
		ArrayList<String[]> personnesAssigne = new ArrayList<String []>();
		
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
		
		
		personnesAssigne.add(0,donneeCSVP.get(0));

		CSVModele modele = new CSVModele();
		modele.ajouterDonnee(personnesAssigne);
		tableview.setModel(modele);
		JPanel panelWest= new JPanel();
		panelWest.setLayout(new BorderLayout());
		this.setTitle("Personnes intervenants dans la mission n°" + this.numMission);
		panelWest.add(tableview.getTableHeader(), BorderLayout.PAGE_START);
		panelWest.add(tableview, BorderLayout.WEST);
		JScrollPane scrollPane = new JScrollPane(tableview);
		panelWest.add(scrollPane);
		this.add(panelWest, "West");
		
		JPanel panelEast = new JPanel();
		panelEast.setLayout(new BorderLayout());
		JButton boutonAjouter = new JButton("Ajouter personne");
		boutonAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boutonAjouterActionPerformed(e);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
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
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			};
		});
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		panel1.add(boutonSupprimer);
		panel2.add(boutonAjouter);
		panelEast.add(panel1,"North");
		panelEast.add(panel2,"South");
		panelEast.add(panel3,"Center");
		CSVModele personnels = new CSVModele();
		Iterator<String []> it = donneeCSVP.iterator();
//		
//		String pathPs = Directory + "\\src\\Bd\\missions_competences.csv";
//				File filePs= new File(pathPs);
//				CSV csvPs = new CSV();
//				ArrayList<String[]> donneeMission = csvP.ReadCSVfile(fileP);
				
				
				
		while (it.hasNext()) {
			String assigne2="";
			String id = it.next()[0];
			for(String[] assigne : personnesAssigne) {
				if (id.equals(assigne[0])) {
					assigne2=assigne[0];
					it.remove();
				}
			}
		}
//		while(it.hasNext()){
//			it.next();
//			boolean ACompetence=false;
//				int x=0;
//			//Pour chaque personne
//			for(String[] personne : donneeCSVP){
//				try{
//				if(x>0){
//				CSV csvlol=new CSV();
//				System.out.println(personne[0]);
//				Competence[] c=csvlol.getCompetences(Integer.parseInt(personne[0]));
//				//Pour chaque competence de la personne
//				for(Competence comp:c){
//				if(comp!=null){
//					//Pour chaque mission
//					for(String[] compMission :donneeMission){
//						//Trouver la bonne mission
//						if(compMission[0].equals(numMission+"")){
//							//Regarder sur toutes les competences de la mission
//							for(int i=1;i<compMission.length;i++){
//								//Compare si la personne à une bonne competence
//								if(comp.getCode()==compMission[i]){
//									ACompetence=ACompetence||true;
//									System.out.println(personne[0]+"\n"+personne[1]);
//								}
//							}	
//						}
//						}
//					}
//				
//				}
//		
//			if(ACompetence==false){
//				System.out.println(personne[0]+"\n"+personne[1]);
//				it.remove();
//			}	
//				}
//			x++;
//			}catch(NumberFormatException e){}
//			}
//		}
		personnels.ajouterDonnee(donneeCSVP);
		this.tablePersonne.setModel(personnels);
		panel4.add(tablePersonne);
		JScrollPane scrollPane2 = new JScrollPane(tablePersonne);
		panel4.add(scrollPane2);
		panelEast.add(panel4,"Center");
		this.add(panelEast,"East");
		this.pack();
		this.setVisible(true);		
		
	}
	
	public void boutonAjouterActionPerformed(ActionEvent e) throws IOException {
		int row = tablePersonne.getSelectedRow();
		if (row != -1) {
			CSVModele modeleP = (CSVModele) tablePersonne.getModel();
			CSVModele modeleA = (CSVModele) tableview.getModel();
			String [] ligne = {""+modeleP.getValueAt(row, 0),""+modeleP.getValueAt(row, 1),""+modeleP.getValueAt(row, 2),""+modeleP.getValueAt(row, 3),""+modeleP.getValueAt(row, 4)};
			modeleA.getDonnee().add(ligne);
			modeleA.fireTableDataChanged();
			this.tableview.setModel(modeleA);
			
			String Directory = System.getProperty("user.dir");
			String pathMP = Directory + "\\src\\Bd\\missions_personnel.csv";
	     	File fileMP = new File(pathMP);
			
			CSV csvMP = new CSV();
			
			ArrayList<String[]> donneeCSVMP = csvMP.ReadCSVfile(fileMP);

			boolean exist = false;
			for(String [] lines : donneeCSVMP) {
				if (lines[0].equals(Integer.toString(this.numMission))) {
					exist = true;
					lines[lines.length-1] += ";"+modeleP.getValueAt(row, 0);
				}
			}
			if (!exist) {
				String [] l = {""+numMission+";",";"+modeleP.getValueAt(row, 0)};
				donneeCSVMP.add(l);

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
			
			if (row != -1) modeleP.deleteRow(row);
		}
		else {
			JOptionPane.showMessageDialog(null, "Veuillez selectionner une personne à ajouter.");
		}
		
	}
	
	public void boutonSupprimerActionPerformed(ActionEvent e) throws IOException {
		int row = tableview.getSelectedRow();
		if (row != -1) {
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
		
			modele.deleteRow(row);
		}
		else {
			JOptionPane.showMessageDialog(null, "Veuillez selectionner une personne à supprimer.");
		}
	}
}
