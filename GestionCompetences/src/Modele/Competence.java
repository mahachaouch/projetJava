package Modele;

public class Competence {
	
	private String domaine;
	private String code;
	
	public Competence(String domaine, String code) {
		this.domaine = domaine;
		this.code = code;
	}
 
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	 
}
