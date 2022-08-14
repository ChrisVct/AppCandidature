package fr.vichit.appCandidature.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Un champs null ou rempli avec espacement
	 */
	public static final int CHAMPS_VIDE = 20000;
	/**
	 * Pseudo existe déjà en base de données
	 */
	public static final int PSEUDO_INDISPONIBLE = 20001;
	/**
	 * Mot de passe ne respect pas tous les critères
	 */
	public static final int MDP_NON_CONFORME = 20002;
	/**
	 * Les deux mots de passe fournis ne sont pas identiques
	 */
	public static final int MOTS_DE_PASSE_INCOHERENTS=20003;

}
