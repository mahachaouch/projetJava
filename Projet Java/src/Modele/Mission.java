package Modele;

import java.util.ArrayList;
import java.util.Date;

public abstract class Mission  {
	private Date DateDebut;
	//la durée d une mission se mesure en mois
	private int duree;
	private int codeMission;
	private Boolean autorisExterne=true;
	
	public Boolean getAutorisExterne() {
		return autorisExterne;
	}

	public void setAutorisExterne(Boolean autorisExterne) {
		this.autorisExterne = autorisExterne;
	}

	public Date getDateDebut() {
		return DateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		DateDebut = dateDebut;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	private ArrayList<Competence> listCompetences=new ArrayList<Competence>();
	
	public void ajouterCompetence(Competence c){
		this.listCompetences.add(c);
	}
//TO DO
//public void commencee(){
//	
//}
	
	
}
