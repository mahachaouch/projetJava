package Modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		MissionPlanifiee m = new MissionPlanifiee();
		ArrayList<Personne> allPersons = new ArrayList<Personne>();

		
		Personne p = new PersonneExterne("Jean","Dupont", 1);
		Personne p1 = new Personne("Julia","Dupont", 1);
		Personne p2 = new PersonneExterne("Julien","Marcel", 1);
		
		System.out.println(p2.getClass().getName());
		
		
		allPersons.add(p);
		allPersons.add(p1);
		allPersons.add(p2);
		
		Competence c = new Competence("Marketing","M.1.","");
		Competence c1 = new Competence("Management","M.2.","");
		Competence c3 = new Competence("Analyse","A.3.","");
		Competence cpt = new Competence ("Gestion","g1","");
		
		
		p.ajouterCompetence(c1);
		p.ajouterCompetence(c);
		p1.ajouterCompetence(c3);
		p1.ajouterCompetence(c1);
		p2.ajouterCompetence(c3);
		
		
		m.ajouterNbPers(4, c1);
		m.ajouterNbPers(2, c);
		m.setDateDebut(new Date("02/01/2017"));
		//System.out.println(m.getDateDebut());
		
		
		
		m.afficherListNbPersParCompt();
		m.modifierNbPersonne(5, c1);
		m.afficherListNbPersParCompt();
		
		
		
		 //System.out.println(m.getListPersonneParCometence().get(c1.getCode()));
		//System.out.println(p1.getNbMission());
		//System.out.println(p1.getListCompetences().toString());
		
		//collecter les personnes eligibles pour une mission (par compétence demandée)
		m.setEligiblePersPourMission(allPersons);
		System.out.println(m.getListPersonneParCometence().toString());
		
	}

}
