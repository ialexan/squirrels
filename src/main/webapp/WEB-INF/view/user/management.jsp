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
 

<script>

function disableUser(userId, user_name) {
    
    var r = confirm("Are you sure you want to disable - " + user_name + " ?");
    
    if (r == true) {
      jQuery.ajax({   
            url:"<c:url value='/user/disableuser?userId='/>"+userId,
            success:function(data){
                /* if(data==-1)
                    alert('Assigned to someone else'); */
                jQuery("#"+userId).parent("td").parent("tr").fadeOut();   
            }
        });    
    } 
    
}

// jQuery(document).ready(function() {
//     jQuery('.btn-warning').click(function(){
//         var id = jQuery(this).attr('id');
//         jQuery.ajax({   
//             url:"<c:url value='/user/disableuser?userId='/>"+id,
//             success:function(data){
//                 /* if(data==-1)
//                     alert('Assigned to someone else'); */
//                 jQuery("#"+id).parent("td").parent("tr").fadeOut();   
//             }
//         });
//         return false;
//     });
// });
</script>

    
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
<security:authorize access="hasAnyRole('ADMIN')">  
  <a href="<c:url value='/user/management'/>" class="btn btn-primary" role="button">List Users</a>
  <a href="<c:url value='/user/add'/>" class="btn btn-success" role="button">Add a User</a>
</security:authorize>
</div>



  <h3> Users List </h3>
  <table class='table table-striped'> 
    <thead> <tr><th>Username</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Action</th></tr> </thead>
    <tbody> 
      <c:forEach items='${users}' var='user'> 
        <tr> 
        <td>${user.username}</td> 
        <td>${user.firstName}</td> 
        <td>${user.lastName}</td> 
        <td>${user.email}</td> 
        <td> 
        <security:authorize access="hasAnyRole('ADMIN')">  
          <a href="<c:url value='/user/edit?userId=${user.id}'/>" class="btn btn-info" role="button">Edit</a>
          <button class='btn btn-warning' id="${user.id}" onclick="disableUser(${user.id}, '${user.username}')">Disable</button>
        </security:authorize>
        </td>
        </tr> 
      </c:forEach> 
    </tbody> 
  </table> 



      <br>
      <hr>

      <div id="footer">
        <div class="container">
          <p class="text-muted">&#169; California State University Los Angeles 2014 &nbsp;&nbsp; 
            <security:authorize access="isAuthenticated()"> | &nbsp;&nbsp; <a href="<c:url value='/myprofile' />" >My Profile</a> &nbsp;&nbsp;  
            </security:authorize>  
          </p>
        </div>
      </div>
      
      
    </div> <!-- /container -->



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value='/js/jquery-1.js' />" ></script>
    <script src="<c:url value='/js/bootstrap.js' />"></script>
  

</body></html>

