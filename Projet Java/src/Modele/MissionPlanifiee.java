package Modele;

import java.util.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

public class MissionPlanifiee extends MissionEnPreparation{

	private Hashtable<Competence,ArrayList<Personne>> ListPersonneParCometence;
	 public MissionPlanifiee(){
		 super();
		 this.ListPersonneParCometence = new Hashtable<Competence,ArrayList<Personne>>();
	 }
	 
	 //ajoute une personne à la liste des personnes ayant la compétence donnée
	 public void affecterPersonne(Personne p,Competence Compet){
		 
		 if(this.ListPersonneParCometence.containsKey(Compet)) {
			 this.ListPersonneParCometence.get(Compet).add(p);
			 p.augmenterNbMission();
		 } else {
			 ArrayList<Personne> l = new ArrayList<Personne>();
			 l.add(p);
			 this.ListPersonneParCometence.put(Compet,l);
		 }
	 }
	 
	 public Hashtable<Competence, ArrayList<Personne>> getListPersonneParCometence() {
		return ListPersonneParCometence;
	}

	public void setListPersonneParCometence(Hashtable<Competence, ArrayList<Personne>> listPersonneParCometence) {
		ListPersonneParCometence = listPersonneParCometence;
	}

	public void afficherListPersParCompt(){
		 
		 String hashstring= "";
		    for (Entry<Competence, ArrayList<Personne>> entry : this.ListPersonneParCometence.entrySet()) {
		        hashstring += entry.getKey().getCode() + "=" + entry.getValue() + "|";
		    }
			System.out.println(hashstring);
	 }
}
