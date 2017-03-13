package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Controleur.ControleurGestionMission;

import javax.swing.ImageIcon;

public class FenetreGestionMission extends JFrame implements ActionListener {
	
	private VueGestionMission vueGestionMission;
	private VueCompetences vueCompetences;
	private VueMissions vueMissions;
	public FenetreGestionMission(){
    this.setTitle("Gestionnaire des Missions");
    this.setLayout(new BorderLayout());

    //Création de l'item "File"
//    JMenu fichier = new JMenu("File");
//    JMenuItem nouveau = new JMenuItem("Nouveau");
//    fichier.add(nouveau);
//    JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
//    fichier.add(sauvegarder);
//    JMenuItem quitter = new JMenuItem("Quitter");
//    fichier.add(quitter);

    //Création de l'item "Option"
   // JMenu option = new JMenu("Option");


    //Création de la barre de menu
//    JMenuBar jMenuBar = new JMenuBar();
//    jMenuBar.add(fichier);
//    jMenuBar.add(option);
    
    //Ajout de la barre de menu et de la vue à  la fenetre
   // this.add(jMenuBar,BorderLayout.NORTH);
    
    
    JTabbedPane tabbedPane = new JTabbedPane();
    ImageIcon iconUser = new ImageIcon("././Img/user.jpg");
    ImageIcon iconComp = new ImageIcon("././Img/comp.jpg");
    ImageIcon iconMission = new ImageIcon("././Img/mission.jpg");
    
    JPanel panelUser = new JPanel(new GridLayout(1,1));
    tabbedPane.addTab("Personnels", iconUser, panelUser);
     
    JPanel panelCompetence = new JPanel(new GridLayout(1,1));
    tabbedPane.addTab("Compétences", iconComp, panelCompetence);
    
    JPanel panelMission = new JPanel(new GridLayout(1,1));
    tabbedPane.addTab("Missions", iconMission, panelMission);
    
    this.vueGestionMission=new VueGestionMission();
    this.vueCompetences = new VueCompetences();
    this.vueMissions= new VueMissions();
    panelUser.add(this.vueGestionMission,BorderLayout.CENTER);
    panelCompetence.add(this.vueCompetences,BorderLayout.CENTER);
    panelMission.add(this.vueMissions,BorderLayout.CENTER);
    this.add(tabbedPane);
    
    //Ajout de la vue Competences
//     JButton b = new JButton("Afficher liste compÃ©tences");
//     b.addActionListener((ActionListener) this);
     
//    this.vueCompetences=new VueCompetences();
//    this.add(this.vueCompetences, BorderLayout.LINE_END);

    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
}
	
	 protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(1, 1));
        return panel;
	 }

	public void actionPerformed(ActionEvent e) {
	        JFrame frame2 = new JFrame("Accueil");
	        frame2.setVisible(true);
	        frame2.setSize(600, 1000);
	        JLabel label = new JLabel("test");
	        JPanel panel = new JPanel();
	        frame2.add(panel);
	        panel.add(label);
	    }
	
	
	
	public static void main(String[] args) {
	    FenetreGestionMission fenetreGestionMission=new  FenetreGestionMission();
	    fenetreGestionMission.setSize(800,450);
	    fenetreGestionMission.setResizable(false);
	}
}
