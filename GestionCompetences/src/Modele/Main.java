package Modele;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		MissionPlanifiee m = new MissionPlanifiee();
		Personne p = new Personne("Jean","Dupont", 1, new Date("01/01/2017"));
		m.setDateDebut(new Date("02/01/2017"));
		System.out.println(m.getDateDebut());
		
		Competence c = new Competence ("Gestion","g1");
		m.affecterPersonne(p, c.getCode());
		System.out.println(m.getNbPersonneParComp().get("g1").toString());
		
	}

}
