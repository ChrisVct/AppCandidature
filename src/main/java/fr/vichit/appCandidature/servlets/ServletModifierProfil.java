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

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ServletModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/JSP_ModifierProfil.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = (String) request.getSession().getAttribute("utilisateur_connecte");
		String ancienMDP = request.getParameter("ancienMotDePasse");
		String nouveauMDP = request.getParameter("nouveauMotDePasse");
		String confNouveauMDP = request.getParameter("confNouveauMotDePasse");
		RequestDispatcher rd = null;
		
		try {
			UtilisateurManager uManager = UtilisateurManager.getInstance();
			uManager.mettreAJourMDP(pseudo, ancienMDP, nouveauMDP, confNouveauMDP);
			rd=request.getRequestDispatcher("ServletAccueilCandidatures");
		} catch (BusinessException e) {
			List<String> msgErr = new ArrayList<>();
			
			for(int i : ((BusinessException) e).getListeCodesErreur()) {
				msgErr.add(LecteurMessage.getMessageErreur(i));
			}
			request.setAttribute("listeCodesErreur", msgErr);
			rd=request.getRequestDispatcher("/WEB-INF/JSP/JSP_ModifierProfil.jsp");
		}
		rd.forward(request, response);
	}

}
