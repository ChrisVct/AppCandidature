package fr.vichit.appCandidature.dal;

import fr.vichit.appCandidature.bo.Utilisateur;
import fr.vichit.appCandidature.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	
	public static DAO<Utilisateur> getDAOUtilisateur() {
		return new UtilisateurDAOJdbcImpl();
	}

}
