package fr.vichit.appCandidature.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import fr.vichit.appCandidature.BusinessException;
import fr.vichit.appCandidature.bo.Utilisateur;
import fr.vichit.appCandidature.dal.DAO;
import fr.vichit.appCandidature.dal.DAOFactory;

public class UtilisateurManager {
	private DAO<Utilisateur> daoUtilisateur;
	private static UtilisateurManager instance;
	private static List<Utilisateur> listeUtilisateurs;
	
	private UtilisateurManager() throws BusinessException {
		this.daoUtilisateur = DAOFactory.getDAOUtilisateur();
		listeUtilisateurs = daoUtilisateur.selectAll();
	}
	
	public static UtilisateurManager getInstance() throws BusinessException {
		if(instance==null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	public void ajouterUtilisateur(String pseudo, String motDePasse, String confMotDePasse) throws BusinessException{
		BusinessException businessException = new BusinessException();
		//verif pseudo
		this.verifierNullite(pseudo, businessException);
		this.verifierUnicitePseudo(pseudo, businessException);
		//verif mdp
		this.verifierNullite(motDePasse, businessException);
		this.verifierConformiteMotDePasse(motDePasse, businessException);
		//verif confirmation MDP
		this.verifierNullite(confMotDePasse, businessException);
		this.verifierCoherenceMotsDePasse(motDePasse,confMotDePasse,businessException);
		
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		
		//insertion en BDD
		Utilisateur utilisateurAAjouter=new Utilisateur(pseudo.trim(), hasherMotDePasse(motDePasse));
		daoUtilisateur.insert(utilisateurAAjouter);
		
		//ajout en liste tampon
		listeUtilisateurs.add(utilisateurAAjouter);
	}
	public void verifierConnection(String pseudo, String motDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
		boolean identifiantsOK=false;

		this.verifierNullite(pseudo, businessException);
		this.verifierNullite(motDePasse, businessException);
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		
		identifiantsOK= this.verifierIdentifiants(pseudo,motDePasse);
		if(!identifiantsOK) {
			businessException.ajouterErreur(CodesResultatBLL.AUTHENTIFICATION_ERREUR);
			throw businessException;
		}
	}
	public void mettreAJourMDP(String pseudo, String ancienMDP, String nouveauMDP, String confNouveauMDP) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.verifierNullite(pseudo, businessException);
		this.verifierNullite(ancienMDP, businessException);
		this.verifierNullite(nouveauMDP, businessException);
		this.verifierNullite(confNouveauMDP, businessException);
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		this.verifierConnection(pseudo, ancienMDP);
		this.verifierConformiteMotDePasse(nouveauMDP, businessException);
		this.verifierCoherenceMotsDePasse(nouveauMDP,confNouveauMDP,businessException);
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		Utilisateur utilisateurAActualiser = new Utilisateur(pseudo, hasherMotDePasse(nouveauMDP));
		daoUtilisateur.update(utilisateurAActualiser);
		for (Utilisateur u : listeUtilisateurs) {
			if(pseudo.equalsIgnoreCase(u.getPseudo()))
				u.setMotDePasse(hasherMotDePasse(confNouveauMDP));
		}
	}


	private void verifierNullite(String champs, BusinessException businessException) {
		if(champs.trim()=="") {
			businessException.ajouterErreur(CodesResultatBLL.CHAMPS_VIDE);
		}
	}
	private void verifierUnicitePseudo(String pseudo, BusinessException businessException) {
		for (Utilisateur u : listeUtilisateurs) {
			if(pseudo.trim().equalsIgnoreCase(u.getPseudo())){
				businessException.ajouterErreur(CodesResultatBLL.PSEUDO_INDISPONIBLE);
			}
		}		
	}
	
	private void verifierConformiteMotDePasse(String motDePasse, BusinessException businessException){
		// verifie le password
		 int uppercaseCounter=0;// Compte le nombre de lettres majuscules dans un mot de passe
		 int lowercaseCounter=0;// Compte les lettres minuscules dans un mot de passe
		 int digitCounter=0;// Compte le nombre de digit dans le mot de passe
		 int specialCounter=0;// Compte le nombre de caractères spéciaux dans le mot de passe
		
		 for (int i=0; i < motDePasse.length(); i++ ) {
		        char c = motDePasse.charAt(i);
		        if(Character.isUpperCase(c)) 
		            uppercaseCounter++;
		        else if(Character.isLowerCase(c)) 
		            lowercaseCounter++;
		        else if(Character.isDigit(c))
		        	digitCounter++;     
		        if(c>=33&&c<=46||c==64)
		        	specialCounter++;
		 }
         if (!(motDePasse.length() >= 12 && uppercaseCounter >= 1
     		 	&& lowercaseCounter >= 1 && digitCounter >= 1 && specialCounter >= 1)) { 
        	 businessException.ajouterErreur(CodesResultatBLL.MDP_NON_CONFORME);
	     }
	}
	private void verifierCoherenceMotsDePasse(String motDePasse, String confMotDePasse,
			BusinessException businessException) {
		if(!motDePasse.equals(confMotDePasse))
			businessException.ajouterErreur(CodesResultatBLL.MOTS_DE_PASSE_INCOHERENTS);
	}
	private String hasherMotDePasse(String motDePasseClair) {
		StringBuffer hexString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(motDePasseClair.getBytes());
			byte[] digest = md.digest();
		      hexString = new StringBuffer();
		      for (int i = 0;i<digest.length;i++) {
		         hexString.append(Integer.toHexString(0xFF & digest[i]));
		      }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}
	private boolean verifierIdentifiants(String pseudo, String motDePasse) {
		boolean identifiantsOK = false;
		for (Utilisateur u : listeUtilisateurs) {
			if(pseudo.equalsIgnoreCase(u.getPseudo()))
			{
				if(hasherMotDePasse(motDePasse).equals(u.getMotDePasse())) {
					identifiantsOK=true;
				}
			}
		}
		return identifiantsOK ;
	}

	
}
