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


<h2>User Management</h2>

<div id="tabs"> 
<security:authorize access="hasAnyRole('APPROVER', 'ADMIN')">  
  <a href="<c:url value='/siting/user/management'/>" class="btn btn-primary btn-lg" role="button">List Users</a>
</security:authorize>
</div>



  
<h3> Add Users </h3>
<form class="form-horizontal" action="http://cs3.calstatela.edu:4042/squirrels/service/user/register" role="form">
  <div class="form-group">
    <label for="addusername" class="col-sm-2 control-label">Username</label>
    <div class="col-sm-10">
      <input type="text" name="username" id="addusername" class="form-control" placeholder="Username">
    </div>
  </div>

  <div class="form-group">
    <label for="addpassword" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <input type="password" name="password" id="addpassword" class="form-control" placeholder="Password">
    </div>
  </div>

  <div class="form-group">
    <label for="addfirstname" class="col-sm-2 control-label">First Name</label>
    <div class="col-sm-10">
      <input type="text" name="firstname" id="addfirstname" class="form-control" placeholder="John">
    </div>
  </div>

  <div class="form-group">
    <label for="addlastname" class="col-sm-2 control-label">Last Name</label>
    <div class="col-sm-10">
      <input type="text" name="lastname" id="addlastname" class="form-control" placeholder="Doe">
    </div>
  </div>

  <div class="form-group">
    <label for="addemail" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <input type="email" name="email" id="addemail" class="form-control" placeholder="Email">
    </div>
  </div>

  <div class="form-group">
    <label for="userrole" class="col-sm-2 control-label">User Role</label>
    <div class="col-sm-10">
      <select class="form-control" id="userrole" name="userrole">
        <option>ADMIN</option>
        <option>APPROVER</option>
        <option>REGULAR_USER</option>
      </select>
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="addusersbutton" class="btn btn-default">Add User</button>
    </div>
  </div>

</form>  
 




      <br>
      <hr>

      <div id="footer">
      	<div class="container">
        	<p class="text-muted">&#169; California State University Los Angeles 2014 &nbsp;&nbsp; | &nbsp;&nbsp; More Information &nbsp;&nbsp; | &nbsp;&nbsp; <a href="<c:url value='/user/management' />" >User Management</a></p>
      	</div>
      </div>
      
      
    </div> <!-- /container -->



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value='/js/jquery-1.js' />" ></script>
    <script src="<c:url value='/js/bootstrap.js' />"></script>
  

</body></html>
