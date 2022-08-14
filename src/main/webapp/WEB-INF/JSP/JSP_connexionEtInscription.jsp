<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Bootstrap demo</title>
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
    <nav
      class="navbar navbar-expand-lg bg-secondary col-xxl-8 mx-auto rounded-3"
      style="--bs-bg-opacity: 0.5"
    >
      <div class="container-fluid">
        <h1 class="fst-italic font-monospace fs-2 text">Candidature.fr</h1>
      </div>
    </nav>
  </header>
  <body>
    <div class="text-center mt-5">
		<c:if test="${!empty listeCodesErreur }">
			<c:forEach var="err" items="${listeCodesErreur}">
				<p style="color:red;">${err }</p>
			</c:forEach>
		</c:if>
	</div>
    <div class="container d-flex flex-row mx-auto my-5 justify-content-center">
      <form action="ServletConnexion" method="post" class="col-5">
        <h2 class="text-center my-5">Se Connecter</h2>
        <div class="mb-3 row">
          <label for="pseudo" class="col-sm-4 col-form-label">Pseudo</label>
          <div class="col-sm-8">
            <input
              type="text"
              class="form-control"
              id="pseudo"
              name="pseudo"
              required
            />
          </div>
        </div>
        <div class="mb-3 row">
          <label for="motDePasse" class="col-sm-4 col-form-label"
            >Mot De Passe</label
          >
          <div class="col-sm-8">
            <input
              type="text"
              class="form-control"
              id="motDePasse"
              name="motDePasse"
              required
            />
          </div>
        </div>
        <div class="row">
          <button type="submit" class="btn btn-secondary col-3 mx-auto my-3">
            Connexion
          </button>
        </div>
      </form>
      <form action="ServletInscription" method="post" class="col-5 offset-1">
        <h2 class="text-center my-5">S'inscrire</h2>
        <div class="mb-3 row">
          <label for="pseudo" class="col-sm-4 col-form-label">Pseudo</label>
          <div class="col-sm-8">
            <input
              type="text"
              class="form-control"
              id="pseudo"
              name="pseudo"
              required
            />
          </div>
        </div>
        <div class="mb-3 row">
          <label for="motDePasse" class="col-sm-4 col-form-label"
            >Mot De Passe</label
          >
          <div class="col-sm-8">
            <input
              type="text"
              class="form-control"
              id="motDePasse"
              name="motDePasse"
              required
            />
          </div>
        </div>
        <div class="mb-3 row">
          <label for="confMotDePasse" class="col-sm-4 col-form-label"
            >Confirmation Mot De Passe</label
          >
          <div class="col-sm-8">
            <input
              type="text"
              class="form-control"
              id="confMotDePasse"
              name="confMotDePasse"
              required
            />
          </div>
        </div>
        <div class="row">
          <button type="submit" class="btn btn-secondary col-3 mx-auto my-3">
            Inscription
          </button>
        </div>
      </form>
    </div>
  </body>
</html>
