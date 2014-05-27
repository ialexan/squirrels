
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
    
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

    
    
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


    <div class="container">


<div id="tabs"> 

  <c:if test="${status=='user already exists'}">
    <script>
        alert("User already exists, please change username or email.");
    </script>
  </c:if>


<h3> My Profile - ${user.username} - <a href="<c:url value='/changepassword?username=${user.username}'/>" class="btn btn-warning">change password</a></h3>

<form class="form-horizontal" role="form" action="<c:url value='/myprofile' />" method="post">
  
  <div class="form-group">
    <label for="editfirstname" class="col-sm-2 control-label">First Name</label>
    <div class="col-sm-10">
      <input type="text" name="firstname" id="firstname" class="form-control" value="${user.firstName}">
    </div>
  </div>

  <div class="form-group">
    <label for="editlastname" class="col-sm-2 control-label">Last Name</label>
    <div class="col-sm-10">
      <input type="text" name="lastname" id="lastname" class="form-control" value="${user.lastName}">
    </div>
  </div>


  <div class="form-group">
    <label for="editemail" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <input type="email" name="email" id="email" class="form-control" value="${user.email}">
    </div>
  </div>


  <input type="hidden" name="username" value="${user.username}" /> 
  <input type="hidden" name="oldEmail" value="${user.email}" /> 

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" name="edit" value="Edit" class="btn btn-default">Edit User Profile</button>
    </div>
  </div>

</form>



      <br>
      <hr>

      <div id="footer">
        <div class="container">
          <p class="text-muted">&#169; California State University Los Angeles 2014 &nbsp;&nbsp; 
          <security:authorize access="hasAnyRole('ADMIN')"> | &nbsp;&nbsp; <a href="<c:url value='/user/management' />" >User Management</a> </security:authorize> </p>
        </div>
      </div>
      
      
    </div> <!-- /container -->



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value='/js/jquery-1.js' />" ></script>
    <script src="<c:url value='/js/bootstrap.js' />"></script>
  

</body></html>

