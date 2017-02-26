package TestCSV;

import java.awt.List;
import au.com.bytecode.opencsv.*;
import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	//ajoutre une ligne dans un csv
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
		
		//adapt line to csv format
		line=line.replaceAll(" ", ";");
		
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
	
	//format adaptator: transforms : aa vv nn to aa;vv;nn
	
	
	public static void main(String[] args) throws IOException{
		String Directory = System.getProperty("user.dir");
		Directory+="\\src\\Bd\\liste_competences.csv";
		test(Directory,"A4 JAVA2 SWING2 29");
		
		//updateCSV(Directory,"xxx",2,2);
		
		//String s1="hello there bla bla2";
		//System.out.println(s1.replaceAll(" ", ";"));
		
		
		
	}
	
	
	
	
	
}
