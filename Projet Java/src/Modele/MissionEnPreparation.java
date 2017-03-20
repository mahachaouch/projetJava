package Modele;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;

public class MissionEnPreparation extends Mission {
	
	 private Hashtable<Competence,Integer> nbPersonneParComp=new Hashtable<Competence,Integer>();
	 
	 public Hashtable<Competence, Integer> getNbPersonneParComp() {
		return nbPersonneParComp;
	}

	public void setNbPersonneParComp(Hashtable<Competence, Integer> nbPersonneParComp) {
		this.nbPersonneParComp = nbPersonneParComp;
	}

	
	 public void ajouterNbPers(Integer pers,Competence cpt ){
		 //vérification : on n ajoute une nouvelle valeur que si la competence n existe pas
		 if(!this.nbPersonneParComp.containsKey(cpt)) {
			 this.nbPersonneParComp.put(cpt, pers);
		 }
	 }
	 
	 public void modifierNbPersonne(Integer nb,Competence cpt){
		 if(this.nbPersonneParComp.containsKey(cpt)) {
			 this.nbPersonneParComp.put(cpt,nb);
		 }
	 }
	 
	 public void afficherListNbPersParCompt(){
		 
		 String hashstring= "";
		    for (Entry<Competence, Integer> entry : this.getNbPersonneParComp().entrySet()) {
		        hashstring += entry.getKey().getCode() + "=" + entry.getValue() + "|";
		    }
			System.out.println(hashstring);
	 }
 
}
