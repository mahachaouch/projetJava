package Modele;

import java.util.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

public class MissionPlanifiee extends MissionEnPreparation{

	private Hashtable<Competence,ArrayList<Personne>> ListPersonneParCometence;
	 public MissionPlanifiee(){
		 super();
		 this.ListPersonneParCometence = new Hashtable<Competence,ArrayList<Personne>>();
	 }
	 
	 //ajoute une personne à la liste des personnes ayant la compétence donnée
	 public void affecterPersonne(Personne p,Competence Compet){
		 String classPErmission = "Modele.PersonneExterne";
		
		 if(this.ListPersonneParCometence.containsKey(Compet)) {
			 //verifier si la mission autorise d embaucher des personne externe + v�rifier si la personne est externe ou pas
			 if(this.getAutorisExterne() && !p.getClass().getName().equals(classPErmission)){
				 this.ListPersonneParCometence.get(Compet).add(p);
				 p.augmenterNbMission();
			 }
		 } else {
			 //verifier si la mission autorise d embaucher des personne externe + v�rifier si la personne est externe ou pas
			 if(this.getAutorisExterne() && !p.getClass().getName().equals(classPErmission)){
				 ArrayList<Personne> l = new ArrayList<Personne>();
				 l.add(p);
				 this.ListPersonneParCometence.put(Compet,l);
			 }
		 }
	 }
	 
	 //remplie la liste de personne ayant chaque compétence demandée pour la réalisation de la mission
	 public void setEligiblePersPourMission(ArrayList<Personne> listP){
		 
		 for(Map.Entry<Competence,Integer> entry : this.getNbPersonneParComp().entrySet()){
			 Competence c =entry.getKey();
				 for(int i=0;i<listP.size();i++){
					 //si la personne posséde la compétence demandée(actuelle) on l ajouteà la liste personne qui lui correspond
					 Personne p = listP.get(i);
					
					 if(p.getListCompetences().contains(c)){
						 this.affecterPersonne(listP.get(i), entry.getKey());
					 }
				  }
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
