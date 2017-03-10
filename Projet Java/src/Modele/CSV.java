package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.synth.SynthSeparatorUI;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CSV {

	private final ArrayList<String[]> lignes = new ArrayList<String[]>();
	private String[] ligne;
	final int MAX = 15;

	public ArrayList<String[]> ReadCSVfile(File file) {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(file));
			while (buff.ready()) {
				String str = buff.readLine();
				ligne = str.split(";");
				lignes.add(ligne);
				System.out.println(Arrays.toString(ligne));
			}
		} catch (Exception e) {
			String errmsg = e.getMessage();
			System.out.println("File not found:" + errmsg);
		}
		return lignes;
	}

	public Competence[] getCompetences(int idEmploye) throws IOException {
		String[] tabIdCompetence = new String[MAX];
		ligne = new String[MAX];
		Competence[] tabComp = new Competence[MAX];
		// Récuperer les ids des compétences
		try {
			String Directory = System.getProperty("user.dir");
			Directory += "\\src\\Bd\\competences_personnel.csv";
			BufferedReader buff = new BufferedReader(new FileReader(Directory));
			buff.readLine();
			while (buff.ready()) {
				String str = buff.readLine();
				// DONNE FALSE ALORS QUE SPLIT ET IDEPLOYE SONT EGAUX
				// System.out.println("Idligne="+str.split(";")[0]);
				// System.out.println("Employe="+String.valueOf(idEmploye));
				// System.out.println(str.split(";")[0].equals(String.valueOf(idEmploye)));
				// System.out.println();
				if (str.split(";")[0].equals(String.valueOf(idEmploye))) {

					for (int i = 1; i < str.split(";").length; i++) {

						ligne[i - 1] = str.split(";")[i];
						// for(String a : ligne)
						// System.out.println(a);
					}
				}
			}
			int i = 0;
			for (String a : ligne) {
				if (a != null) {
					tabIdCompetence[i] = a;
					i++;
				}
			}

		} catch (FileNotFoundException e) {
			String errmsg = e.getMessage();
			System.out.println("File not found:" + errmsg);
		}

		// Récuperer les compétences
		try {
			ligne = new String[MAX];
			String Directory = System.getProperty("user.dir");
			Directory += "\\src\\Bd\\liste_competences.csv";
			BufferedReader buff = new BufferedReader(new FileReader(Directory));
			int i = 0;
			int compteur = 0;
			while (buff.ready()) {
				i = 0;
				String str = buff.readLine();
				while (i < tabIdCompetence.length) {
					if (str.split(";")[0].equals(String.valueOf(tabIdCompetence[i]))) {
						tabComp[compteur] = new Competence(str.split(";")[0], str.split(";")[1], str.split(";")[2]);
						compteur++;

					}
					i++;
				}

			}
		} catch (FileNotFoundException e) {
			String errmsg = e.getMessage();
			System.out.println("File not found:" + errmsg);
		}
		return tabComp;
	}

	public Personne[] getPersonnes(int idMission) throws IOException {
		String[] tabIdPersonne = new String[MAX];
		ligne = new String[MAX];
		Personne[] tabPers = new Personne[MAX];
		// Récuperer les ids des personnes
		try {
			String Directory = System.getProperty("user.dir");
			Directory += "\\src\\Bd\\missions_personnel.csv";
			BufferedReader buff = new BufferedReader(new FileReader(Directory));
			buff.readLine();
			while (buff.ready()) {
				String str = buff.readLine();
				if (str.split(";")[0].equals(String.valueOf(idMission))) {
					for (int i = 1; i < str.split(";").length; i++) {
						ligne[i - 1] = str.split(";")[i];
					}
				}
			}
			int i = 0;
			for (String a : ligne) {
				if (a != null) {
					tabIdPersonne[i] = a;
					i++;
				}
			}

		} catch (FileNotFoundException e) {
			String errmsg = e.getMessage();
			System.out.println("File not found:" + errmsg);
		}

		// Récuperer les personnes
		try {
			ligne = new String[MAX];
			String Directory = System.getProperty("user.dir");
			Directory += "\\src\\Bd\\liste_personnel.csv";
			BufferedReader buff = new BufferedReader(new FileReader(Directory));
			int i = 0;
			int compteur = 0;
			while (buff.ready()) {
				i = 0;
				String str = buff.readLine();
				while (i < tabIdPersonne.length) {
					if (str.split(";")[0].equals(String.valueOf(tabIdPersonne[i]))) {
						tabPers[compteur] = new Personne(str.split(";")[0], str.split(";")[1],Integer.parseInt(str.split(";")[2]));
						compteur++;

					}
					i++;
				}

			}
		} catch (FileNotFoundException e) {
			String errmsg = e.getMessage();
			System.out.println("File not found:" + errmsg);
		}
		return tabPers;
	}

	// ajoute une ligne dans un csv
	// line doit etre sous la forme : "aa;bb;cc" ceci va etre géré par l
	// appelant
	// file : fichier dans lequel on souhaite ajouter une ligne
	public static void addRawCsv(String fileNAme, String line) throws IOException {

		String seperator = ";";

		// String fileNAme= file.getName();
		String Directory = System.getProperty("user.dir");
		Directory += "\\src\\Bd\\" + fileNAme;

		// initialise PrintWriter object
		java.io.FileWriter outfile = null;

		try {
			// create PrintWriter object on new File object
			outfile = new java.io.FileWriter(Directory, true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // true = append

		// write the line
		outfile.write(line + "\n");

		outfile.close();
	}

	// met à jour une case du cvs (croisement entre ligne et colone)

	public static void updateCSV(String fileToUpdate, String replace, int row, int col) throws IOException {

		File inputFile = new File(fileToUpdate);

		// Read existing file
		CSVReader reader = new CSVReader(new FileReader(inputFile), ';');
		ArrayList<String[]> csvBody = (ArrayList) reader.readAll();
		// get CSV row column and replace with by using row and column
		csvBody.get(row)[col] = replace;
		reader.close();

		// Write to CSV file which is open
		CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ';');
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();
	}

	public static void test(String s, String line) throws IOException {
		java.io.FileWriter outfile = null;
		try {
			outfile = new java.io.FileWriter(s, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // true = append
		outfile.write(line.replaceAll(" ", ";") + "\n");

		outfile.close();

	}

	public static void main(String[] args) throws IOException {
		String Directory = System.getProperty("user.dir");
		Directory += "\\src\\Bd\\liste_mission.csv";
		// File file = new File(Directory);

		// updateCSV(Directory,"xxx",2,2);

		// String s1="hello there bla bla2";
		addRawCsv("liste_mission.csv", "22;29/03/2017;4");

	}
}
