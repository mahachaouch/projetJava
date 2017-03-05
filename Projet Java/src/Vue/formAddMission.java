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


public class formAddMission {
		
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
		
		                	formAddMission window = new formAddMission();
		
		                    window.frame.setVisible(true);
		                    
		                    window.frame.setTitle("Ajout d'une mission");
		                    
		                    textField = new JTextField();
		            		textField.setBounds(128, 28, 86, 20);
		            		frame.getContentPane().add(textField);
		            		textField.setColumns(10);
		            		
		            		JLabel lblName = new JLabel("Code Mission");
		            		lblName.setBounds(80, 31, 46, 14);
		            		frame.getContentPane().add(lblName);
		            		
		            		JLabel lblEmailId = new JLabel("Date d�but");
		            		lblEmailId.setBounds(80, 115, 46, 14);
		            		frame.getContentPane().add(lblEmailId);
		            		
		            		textField_2 = new JTextField();
		            		textField_2.setBounds(128, 112, 247, 17);
		            		frame.getContentPane().add(textField_2);
		            		textField_2.setColumns(10);
		            		
		            		JLabel lblAddress = new JLabel("Dur�e");
		            		lblAddress.setBounds(80, 162, 46, 14);
		            		frame.getContentPane().add(lblAddress);
		            				
		            		JTextArea textArea_1 = new JTextArea();
		            		textArea_1.setBounds(126, 157, 212, 40);
		            		frame.getContentPane().add(textArea_1);
		            		textArea_1.setColumns(10);
		            		
		            		
		            		
		            		JButton btnClear = new JButton("Clear");
		            		
		            		btnClear.setBounds(312, 387, 89, 23);
		            		frame.getContentPane().add(btnClear);
		            		
		            		btnClear.addActionListener(new ActionListener() {
		            			public void actionPerformed(ActionEvent e) {
		            				
		            				textField_2.setText(null);
		            				textField.setText(null);
		            				textArea_1.setText(null);
		            			}
		            		
		            		
		            		});		
		            		
		            		JButton btnSubmit = new JButton("submit");
		            		
		            		btnSubmit.setBackground(Color.BLUE);
		            		btnSubmit.setForeground(Color.MAGENTA);
		            		btnSubmit.setBounds(65, 387, 89, 23);
		            		frame.getContentPane().add(btnSubmit);
		            		
		            		btnSubmit.addActionListener(new ActionListener() {
		            			public void actionPerformed(ActionEvent arg0) {
		            				if(textField.getText().isEmpty()||(textField_2.getText().isEmpty())||(textArea_1.getText().isEmpty()))
		            					JOptionPane.showMessageDialog(null, "Data Missing");
		            				else{	
			            				JOptionPane.showMessageDialog(null, "Data Submitted");
			            				//ajouter la nouvelle mission dans le fichier liste_mission
			            				String seperator=";";
			            				String line = textField.getText()+seperator+textField_2.getText()+seperator+textArea_1.getText();
			            				try {
											CSV.addRawCsv("liste_mission.csv",line);
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
		
		    public formAddMission() {
		
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
