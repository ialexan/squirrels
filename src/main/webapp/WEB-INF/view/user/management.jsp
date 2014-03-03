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
$(document).ready(function() {   
	
	/* List All Users */
	
	var listUsers = "<h3> Users List </h3>" +
    "<table class='table table-striped'> <thead> <tr><th>Username</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Action</th></tr> </thead>" + 
	" <tbody> <c:forEach items='${users}' var='user'> <tr> <td>${user.username}</td> <td>${user.firstName}</td> <td>${user.lastName}</td> " +
	" <td>${user.email}</td> <td><button class='btn btn-info' id='${user.id}' name='${user.username}'>Edit</button> &nbsp; <button class='btn btn-warning' id='${user.id}'>Disable</button></td>" +
	"</tr> </c:forEach> </tbody> </table> "
	
	
	window.onload = function(){
    	$("#output").html(listUsers); 
    };
    
    $('#listUsers').click(function(){
    	$("#output").html(listUsers);
    });
	
    
    
    
	/* Add Users Accounts */
	
var addUser = '<h3> Add Users </h3>' +
'<form class="form-horizontal" action="http://cs3.calstatela.edu:4042/squirrels/service/user/register" role="form">' +
'<div class="form-group">' +
'<label for="addusername" class="col-sm-2 control-label">Username</label>' +
'<div class="col-sm-10">' +
'<input type="text" name="username" id="addusername" class="form-control" placeholder="Username">' +
'</div></div>' +
'<div class="form-group">' +
'<label for="addpassword" class="col-sm-2 control-label">Password</label>' +
'<div class="col-sm-10">' +
'<input type="password" name="password" id="addpassword" class="form-control" placeholder="Password">' +
'</div></div>' + 
'<div class="form-group">' +
'<label for="addfirstname" class="col-sm-2 control-label">First Name</label>' +
'<div class="col-sm-10">' +
'<input type="text" name="firstname" id="addfirstname" class="form-control" placeholder="John">' +
'</div></div>' +
'<div class="form-group">' +
'<label for="addlastname" class="col-sm-2 control-label">Last Name</label>' +
'<div class="col-sm-10">' +
'<input type="text" name="lastname" id="addlastname" class="form-control" placeholder="Doe">' +
'</div></div>' +
'<div class="form-group">' +
'<label for="addemail" class="col-sm-2 control-label">Email</label>' +
'<div class="col-sm-10">' +
'<input type="email" name="email" id="addemail" class="form-control" placeholder="Email">' +
'</div></div>' +
'<div class="form-group">' +
'<div class="col-sm-offset-2 col-sm-10">' +
'<button id="addusersbutton" class="btn btn-default">Add</button>' +
'</div></div></form>';  
  
    // Add user form
    $('#addUser').click(function(){
    	$("#output").html(addUser);
    });
    
    // After clicking the add user form
    $('#addusersbutton').click(function(){
        var addusername = $('#addusername').attr('value');
        var addpassword = $('#addpassword').attr('value');
        var addfirstname = $('#addfirstname').attr('value');
        var addlastname = $('#addlastname').attr('value');
        var addemail = $('#addemail').attr('value');
        
        jQuery.ajax({
            url:'http://cs3.calstatela.edu:4042/squirrels/service/user/register?adduser='+addusername+'&firstName='+addfirstname+
            		'&lastName='+addlastname+'&email='+addemail+'&addpassword'+password,
            success:function(data){
                /* if(data==-1)
                    alert('Assigned to someone else'); */
                //jQuery("#"+id).parent("td").parent("tr").fadeOut(); 
            	$("#output").html(listUsers);    
            }
        });
        return false;
    });
   
    
    
    /* Edit User Accounts */
    
var editUser = '<form class="form-horizontal" role="form">' +
'<div class="form-group">' +
'<label for="editusername" class="col-sm-2 control-label">Username</label>' +
'<div class="col-sm-10">' +
'<input type="text" name="editusername" id="editusername" class="form-control">' +
'</div></div>' +
'<div class="form-group">' +
'<label for="editpassword" class="col-sm-2 control-label">Password</label>' +
'<div class="col-sm-10">' +
'<input type="password" name="editpassword" id="editpassword" class="form-control">' +
'</div></div>' + 
'<div class="form-group">' +
'<label for="editfirstname" class="col-sm-2 control-label">First Name</label>' +
'<div class="col-sm-10">' +
'<input type="text" name="editfirstname" id="editfirstname" class="form-control">' +
'</div></div>' +
'<div class="form-group">' +
'<label for="editlastname" class="col-sm-2 control-label">Last Name</label>' +
'<div class="col-sm-10">' +
'<input type="text" name="editlastname" id="editlastname" class="form-control">' +
'</div></div>' +
'<div class="form-group">' +
'<label for="editemail" class="col-sm-2 control-label">Email</label>' +
'<div class="col-sm-10">' +
'<input type="email" name="editemail" id="editemail" class="form-control">' +
'</div></div>' +
'<div class="form-group">' +
'<div class="col-sm-offset-2 col-sm-10">' +
'<button type="submit" name="edit" value="Edit" class="btn btn-default">Edit</button>' +
'</div></div></form>';
    

    $(document).on('click', '.btn-info', function() {
        console.log('You are in edit');
        var id = $(this).attr('id');
        var str = "<h3> Edit User - " + $(this).attr('name') + " </h3>";
        var str = str.concat(editUser);
        
        $("#output").html(str);
        return false;
    });
    
    
    /* Disable Users */
    
   $(document).on('click', '.btn-warning', function() {
        var id = $(this).attr('id');
        
        $("#output").html("Disable");
    });
    
    
});
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
<button id="listUsers" class="btn btn-primary">List Users</button>
<button id="addUser" class="btn btn-success">Add a User</button>
</div>



<div id="output">

</div>


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

