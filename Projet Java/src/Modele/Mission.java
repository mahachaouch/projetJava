package Modele;

import java.util.ArrayList;
import java.util.Date;

public abstract class Mission  {
	private Date DateDebut;
	//la durée d une mission se mesure en mois
	private int duree;
	private int codeMission;
	private Boolean autorisExterne=true;
	private ArrayList<Competence> listCompetences=new ArrayList<Competence>();
	
	/**
	 * @return
	 */
	public Boolean getAutorisExterne() {
		return autorisExterne;
	}

	/**
	 * @param autorisExterne
	 */
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

	
	
	public void ajouterCompetence(Competence c){
		this.listCompetences.add(c);
	}

	public int getCodeMission() {
		return codeMission;
	}

	public void setCodeMission(int codeMission) {
		this.codeMission = codeMission;
	}

	public ArrayList<Competence> getListCompetences() {
		return listCompetences;
	}

	public void setListCompetences(ArrayList<Competence> listCompetences) {
		this.listCompetences = listCompetences;
	}

	
}
