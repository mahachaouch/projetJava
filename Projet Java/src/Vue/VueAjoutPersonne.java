package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Modele.CSV;
import Modele.CSVModele;

public class VueAjoutPersonne extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VueGestionMission vgm;
	JLabel id=new JLabel("ID Personne : ");
	JTextField tId=new JTextField();
	JLabel nom=new JLabel("Nom : ");
	JTextField tNom=new JTextField();
	JLabel prenom=new JLabel ("Prenom : ");
	JTextField tPrenom=new JTextField();
	ButtonGroup bg=new ButtonGroup();
	JRadioButton interne=new JRadioButton("Personne interne");
	JRadioButton externe=new JRadioButton("Personne externe");
	JLabel date=new JLabel("Date d'entrée : ");
	JTextField tDate=new JTextField();
	JButton ok=new JButton("Valider");
	JButton annuler=new JButton("Annuler");
	
 public VueAjoutPersonne(VueGestionMission vgm){
	 this.vgm=vgm;
	 JPanel main =new JPanel();
	 main.setLayout(new GridLayout(6,2,5,5));
	 this.setSize(400, 200);
	 this.setLocationRelativeTo(null);
	 this.setVisible(true);
	 
	 main.add(id);
	 tId.setPreferredSize(new Dimension(60,20) );
	 String Directory = System.getProperty("user.dir");
		Directory+="\\src\\Bd\\liste_personnel.csv";
		File fichier=new File(Directory);
		CSV csv = new CSV();
		ArrayList<String[]> donneeCSV = csv.ReadCSVfile(fichier);
	 tId.setText((Integer.parseInt(donneeCSV.get(donneeCSV.size()-1)[0])+1)+"");
	 tId.setEditable(false);
	 main.add(tId);
	 
	 main.add(prenom);
	 tPrenom.setPreferredSize(new Dimension(60,20));
	 main.add(tPrenom);
	 
	 main.add(nom);
	 tNom.setPreferredSize(new Dimension(60,20));
	 main.add(tNom);
	 
	 interne.addItemListener(new ItemListener(){
		 @Override
		 public void itemStateChanged(ItemEvent e) {
		     if (e.getStateChange() == ItemEvent.SELECTED) {
		        date.setVisible(true);
		        tDate.setVisible(true);
		     }
		     else if (e.getStateChange() == ItemEvent.DESELECTED) {
		        date.setVisible(false);
		        tDate.setVisible(false);
		     }
		 }
	 });
	 bg.add(interne);
	 bg.add(externe);
	 externe.setSelected(true);
	 main.add(interne);
	 main.add(externe);
	 
	 date.setVisible(false);
     tDate.setVisible(false);
	 main.add(date);
	 tDate.setPreferredSize(new Dimension(60,20));
	 main.add(tDate);
	 ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tId.getText().isEmpty()||tNom.getText().isEmpty() || (tPrenom.getText().isEmpty()))
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs s'il vous plaît");
				else {
					if(interne.isSelected()&& tDate.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Data Missing");}else{
					JOptionPane.showMessageDialog(null, "Personne ajoutée");
					// ajouter la nouvelle mission dans le fichier liste_mission
					String separator = ";";
					String line = tId.getText()+ separator + tNom.getText() + separator
							+ tPrenom.getText()+separator+tDate.getText();
					if(interne.isSelected()){
					line +=separator+"oui";
					}else{line +=separator+"non";}
					try {
						CSV.addRawCsv("liste_personnel.csv", line);
						String Directory = System.getProperty("user.dir");
						Directory+="\\src\\Bd\\liste_personnel.csv";
						File fichier=new File(Directory);
						CSV csv = new CSV();
						CSVModele modele = new CSVModele();
						ArrayList<String[]> donneeCSV = csv.ReadCSVfile(fichier);
						modele.ajouterDonnee(donneeCSV);
						vgm.tableview.setModel(modele);
						vgm.setEnabled(true);
						dispose();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
			}
		});
	 annuler.addActionListener(new ActionListener() {
		 @Override
			public void actionPerformed(ActionEvent arg0) {
			 dispose();
			}
	 });
	 main.add(ok);
	 main.add(annuler);
	 
	 this.add(main);
	 
}

}