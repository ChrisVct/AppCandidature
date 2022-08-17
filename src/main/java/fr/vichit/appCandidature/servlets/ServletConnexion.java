package fr.vichit.appCandidature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.vichit.appCandidature.BusinessException;
import fr.vichit.appCandidature.bll.UtilisateurManager;
import fr.vichit.appCandidature.bo.Utilisateur;
import fr.vichit.appCandidature.messages.LecteurMessage;
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/JSP_connexionEtInscription.jsp");
	rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("deconnexion")!=null) {
			request.getSession().invalidate();
		}
		RequestDispatcher rd = null;
		String pseudo = request.getParameter("pseudoConnexion");
		String motDePasse = request.getParameter("motDePasseConnexion");
		
		try {
			UtilisateurManager uManager = UtilisateurManager.getInstance();
			uManager.verifierConnection(pseudo, motDePasse);
			request.getSession().setAttribute("utilisateur_connecte", pseudo);
			rd=request.getRequestDispatcher("ServletAccueilCandidatures");
			
		} catch (BusinessException e) {
			List<String> msgErr = new ArrayList<>();
			
			for(int i : ((BusinessException) e).getListeCodesErreur()) {
				msgErr.add(LecteurMessage.getMessageErreur(i));
			}
			request.setAttribute("listeCodesErreur", msgErr);
			rd=request.getRequestDispatcher("/WEB-INF/JSP/JSP_connexionEtInscription.jsp");
		}
		rd.forward(request, response);
	}

}
