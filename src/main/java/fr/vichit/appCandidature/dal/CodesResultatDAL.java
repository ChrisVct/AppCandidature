package fr.vichit.appCandidature.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec lors de l'ajout du nouvel utilisateur
	 */
	public static final int INSERT_UTILISATEUR=10000;
	/**
	 * Echec lors de la récupération des utilisateurs en BDD
	 */
	public static final int SELECT_ALL_UTILISATEUR=10001;


}
