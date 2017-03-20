package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.TableModel;

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
		// R�cuperer les ids des comp�tences
		try {
			String Directory = System.getProperty("user.dir");
			Directory += "\\src\\Bd\\competences_personnel.csv";
			BufferedReader buff = new BufferedReader(new FileReader(Directory));
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

		// R�cuperer les comp�tences
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
		// R�cuperer les ids des personnes
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


		// R�cuperer les personnes
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
		
		//retourne le dernier id de personnel
		public static String getLasIdLinePersonne( String fileNAme ) {
			
			String Directory = System.getProperty("user.dir");
			Directory+="\\src\\Bd\\"+fileNAme ;
			File file = new File(Directory);
			
		    RandomAccessFile fileHandler = null;
		    try {
		        fileHandler = new RandomAccessFile( file, "r" );
		        long fileLength = fileHandler.length() - 1;
		        StringBuilder sb = new StringBuilder();

		        for(long filePointer = fileLength; filePointer != -1; filePointer--){
		            fileHandler.seek( filePointer );
		            int readByte = fileHandler.readByte();

		            if( readByte == 0xA ) {
		                if( filePointer == fileLength ) {
		                    continue;
		                }
		                break;

		            } else if( readByte == 0xD ) {
		                if( filePointer == fileLength - 1 ) {
		                    continue;
		                }
		                break;
		            }

		            sb.append( ( char ) readByte );
		        }

		        String lastLine = sb.reverse().toString();
		        String[] array = lastLine.split(";");
		        return array[3];
		    } catch( java.io.FileNotFoundException e ) {
		        e.printStackTrace();
		        return null;
		    } catch( java.io.IOException e ) {
		        e.printStackTrace();
		        return null;
		    } finally {
		        if (fileHandler != null )
		            try {
		                fileHandler.close();
		            } catch (IOException e) {
		                /* ignore */
		            }
		    }
		}
		


	// ajoute une ligne dans un csv
	// line doit etre sous la forme : "aa;bb;cc" ceci va etre g�r� par l
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
	// met � jour une case du cvs (croisement entre ligne et colone)
		 public static void exportTable(JTable table, File file) throws IOException {
		        TableModel model = table.getModel();
		        FileWriter out = new FileWriter(file);

		        for(int i=0; i < model.getColumnCount(); i++) {
		        	if(i+1==model.getColumnCount()){
		        		out.write(model.getColumnName(i));
		        	}else{
		            out.write(model.getColumnName(i)+";");}
		        }
		        out.write(""+"\r\n");
		        for(int i=0; i< model.getRowCount(); i++) {
		            for(int j=0; j < model.getColumnCount(); j++) {
		            	if(j+1==model.getColumnCount()){
			        		out.write(model.getValueAt(i,j).toString());
			        	}else{
		                out.write(model.getValueAt(i,j).toString()+";");
			        	}
		            }
		            out.write(""+"\r\n");
		        }
		        out.close();
		        System.out.println("write out to: " + file);
		    }

	// met � jour une case du cvs (croisement entre ligne et colone)

	public static void updateCSV(String fileToUpdate, String replace, int row, int col) throws IOException {

		File inputFile = new File(fileToUpdate);

		// Read existing file
		CSVReader reader = new CSVReader(new FileReader(inputFile), ';');
		ArrayList<String[]> csvBody =  (ArrayList<String[]>) reader.readAll();
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
		Directory += "\\src\\Bd\\liste_personnel.csv";
		 //File file = new File(Directory);

		 updateCSV(Directory,"yyy",2,0);

		// String s1="hello there bla bla2";
		//addRawCsv("liste_mission.csv", "22;29/03/2017;4");

	}
}
