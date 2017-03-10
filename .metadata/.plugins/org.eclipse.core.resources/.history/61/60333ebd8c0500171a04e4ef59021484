
package Vue;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.portable.InputStream;

import Modele.CSV;
import Modele.Personne;
import TestCSV.AppTest;

public class VueMissions extends JPanel implements Serializable {
	final int  MAX=100;
	
	public VueMissions(){
	
		this.setLayout(new BorderLayout());
		
		String[] columnNames = {"Code","Date début","durée(mois)"};
		
		//Ajouter les donnÃ©es CSV	
		String Directory = System.getProperty("user.dir");
		Directory+="\\src\\Bd\\liste_mission.csv";
     	File file = new File(Directory);
							
		CSV csv = new CSV();
		
		ArrayList<String[]> donneeCSV = csv.ReadCSVfile(file);
		
		String[][] array = new String[donneeCSV.size()][0];
		donneeCSV.toArray(array);
		
		JTable tableview=new JTable(array,columnNames);
		tableview.setFillsViewportHeight(true);
		JPanel westPanel= new JPanel();
		westPanel.setLayout(new BorderLayout());
		westPanel.add(tableview.getTableHeader(), BorderLayout.PAGE_START);
		westPanel.add(tableview, BorderLayout.WEST);
		JScrollPane scrollPane = new JScrollPane(tableview);
		westPanel.add(scrollPane);
		this.add(westPanel, "West");
		
		JPanel eastPanel = new JPanel();
		JButton boutonAjouter = new JButton("Ajouter");
		boutonAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boutonAjouterActionPerformed(e);
			};
		});
		eastPanel.add(boutonAjouter);
		this.add(eastPanel);
	}
	
	public void boutonAjouterActionPerformed(ActionEvent e) {
		FormAddMission form = new FormAddMission();
	}
	
	
}
