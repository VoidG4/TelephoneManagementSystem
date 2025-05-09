<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CallManage</title>
    <link rel="stylesheet" href="css/menu.css">
    <link rel="icon" href="images/logo_icon.ico" type="image/x-icon">
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
	        <p>Choose an action from the menu.</p>
	    </div>
	</div>
</body>
</html>