package Vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FenetreGestionMission extends JFrame {
	
	   private VueGestionMission vueGestionMission;
	public FenetreGestionMission(){
    this.setTitle("Gestionnaire des Missions");
    this.setLayout(new BorderLayout());

    //Création de l'item "Jeu"
    JMenu fichier = new JMenu("File");
    JMenuItem rejouer = new JMenuItem("Nouveau");
    fichier.add(rejouer);
    JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
    fichier.add(sauvegarder);
    JMenuItem quitter = new JMenuItem("Quitter");
    fichier.add(quitter);




    //Création de l'item "Option"
    JMenu option = new JMenu("Option");


    //Création de la bar de menu
    JMenuBar jMenuBar = new JMenuBar();
    jMenuBar.add(fichier);
    jMenuBar.add(option);
    //TODO AJOUTER LISTENER

    //Ajout de la bar de menu et de la vue à la fenetre
    this.add(jMenuBar,BorderLayout.NORTH);
    this.vueGestionMission=new VueGestionMission();
    this.add(this.vueGestionMission,BorderLayout.CENTER);
    

    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
}
public static void main(String[] args) {
    FenetreGestionMission fenetreGestionMission=new  FenetreGestionMission();
    fenetreGestionMission.setSize(600,450);
    fenetreGestionMission.setResizable(false);
}
}
