package Modele;

import java.sql.Date;

public class PersonneInterne extends Personne{

	private Date dateEntree;
	public PersonneInterne(String nom, String prenom, int identifiant,Date dateEntree){
		super(nom,prenom,identifiant);
		this.dateEntree=dateEntree;
	}
}
