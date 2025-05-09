<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CallManage</title>
    <link rel="stylesheet" href="css/error.css">
    <link rel="icon" href="images/logo_icon.ico" type="image/x-icon">
</head>

<body>

	<%
		String username = "n/a";
		if (session.getAttribute("username") == null) response.sendRedirect("/WebDevelopmentProject2/index.html");
		else username = session.getAttribute("username").toString();
	%>
	
	<div class="error-container">
        <img src="images/tick.png" alt="Error Icon">
        
        <h1>Successful Logout</h1>
        <p>You have logged out successfully.</p> 
        <p>See you soon <b><%= username %></b>!</p>
        <%
			session.removeAttribute("username");
			session.removeAttribute("property");
			session.invalidate();
		%>
        <a href="index.html">Login</a>
    </div>
    
</body>
</html>