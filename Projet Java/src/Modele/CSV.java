package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
}
