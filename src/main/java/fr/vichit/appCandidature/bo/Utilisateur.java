package fr.vichit.appCandidature.bo;

import java.util.Set;

public class Utilisateur {
	private String pseudo;
	private String motDePasse;
	
	/**
	 * 
	 */
	public Utilisateur() {
	}
	
	
	/**
	 * @param pseudo
	 * @param motDePasse
	 */
	public Utilisateur(String pseudo, String motDePasse) {
		setPseudo(pseudo);
		setMotDePasse(motDePasse);
	}


	@Override
	public String toString() {
		return "Utilisateur [pseudo=" + pseudo + ", motDePasse=" + motDePasse + "]";
	}


	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	
}
