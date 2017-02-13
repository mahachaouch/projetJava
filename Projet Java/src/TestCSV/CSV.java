package TestCSV;

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
}
