package Modele;

import java.util.ArrayList;
import java.util.Date;

public class Personne {
	private String nom;
	private String prenom;
	private int identifiant;
	private int nbMission;
	private Date dateEntree;
	private ArrayList<Competence> listCompetences=new ArrayList<Competence>();
	
	public Personne(String nom, String prenom, int identifiant, Date dateEntree) {
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.nbMission = 0;
		this.dateEntree = dateEntree;
	}
	
	public void ajouterCompetence(Competence c){
		listCompetences.add(c);
	}
	
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", identifiant="
				+ identifiant + ", dateEntree=" + dateEntree + "]";
	}

	public void augmenterNbMission () {
		this.nbMission++;
	}
	
}
