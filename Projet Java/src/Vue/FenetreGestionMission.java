package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FenetreGestionMission extends JFrame implements ActionListener {
	
	private VueGestionMission vueGestionMission;
	private VueCompetences vueCompetences;
	public FenetreGestionMission(){
    this.setTitle("Gestionnaire des Missions");
    this.setLayout(new BorderLayout());

    //CrÃ©ation de l'item "Jeu"
    JMenu fichier = new JMenu("File");
    JMenuItem rejouer = new JMenuItem("Nouveau");
    fichier.add(rejouer);
    JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
    fichier.add(sauvegarder);
    JMenuItem quitter = new JMenuItem("Quitter");
    fichier.add(quitter);




    //CrÃ©ation de l'item "Option"
    JMenu option = new JMenu("Option");


    //CrÃ©ation de la bar de menu
    JMenuBar jMenuBar = new JMenuBar();
    jMenuBar.add(fichier);
    jMenuBar.add(option);
    

    //Ajout de la bar de menu et de la vue Ã  la fenetre
    this.add(jMenuBar,BorderLayout.NORTH);
    this.vueGestionMission=new VueGestionMission();
    this.add(this.vueGestionMission,BorderLayout.CENTER);
    
    //Ajout de la vue Competences
     JButton b = new JButton("Afficher liste compétences");
     b.addActionListener((ActionListener) this);
     
    this.vueCompetences=new VueCompetences();
    this.add(this.vueCompetences, BorderLayout.LINE_END);

    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
}
	
	
	 public void actionPerformed(ActionEvent e) {
	        JFrame frame2 = new JFrame("Accueil");
	        frame2.setVisible(true);
	        frame2.setSize(600, 600);
	        JLabel label = new JLabel("test");
	        JPanel panel = new JPanel();
	        frame2.add(panel);
	        panel.add(label);
	    }
	
	
	
public static void main(String[] args) {
    FenetreGestionMission fenetreGestionMission=new  FenetreGestionMission();
    fenetreGestionMission.setSize(600,450);
    fenetreGestionMission.setResizable(false);
}
}
