package fr.vichit.appCandidature.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.vichit.appCandidature.BusinessException;
import fr.vichit.appCandidature.bo.Utilisateur;
import fr.vichit.appCandidature.dal.CodesResultatDAL;
import fr.vichit.appCandidature.dal.ConnectionProvider;
import fr.vichit.appCandidature.dal.DAO;

public class UtilisateurDAOJdbcImpl implements DAO<Utilisateur>{
	private static final String INSERT = "INSERT INTO UTILISATEURS VALUES (?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS";

	@Override
	public void insert(Utilisateur t) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt ;
			ResultSet rs;
			
			pstmt = cnx.prepareStatement(INSERT);
			
			pstmt.setString(1,t.getPseudo());
			pstmt.setString(2,t.getMotDePasse());
			
			pstmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR);
			throw businessException;
		}
	}

	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		List<Utilisateur> listeUtilisateurs=new ArrayList<>();

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				String pseudo = rs.getString("pseudo");
				String motDePasse = rs.getString("mot_de_passe");
				
				Utilisateur tmpUtilisateur = new Utilisateur(pseudo,motDePasse);
				listeUtilisateurs.add(tmpUtilisateur);
			}
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_UTILISATEUR);
			throw businessException;
		}
		return listeUtilisateurs;
	}

	@Override
	public Utilisateur selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Utilisateur t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
