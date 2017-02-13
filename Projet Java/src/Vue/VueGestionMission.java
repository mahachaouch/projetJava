package Vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.TableView;

import Modele.Personne;

public class VueGestionMission extends JPanel implements Serializable {
	final int  MAX=100;
	public VueGestionMission(){
	this.setLayout(new BorderLayout());
	//Ajouter les données CSV
	String[] columnNames = {"Identifiant","First Name",  "Last Name"};
	Personne p1=new Personne("Dupont","Louis",1,new Date(1996,9,21));
	Personne p2=new Personne("Dupont","Louis",1,new Date(1996,9,21));
	Personne[] personne=new Personne[MAX];
	personne[0]=p1;
	personne[1]=p2;
	String[][] data={
	{p1.getIdentifiant()+"",p1.getPrenom(),p1.getNom()},
	{p2.getIdentifiant()+"",p2.getPrenom(),p2.getNom()}
	};
	JTable tableview=new JTable(data,columnNames);
	JScrollPane scrollPane = new JScrollPane(tableview);
	tableview.setFillsViewportHeight(true);
	  JPanel Westpannel= new JPanel();
	 Westpannel.setLayout(new BorderLayout());
	 Westpannel.add(tableview.getTableHeader(), BorderLayout.PAGE_START);
	 Westpannel.add(tableview, BorderLayout.WEST);
	  Westpannel.add(scrollPane);
	 this.add(Westpannel, "West");
	}
}
