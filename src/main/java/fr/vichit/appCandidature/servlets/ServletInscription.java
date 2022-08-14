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
import fr.vichit.appCandidature.messages.LecteurMessage;
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/JSP_connexionEtInscription.jsp");
	rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("motDePasse");
		String confMotDePasse = request.getParameter("confMotDePasse");
		
		try {
			UtilisateurManager uManager = UtilisateurManager.getInstance();
			uManager.ajouterUtilisateur(pseudo, motDePasse,confMotDePasse);
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
