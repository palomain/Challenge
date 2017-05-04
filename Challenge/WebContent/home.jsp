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
		response.sendRedirect("/InstacartChallenge/backgroundcheck.jsp"); 
	}
 %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Instacart</title>
</head>

<body>
	<link rel="stylesheet"  href="./css/shopperApp.css" >
	<script src="http://vitalets.github.io/x-editable/assets/bootstrap300/js/bootstrap.js" ></script>
	<script src ="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="http://vitalets.github.io/x-editable/assets/bootstrap300/css/bootstrap.css" type="text/css" charset="utf-8" />
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
	
		<form id ="apply-form" name="apply-form"  action="/InstacartChallenge/StoreSessionData" onsubmit="return validateForm()" method="post" class="hidden" >
		 	<div class="form-control">	
				<span class="glyphicon glyphicon-remove" aria-hidden="true" style="float:right" onclick="hideForm()"></span>
				  <div class="form-group">
				    <label for="fname">First Name</label>
				    <input type="text" class="form-control" id="fname" name="fname" placeholder="Enter your first name" maxLength="60">
				    <small  id="fnameHelp" class="form-text  hidden err-message "></small>
				   
				  </div>
				  <div class="form-group">
				  	<label for="lname">Last Name</label>
				    <input type="text" class="form-control" id="lname"  name="lname" placeholder="Enter  your last name" maxLength="60">
				    <small  id="lnameHelp" class="form-text  hidden err-message "></small>
				    
				  </div>
				  <div class="form-group">
				    <label for="userEmail">Email</label>
				    <input type="email" class="form-control" id="email" name="email" placeholder="Enter your Email">
				    <small  id="emailHelp" class="form-text  hidden err-message "></small>
				  </div>	
				  
				  <div class="form-group">
				    <label for="number">Cell phone number</label>
				    <input type="text" class="form-control" id="number" name="number" placeholder="Enter your cell phone number">
				    <small  id="numberHelp" class="form-text  hidden err-message "></small>
				  </div>
				  <div class="form-group">
				    <label for="rcode">Referral code</label>
				    <input type="text" class="form-control" id="rcode" name="rcode" placeholder="Referral Code(optional)">
				    <small  id="numberHelp" class="form-text  hidden err-message "></small>
				  </div>
				  <div style="text-align:center;"><button type="submit" class="btn btn-primary" id="submitShopper">Submit</button></div>
			 </div> 
		  
		 </form>
	

</body>
</html>