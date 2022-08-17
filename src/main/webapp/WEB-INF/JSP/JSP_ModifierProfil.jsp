<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Candidature.fr - Profil</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
      defer
    ></script>
  </head>
  <header>
	<%@include file="JSP_NavBarConnecte.jspf" %>
  </header>
  <body>
	<c:if test="${!empty listeCodesErreur }">
		<c:forEach var="err" items="${listeCodesErreur}">
			<p style="color:red;">${err }</p>
		</c:forEach>
	</c:if>
    <form action="ServletModifierProfil" method="post" class="row col-4 mx-auto">
      <h2 class="text-center my-5">S'inscrire</h2>
      <div class="mb-3 row">
        <label for="pseudo" class="col-sm-4 col-form-label">Pseudo</label>
        <div class="col-sm-8">
          <p>${utilisateur_connecte}</p>
        </div>
      </div>
      <div class="mb-3 row">
        <label for="ancienMotDePasse" class="col-sm-4 col-form-label my-auto"
          >Anicen mot De Passe</label
        >
        <div class="col-sm-8 my-auto">
          <input
            type="text"
            class="form-control"
            id="ancienMotDePasse"
            name="ancienMotDePasse"
            required
          />
        </div>
      </div>
      <div class="mb-3 row">
        <label for="nouveauMotDePasse" class="col-sm-4 col-form-label"
          >Nouveau mot De Passe</label
        >
        <div class="col-sm-8 my-auto">
          <input
            type="text"
            class="form-control"
            id="nouveauMotDePasse"
            name="nouveauMotDePasse"
            required
          />
        </div>
      </div>
      <div class="mb-3 row">
        <label for="confNouveauMotDePasse" class="col-sm-4 col-form-label"
          >Confirmation nouveau mot De Passe</label
        >
        <div class="col-sm-8 my-auto">
          <input
            type="text"
            class="form-control"
            id="confNouveauMotDePasse"
            name="confNouveauMotDePasse"
            required
          />
        </div>
      </div>
      <div class="row d-flex justify-content-evenly">
        <button type="submit" class="btn btn-secondary col-3 my-3">
          Valider
        </button>
        <a href="ServletAccueilCandidatures" class="btn btn-secondary col-3 my-3"
          >Annuler</a
        >
      </div>
    </form>
  </body>
</html>
