import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AppTest extends JPanel {
	private final JTable table;
	private Modele modele;
	private JPanel boutons;
	static File fichier;

	public AppTest() {
		// Interface avec JTable (Centre)
		super(new BorderLayout());
		this.table = new JTable(new Modele());
		this.table.setPreferredScrollableViewportSize(new Dimension(700, 700));
		this.table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Boutons (Sud)
		boutons = new JPanel();
		boutons.setLayout(new FlowLayout(5, 50, 5));
		JButton open = new JButton("Ouvrir");
		boutons.add(open);
		JButton add = new JButton("Ajouter ligne");
		boutons.add(add);
		JButton remove = new JButton("Supprimer ligne");
		boutons.add(remove);
		JButton save = new JButton("Enregistrer");
		boutons.add(save);
		JButton saveAs = new JButton("Enregistrer sous");
		boutons.add(saveAs);
		this.add(boutons, BorderLayout.SOUTH);

		// Bouton affichage
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser choixFichier = new JFileChooser();
				FileFilter filtre = new FileNameExtensionFilter("Text/CSV file", "txt", "csv");
				choixFichier.addChoosableFileFilter(filtre);
				int ret = choixFichier.showDialog(null, "Choisissez un fichier");
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = choixFichier.getSelectedFile();
					AppTest.fichier = file;
					CSV csv = new CSV();
					modele = new Modele();
					ArrayList<String[]> donneeCSV = csv.ReadCSVfile(file);
					modele.ajouterDonnee(donneeCSV);
					table.setModel(modele);
					System.out.println("Lignes: " + modele.getRowCount());
					System.out.println("Colonnes: " + modele.getColumnCount());
				}
			}
		});

		// Bouton save
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File file = fichier; // Path du fichier
					if (file == null) {
						JOptionPane.showMessageDialog(null, "Aucun fichier sélectionné");
						return;
					}
					FileWriter writerF = new FileWriter(file.getAbsoluteFile());
					BufferedWriter writerB = new BufferedWriter(writerF);

					// Boucle ligne
					for (int i = 0; i < table.getRowCount(); i++) {
						// Boucle colonne
						for (int j = 0; j < table.getColumnCount(); j++) {
							writerB.write(table.getModel().getValueAt(i, j) + ";");
						}
						writerB.write("\r\n");
					}
					writerB.close();// On ferme le BufferedWriter
					writerF.close();// On ferme le FileWriter
					JOptionPane.showMessageDialog(null, "Done !");

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		// Bouton Save As
		saveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser choix = new JFileChooser(".");
					int status = choix.showSaveDialog(AppTest.this);
					if (status == JFileChooser.APPROVE_OPTION) {
						File file = choix.getSelectedFile(); // Path du fichier
						FileWriter writerF = new FileWriter(file.getAbsoluteFile());
						BufferedWriter writerB = new BufferedWriter(writerF);
						// Boucle ligne
						for (int i = 0; i < table.getRowCount(); i++) {
							// Boucle colonne
							for (int j = 0; j < table.getColumnCount(); j++) {
								writerB.write(table.getModel().getValueAt(i, j) + ";");
							}
							writerB.write("\r\n");
						}
						writerB.close();// On ferme le BufferedWriter
						writerF.close();// On ferme le FileWriter
						JOptionPane.showMessageDialog(null, "Fichier enregistré.");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		// Bouton add
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modele modele = (Modele) table.getModel();
				modele.addRow();
			}
		});

		// Bouton remove
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Modele modele = (Modele) table.getModel();
				if (row != -1)
					modele.deleteRow(row);
			}
		});
	}

	private static void init() {
		JFrame frame = new JFrame("Donnee");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AppTest app = new AppTest();
		frame.setContentPane(app);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		init();
	}
}