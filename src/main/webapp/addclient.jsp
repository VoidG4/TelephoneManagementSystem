<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CallManage</title>
    <link rel="stylesheet" href="css/menu_admin.css">
    <link rel="icon" href="images/logo_icon.ico" type="image/x-icon">
    <style>
        .nonmatching {
            border-color: rgb(255, 0, 6);
        }
    </style>
</head>
<body>
<%
	String username = "n/a";
	response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
	response.setHeader("pragma", "no-cache");
	response.setHeader("expires", "0");
	
	if (session.getAttribute("username") == null) response.sendRedirect("/WebDevelopmentProject2/index.jsp");
	else username = session.getAttribute("username").toString();
%>

<div class="container">
	<div class="menu">
		<h1>Welcome <%= username %> !</h1>
		<form action="seller" method= "post">
			<input type="hidden" name="action" value="displayPrograms">
			<input type="submit" class="button" value="Display all Programs"/>
		</form>
		
		<form action="addclient.jsp" method= "post">
			<input type="hidden" name="action" value="addclient">
			<input type="submit" class="button" value="Add Client"/>
		</form>
		
		<form action="seller" method= "post">
			<input type="hidden" name="action" value="programClient">
			<input type="submit" class="button" value="Match Program with Client"/>
		</form>
		
		<form action="issuebill.jsp" method= "post">
			<input type="submit" class="button" value="Issue bills for clients"/>
		</form>
	
		<img src="images/logo_app.png" width="100%">
	</div>
	
	<div class="content">
        <button class="logout-button" onclick="javascript:location.href='/WebDevelopmentProject2/logout.jsp';">Logout</button>
        <div class="signup">
		    <div class="container_adduser">
		    	<p>Fill in the form to add a new client to the system.</p>
		        <form id="myForm" name="myForm" action="client" method="post">
		            <div class="input-container">
		                <img class="icon" src="images/name.png" alt="Name Icon">
		                <input type="text" name="name" placeholder="Name" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/surname.png" alt="Surname Icon">
		                <input type="text" name="surname" placeholder="Surname" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/afm.png" alt="afm Icon">
		                <input type="text" name="afm" placeholder="Identification Number" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/mail.png" alt="Email Icon">
		                <input type="text" id="email" name="email" placeholder="Email" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/number.png" alt="number Icon">
		                <input type="text" id="number" name="number" placeholder="Phonenumber" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/user.png" alt="Username Icon">
		                <input type="text" name="username" placeholder="Username" required>
		            </div>
		
		            <div class="input-container">
		                <img class="icon" src="images/password.png" alt="Password Icon">
		                <input type="password" id="password" name="password" placeholder="Password" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/repeat.png" alt="Password Repeat Icon">
		                <input type="password" id="passwordRepeat" name="passwordRepeat" placeholder="Password Repeat" required>
		            </div>
		            
		            <input type="hidden" name="sellerUsername" value="<%= username %>">
		            <input type="hidden" name="action" value="addClient">
		            <input type="submit" value="Add">
		        </form>
	    	</div>
    	</div>
	</div>
</div>
	
<script>
document.addEventListener("DOMContentLoaded", () => {
	  const form = document.getElementById('myForm');

	  form.addEventListener('submit', function(event) {
		  const email = document.getElementById("email").value;
		  const password = document.getElementById("password").value;
		  const passwordRepeat = document.getElementById("passwordRepeat").value;
		  let valid = true;

		  if (!email.includes('@') || !email.endsWith('.com')) {
	        document.getElementById("email").classList.add("nonmatching");
	        console.log('Invalid email address!');
	        valid = false;
	      } else {
	        document.getElementById("email").classList.remove("nonmatching");
	      }
	    
	    if (password !== passwordRepeat) {
	      document.getElementById("password").classList.add("nonmatching");
	      document.getElementById("passwordRepeat").classList.add("nonmatching");
	      console.log('Passwords do not match!');
	      valid = false;
	    } else {
	      document.getElementById("password").classList.remove("nonmatching");
	      document.getElementById("passwordRepeat").classList.remove("nonmatching");
	      console.log('Passwords match, form can be submitted.');
	    }
	    
	    if (!valid) {
	        event.preventDefault();
	    }
	  });
	});
</script>
</body>
</html>