
package Vue;
import Modele.CSV;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

//public class  extends JFrame


public class FormAddPersonne {
		
		    private static JFrame frame;
		
		    private static JTextField textField;
		
		    private static JTextField textField_1;
		
		    private static JTextField textField_2;
		
		 
		
		    /**
		
		     * Launch the application.
		
		     */
				    public static void main(String[] args) {
		
		        EventQueue.invokeLater(new Runnable() {
		
		            public void run() {
		
		                try {
		
		                	FormAddPersonne window = new FormAddPersonne();
		
		                    window.frame.setVisible(true);
		                    
		                    window.frame.setTitle("Ajout d'une personne");
		                    
		                    textField = new JTextField();
		            		textField.setBounds(128, 28, 86, 20);
		            		frame.getContentPane().add(textField);
		            		textField.setColumns(10);
		            		
		            		JLabel lblPrenom = new JLabel("Prénom");
		            		lblPrenom.setBounds(80, 31, 46, 14);
		            		frame.getContentPane().add(lblPrenom);
		            		
		            		JLabel lblName = new JLabel("Nom");
		            		lblName.setBounds(80, 115, 46, 14);
		            		frame.getContentPane().add(lblName);
		            		
		            		textField_2 = new JTextField();
		            		textField_2.setBounds(128, 112, 247, 17);
		            		frame.getContentPane().add(textField_2);
		            		textField_2.setColumns(10);
		            		
		            		JLabel lblDate = new JLabel("Date entrée");
		            		lblDate.setBounds(80, 162, 46, 14);
		            		frame.getContentPane().add(lblDate);
		            		
		            		
		            		textField_1 = new JTextField();
		            		textField_1.setBounds(126, 157, 212, 40);
		            		frame.getContentPane().add(textField_1);
		            		textField_1.setColumns(10);
		            		
		            		JLabel lblExterne = new JLabel("Interne :");
		            		lblExterne.setBounds(65, 228, 46, 14);
		            		frame.getContentPane().add(lblExterne);
		            		
		            		
		            		JRadioButton radioButton = new JRadioButton("Oui");
		            		radioButton.setBounds(337, 224, 109, 23);
		            		frame.getContentPane().add(radioButton);
		            		
		            		JRadioButton radioButton_1 = new JRadioButton("non");
		            		radioButton_1.setBounds(162, 224, 109, 23);
		            		frame.getContentPane().add(radioButton_1);
		            		
		            		ButtonGroup group = new ButtonGroup();
		            		group.add(radioButton);
		            		group.add(radioButton_1);
		            		
		            		JButton btnClear = new JButton("Clear");
		            		btnClear.setBounds(312, 387, 89, 23);
		            		frame.getContentPane().add(btnClear);
		            		
		            		btnClear.addActionListener(new ActionListener() {
		            			public void actionPerformed(ActionEvent e) {
		            				
		            				textField_2.setText(null);
		            				textField.setText(null);
		            				textField_1.setText(null);
		            				radioButton.setSelected(false);
		            				radioButton_1.setSelected(false);
		            			}
		            		
		            		
		            		});		
		            		
		            		JButton btnSubmit = new JButton("submit");
		            		
		            		btnSubmit.setBounds(65, 387, 89, 23);
		            		frame.getContentPane().add(btnSubmit);
		            		
		            		btnSubmit.addActionListener(new ActionListener() {
		            			public void actionPerformed(ActionEvent arg0) {
		            				if(textField.getText().isEmpty()||(textField_2.getText().isEmpty())||(textField_1.getText().isEmpty())||((radioButton_1.isSelected())&&(radioButton.isSelected())))
		            					JOptionPane.showMessageDialog(null, "Veuillez remplir le formulaire svp");
		            				else{	
			            				JOptionPane.showMessageDialog(null, "Personne ajoutée");
			            				//ajouter la nouvelle mission dans le fichier liste_mission
			            				String seperator=";";
			            				String choosen;
			            				int lastId=Integer.parseInt(CSV.getLasIdLinePersonne("liste_personnel.csv"));
			            				lastId++;
			            				String id =""+ lastId;
			            				//vérifier quel radioButton a été choisi
			            				if(radioButton_1.isSelected()){
			            					choosen=radioButton_1.getText();
			            				}else{
			            					choosen=radioButton.getText();
			            				}
			            				
			            				//préparer la line à ajouter dans le fichier
			            				String line = textField.getText()+seperator+textField_2.getText()+seperator+textField_1.getText()+seperator+id+seperator+choosen;
			            				try {
											CSV.addRawCsv("liste_personnel.csv",line);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
			            				
		            			   }
		            			}
		            		});
		            		
		            		
		            		
		                } catch (Exception e) {
		
		                    e.printStackTrace();
				                }
		
		            }
		
		        });
		
		    }
		
		 
		
		    /**
		
		     * Create the application.
		
		     */
		
		    public FormAddPersonne() {
		
		        initialize();
		
		    }
		
		 
		
		    /**
		
		     * Initialize the contents of the frame.
		
		     */
		
		    private void initialize() {
		
		        frame = new JFrame();
		
		        frame.setBounds(100, 100, 730, 489);
		
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		        frame.getContentPane().setLayout(null);
		
		 
		
		       }
		

}
