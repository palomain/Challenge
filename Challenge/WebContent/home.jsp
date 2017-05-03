<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.instacart.challenge.model.ShopperApplication" %>
    
    
 <% 
 	if(request.getSession() != null && request.getSession().getAttribute("sessionData") != null  ){
		ShopperApplication app = (ShopperApplication)request.getSession().getAttribute("sessionData");
		String email = app.getEmail();
		String fname = app.getFirstName();
		String lname = app.getLastName();
		String rcode = app.getReferralCode();
		String phone = app.getPhoneNumber();
		response.sendRedirect("/InstacartChallenge/backgroundcheck.html?email=" + email +"&fname="+fname +"&lname="+lname+"&rcode="+rcode+"&number="+phone); 
	}
 %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<link rel="stylesheet"  href="./css/shopperApp.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<script src ="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" type="text/css" media="screen" title="no title" charset="utf-8"/>
	<script src = "https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js">
		
	</script>
	<script src = "./js/validations.js"></script>
	<script src = "./js/shopperApplication.js"></script>
				
	<div class="jumbotron">
	  <h1>Welcome to instacart!</h1>
	  <div class="container">
    	<p>Earn money shopping and delivering groceries, giving customers more time to do what they love.</p>
	  	<p><a class="btn btn-primary btn-lg" href="#" role="button" onclick="showForm()">Apply Now</a></p>
  	  </div>
	  
	</div>
	
		<form id ="applyForm" name="applyFrom"  action="/InstacartChallenge/StoreSessionData" onsubmit="return validateForm()" method="post" class="hidden" >
			
		 	<div id="formFields"></div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		 </form>
	

</body>
</html>