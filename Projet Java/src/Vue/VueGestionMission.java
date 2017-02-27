package Vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
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
	final int  MAX=100;
	 public JList list=new JList();
	public JTable tableview=new JTable();
	public VueGestionMission(){
		this.setLayout(new BorderLayout());

		
		JScrollPane scrollPane = new JScrollPane(tableview);
		tableview.setFillsViewportHeight(true);
		
		//Ajouter les données CSV
		String Directory = System.getProperty("user.dir");
		Directory+="\\src\\Bd\\liste_personnel.csv";
		File fichier=new File(Directory);
		CSV csv = new CSV();
		CSVModele modele = new CSVModele();
		ArrayList<String[]> donneeCSV = csv.ReadCSVfile(fichier);
		modele.ajouterDonnee(donneeCSV);
		tableview.setModel(modele);
		tableview.setAutoCreateRowSorter(true);
		ListSelectionModel listSelectionModel = tableview.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new ControleurGestionMission(this));
	
		JPanel Westpannel= new JPanel();
		Westpannel.setLayout(new BorderLayout());
		Westpannel.add(tableview.getTableHeader(), BorderLayout.PAGE_START);
		Westpannel.add(tableview, BorderLayout.WEST);
		Westpannel.add(scrollPane);
		
		
		JPanel Eastpannel=new JPanel();
		Eastpannel.setLayout(new BorderLayout());
		JLabel comp=new JLabel("Compétences :");
		JScrollPane Scroll=new JScrollPane(list);
		Eastpannel.add(Scroll,BorderLayout.NORTH);
		Eastpannel.add(comp,BorderLayout.NORTH);
		Eastpannel.add(list);
		
		
		this.add(Eastpannel);
		this.add(Westpannel, "West");
	}
}

