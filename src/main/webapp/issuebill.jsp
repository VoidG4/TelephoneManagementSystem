<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CallManage</title>
    <link rel="stylesheet" href="css/menu_admin.css">
    <link rel="icon" href="images/logo_icon.ico" type="image/x-icon">
</head>
<body>
	<%
		String username = "n/a";
		response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
		response.setHeader("pragma", "no-cache");
		response.setHeader("expires", "0");
		
		if (session.getAttribute("username") == null) response.sendRedirect("/WebDevelopmentProject2/index.html");
		else username = session.getAttribute("username").toString();
		
		session.setAttribute("action", "addProgram");
	%>
	
	<div class="container">
		<div class="menu">
			<h1>Hello <%= username %> !</h1>
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
		          	<p>Type the number of the client to issue their bill.</p>
		          	<form id="myForm" name="myForm" action="client" method="post">
			            <div class="input-container">
			                <img class="icon" src="images/number.png" alt="Number Icon">
			                <input type="number" name="number" placeholder="Number" required>
			            </div>
			            
			            <input type="hidden" name="adminUsername" value="<%= username %>">
			            <input type="hidden" name="action" value="issueBill">
			            <input type="submit" value="Submit">
				    </form>
			   </div>
          	</div>
        </div>
	</div>
</body>
</html>