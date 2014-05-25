<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="California Tree Squirrel Application">
    <meta name="author" content="Ishag Alexanian">
    <link rel="shortcut icon" href="">

    <title>California Tree Squirrel</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value='/css/jumbotron.css' />" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<c:url value='/'/>" >California Squirrel Application</a>
        </div>
        <div class="navbar-collapse collapse">
          
        <security:authorize access="isAnonymous()">
        <form class="navbar-form navbar-right" role="form" action="<c:url value='/j_spring_security_check'/>"  method="post">
            <div class="form-group">
              <input name="j_username" placeholder="Email" class="form-control" type="text">
            </div>
            <div class="form-group">
              <input name="j_password" placeholder="Password" class="form-control" type="password">
            </div>
            <div class="checkbox">
    		<label>
      			<input type="checkbox" name="_spring_security_remember_me"> <span style="color: white;">Remember me</span>
    		</label>
  			</div>
            <button type="submit" class="btn btn-success" name="login">Sign in</button>
          </form>
        </security:authorize>

          <security:authorize access="isAuthenticated()">
          	<form class="navbar-form navbar-right" role="form" action="<c:url value='/j_spring_security_logout'/>"  method="post">
				<button type="submit" class="btn btn-danger" name="login">Sign Out</button>
          	</form>
		  </security:authorize>
        
        </div><!--/.navbar-collapse -->
      </div>
    </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Squirrel!</h1>
        <p>
          The California Tree Squirrel Study is a research project conducted by Dr Alan Muchlinski and an interdisciplinary group of faculty 
          and students from California State University, Los Angeles. Faculty and students are from the Departments of Biological Sciences, 
          Computer Science, and Geosciences and the Environment. If you see a tree squirrel anywhere in California you can help us gather data 
          for this study by using the CaliTreeSquirrel application on your android smart phone.
        </p>
        <p>
        <!--
        <security:authorize access="hasAnyRole('REGULAR_USER', 'APPROVER', 'ADMIN')">
        <a href="<c:url value='/siting/submit'/>" class="btn btn-primary btn-lg" role="button">Submit a Sighting</a>
        </security:authorize>
        -->
        
        <security:authorize access="hasAnyRole('APPROVER', 'ADMIN')">
        <a href="<c:url value='/siting/unverified'/>" class="btn btn-primary btn-lg" role="button">Unverified Sightings</a>
        <a href="<c:url value='/siting/verified'/>" class="btn btn-primary btn-lg" role="button">Verified Sightings</a>
        <a href="<c:url value='/siting/exportSightings'/>" class="btn btn-primary btn-lg" role="button">Export Sightings</a>
        </security:authorize>
        </p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->

			<img src="<c:url value='/img/squirrel1.png' />" alt="image" style="width: 220px; height: 183px; padding:10px;">

        	<img src="<c:url value='/img/squirrel2.png' />" alt="image" style="width: 220px; height: 183px; padding:10px;">

			<img src="<c:url value='/img/squirrel3.png' />" alt="image" style="width: 220px; height: 225x; padding:10px;">

			<img src="<c:url value='/img/squirrel4.png' />" alt="image" style="width: 220px; height: 225x; padding:10px;">

			<img src="<c:url value='/img/squirrel5.png' />" alt="image" style="width: 220px; height: 183px; padding:10px; "> 

      </div>
      
      

      <br>
      <hr>

      <div id="footer">
      	<div class="container">
        	<p class="text-muted">&#169; California State University Los Angeles 2014 &nbsp;&nbsp; | &nbsp;&nbsp; 
          <security:authorize access="isAuthenticated()"> <a href="<c:url value='/myprofile' />" >My Profile</a> &nbsp;&nbsp;  
          </security:authorize>  
          <security:authorize access="hasAnyRole('APPROVER', 'ADMIN')"> | &nbsp;&nbsp; <a href="<c:url value='/user/management' />" >User Management</a> </security:authorize> </p>
      	</div>
      </div>
      

     
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value='/js/jquery-1.js' />" ></script>
    <script src="<c:url value='/js/bootstrap.js' />"></script>
  

</body></html>