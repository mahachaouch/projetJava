package Modele;

import java.util.Date;
import java.util.ArrayList;
import java.util.Hashtable;

public class MissionPlanifiee extends Mission{

	private Hashtable<String,ArrayList<Personne>> nbPersonneParComp;
	 public MissionPlanifiee(){
		 super();
		 this.nbPersonneParComp=new Hashtable<String,ArrayList<Personne>>();
	 }
	 
	 public void affecterPersonne(Personne p,String codeCompet){
		 
		 if(this.nbPersonneParComp.containsKey(codeCompet)) {
			 this.nbPersonneParComp.get(codeCompet).add(p);
			 p.augmenterNbMission();
		 } else {
			 ArrayList<Personne> l = new ArrayList<Personne>();
			 l.add(p);
			 this.nbPersonneParComp.put(codeCompet,l);
		 }
	 }

	public Hashtable<String, ArrayList<Personne>> getNbPersonneParComp() {
		return nbPersonneParComp;
	}

	public void setNbPersonneParComp(
			Hashtable<String, ArrayList<Personne>> nbPersonneParComp) {
		this.nbPersonneParComp = nbPersonneParComp;
	}
}
