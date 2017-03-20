package Modele;

public class Competence {
	
	@Override
	public String toString() {
		return "Competence [nomAng=" + nomAng + ", code=" + code + "]";
	}
	private String nomAng;
	private String nomFr;
	private String code;
	
	/**
	 * @param code
	 * @param nomAng
	 * @param nomFr
	 */
	public Competence( String code,String nomAng,String nomFr) {
		this.nomAng = nomAng;
		this.code = code;
		this.nomFr=nomFr;
	}
 
	/**
	 * @return
	 */
	public String getNomAng() {
		return nomAng;
	}
	/**
	 * @param nomAng
	 */
	public void setNomAng(String nomAng) {
		this.nomAng = nomAng;
	}
	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return
	 */
	public String getNomFr() {
		return nomFr;
	}

	/**
	 * @param nomFr
	 */
	public void setNomFr(String nomFr) {
		this.nomFr = nomFr;
	}
	 
}
