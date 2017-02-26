package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CSV {

	private final ArrayList<String[]> lignes = new ArrayList<String[]>();
	private String[] ligne;

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
	
	
	public String[] getCompetences(int idEmploye){
		String[] tabIdCompetence=null;
		//Récuperer les ids des compétences
		try {
			String Directory = System.getProperty("user.dir");
			Directory+="\\src\\Bd\\competences_personnel.csv";
			BufferedReader buff = new BufferedReader(new FileReader(Directory));
			while (buff.ready()) {
				String str = buff.readLine();
				//DONNE FALSE ALORS QUE SPLIT ET IDEPLOYE SONT EGAUX
				System.out.println("Idligne="+str.split(";")[0]);
				System.out.println("Employe="+String.valueOf(idEmploye));
				System.out.println(str.split(";")[0]==String.valueOf(idEmploye));
				System.out.println();
				if(str.split(";")[0].equals(String.valueOf(idEmploye))){
					for(int i =1 ;i<str.split(";").length;i++){
				ligne[i-1] =str.split(";")[i] ;
				System.out.println("i="+(str.split(";")[i]));
					}
				}
			}
			tabIdCompetence=ligne;
			System.out.println("OK1");
		} catch (Exception e) {
			String errmsg = e.getMessage();
			System.out.println("File not found:" + errmsg);
		}
		
		//Récuperer les compétences
		try {
			String Directory = System.getProperty("user.dir");
			Directory+="\\src\\Bd\\list_competences.csv";
			BufferedReader buff = new BufferedReader(new FileReader(Directory));
			
			while (buff.ready()) {
				int i=0;
				String str = buff.readLine();
				while(i<tabIdCompetence.length)
				if(str.split(";")[0]==String.valueOf(tabIdCompetence[i]))
					for(int x =1 ;x<str.split(";").length;i++)
				ligne[x-1] =str.split(";")[x] ;
				System.out.println(Arrays.toString(ligne));
				System.out.println("OK2");
			}
		} catch (Exception e) {
			String errmsg = e.getMessage();
			System.out.println("File not found:" + errmsg);
		}
		return ligne;
	}
	
	//ajoutre une ligne dans un csv
	// line doit etre sous la forme : "aa;bb;cc" ceci va etre géré par l appelant
	//file : fichier dans lequel on souhaite ajouter une ligne
		public void addRawCsv(File file,String line) throws IOException{
			
			String seperator = ";" ;
			
			String fileNAme= file.getName();
			String Directory = System.getProperty("user.dir");
			Directory+="\\src\\Bd\\"+fileNAme ;
			
			//initialise PrintWriter object
			java.io.FileWriter outfile = null;
			
			try {
				 //create PrintWriter object on new File object
				outfile = new java.io.FileWriter(Directory, true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //true = append
			
			//adapt line to csv format(dans le cas ou on recoit un String "aa bb bb"
			//line=line.replaceAll(" ", ";");
			
			//write the line
			outfile.write(line + "\n");

		    outfile.close();
		}
		
		
		//met à jour une case du cvs (croisement entre ligne et colone)
		
		public static void updateCSV(String fileToUpdate, String replace,
			    int row, int col) throws IOException {

			File inputFile = new File(fileToUpdate);

			// Read existing file 
			CSVReader reader = new CSVReader(new FileReader(inputFile), ';');
			ArrayList<String[]> csvBody = (ArrayList)reader.readAll();
			// get CSV row column  and replace with by using row and column
			csvBody.get(row)[col] = replace;
			reader.close();

			// Write to CSV file which is open
			CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ';');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close();
			}
		
		
		public static void test(String s,String line) throws IOException{
			java.io.FileWriter outfile = null;
			try {
				outfile = new java.io.FileWriter(s, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //true = append
		    outfile.write(line.replaceAll(" ", ";") + "\n");

		    outfile.close();
		
		}
		
		
		public static void main(String[] args) throws IOException{
			String Directory = System.getProperty("user.dir");
			Directory+="\\src\\Bd\\liste_mission.csv";
			test(Directory,"5;20/02/2017;6");
			
			//updateCSV(Directory,"xxx",2,2);
			
			//String s1="hello there bla bla2";
			
		}
}
