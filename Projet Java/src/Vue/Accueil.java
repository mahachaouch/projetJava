package Vue;



import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil implements ActionListener {

	 private enum Actions {
		    Personnes,
		    Missions,
		    Competences
		  }
	
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Accueil().createGui();
            }
        });

    }

    public void createGui() {
        JFrame frame = new JFrame("Java Accueil");
        frame.setSize(700, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        frame.getContentPane().add(panel, BorderLayout.WEST);
        GridBagConstraints c = new GridBagConstraints();
        
        

        JButton button1 = new JButton("Personnes");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(40, 40, 40, 40);
        panel.add(button1, c);
        button1.addActionListener(this);
        button1.setActionCommand(Actions.Personnes.name());
       

        JButton button2 = new JButton("Missions");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(button2, c);
        button2.addActionListener(this);
        button2.setActionCommand(Actions.Missions.name());

        JButton button3 = new JButton("Compétences");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(button3, c);
        button3.addActionListener(this);
        button3.setActionCommand(Actions.Competences.name());

    }

    public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand() == Actions.Personnes.name()) {
    		// this.add(new VueGestionMission(),BorderLayout.CENTER);
    		//Afficher la vue Personnes :TODO
    	}
    	if (e.getActionCommand() == Actions.Missions.name()) {
    		//Afficher vue Mission : TODO
    		
    		//Exemple pour montrer que ça marche :p
    		JOptionPane.showMessageDialog(null, "Missions");
    	}
    	if (e.getActionCommand() == Actions.Personnes.name()) {
    		//Afficher vue Competences:TODO
    	}
    	
        JFrame frame2 = new JFrame("Dashboard");
        frame2.setVisible(true);
        frame2.setSize(600, 600);
        JLabel label = new JLabel("Tableau de bord");
        JPanel panel = new JPanel();
        frame2.add(panel);
        panel.add(label);
    }
}
