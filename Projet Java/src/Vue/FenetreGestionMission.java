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

    //CrÃ©ation de l'item "File"
    JMenu fichier = new JMenu("File");
    JMenuItem nouveau = new JMenuItem("Nouveau");
    fichier.add(nouveau);
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
    
    
    JTabbedPane tabbedPane = new JTabbedPane();
    ImageIcon iconUser = createImageIcon("user.jpg");
    ImageIcon iconComp = createImageIcon("comp.jpg");
    ImageIcon iconMission = createImageIcon("mission.jpg");
    
    JPanel panelUser = new JPanel(new GridLayout(1,1));
    tabbedPane.addTab("Utilisateurs", iconUser, panelUser);
    //tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
     
    JPanel panelCompetence = new JPanel(new GridLayout(1,1));
    tabbedPane.addTab("Comp�tences", iconComp, panelCompetence);
    //tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    
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
//     JButton b = new JButton("Afficher liste compétences");
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
	
	 /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ControleurGestionMission.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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
