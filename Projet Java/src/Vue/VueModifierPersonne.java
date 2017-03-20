package Vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Modele.CSV;
import Modele.CSVModele;

public class VueModifierPersonne extends JFrame {

	VueGestionMission Vm;
	private static final long serialVersionUID = 1L;
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
	
	public VueModifierPersonne(VueGestionMission vueGestionMission) {
		Vm=vueGestionMission;
		 JPanel main =new JPanel();
		 main.setLayout(new GridLayout(6,2,5,5));
		 this.setSize(400, 200);
		 this.setLocationRelativeTo(null);
		 this.setVisible(true);
		 
		 main.add(id);
		 tId.setPreferredSize(new Dimension(60,20) );
		 Vm.tableview.getValueAt(Vm.tableview.getSelectedRow(), 0);
		 tId.setText(Vm.tableview.getValueAt(Vm.tableview.getSelectedRow(), 0).toString());
		 tId.setEditable(false);
		 main.add(tId);
		 
		 main.add(prenom);
		 tPrenom.setPreferredSize(new Dimension(60,20));
		 tPrenom.setText(Vm.tableview.getValueAt(Vm.tableview.getSelectedRow(), 1).toString());
		 main.add(tPrenom);
		 
		 main.add(nom);
		 tNom.setPreferredSize(new Dimension(60,20));
		 tNom.setText(Vm.tableview.getValueAt(Vm.tableview.getSelectedRow(), 2).toString());
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
		 if(Vm.tableview.getValueAt(Vm.tableview.getSelectedRow(), 4).toString().equals("oui")){
		 interne.setSelected(true);
		 new Thread(new Runnable() {
		      public void run() {
		        SwingUtilities.invokeLater(new Runnable() {
		          public void run() { 
		        	  date.setVisible(true);
	                 tDate.setVisible(true);
		           
		          }
		        });
		      }
		  }).start();
		
		 }else{externe.setSelected(true);}
		 main.add(interne);
		 main.add(externe);
		 
		 date.setVisible(false);
	     tDate.setVisible(false);
		 main.add(date);
		 tDate.setPreferredSize(new Dimension(60,20));
		 tDate.setText(Vm.tableview.getValueAt(Vm.tableview.getSelectedRow(), 3).toString());
		 main.add(tDate);
		 ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tId.getText().isEmpty()||tNom.getText().isEmpty() || (tPrenom.getText().isEmpty()))
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs s'il vous plaît");
					else {
						if(interne.isSelected()&& tDate.getText().isEmpty()){JOptionPane.showMessageDialog(null, "Data Missing");}else{
						JOptionPane.showMessageDialog(null, "Personne Modifiée");
						String line="";
						if(interne.isSelected()){
							line +="oui";
							}else{line +="non";}
			
						String Directory = System.getProperty("user.dir");
						Directory+="\\src\\Bd\\liste_personnel.csv";
						System.out.println(Vm.tableview.getSelectedRow());
						System.out.println(tNom.getText());	
						File fichier=new File(Directory);
						CSV csv = new CSV();
						CSVModele modele = new CSVModele();
						ArrayList<String[]> donneeCSV = csv.ReadCSVfile(fichier);
						donneeCSV.get(Vm.tableview.getSelectedRow()+1)[1]=tPrenom.getText();
						donneeCSV.get(Vm.tableview.getSelectedRow()+1)[2]=tNom.getText();
						donneeCSV.get(Vm.tableview.getSelectedRow()+1)[3]=tDate.getText();
						donneeCSV.get(Vm.tableview.getSelectedRow()+1)[4]=line;
						modele.ajouterDonnee(donneeCSV);
						Vm.tableview.setModel(modele);
						try {
							CSV.exportTable(Vm.tableview,fichier);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
													
						Vm.setEnabled(true);
						dispose();
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