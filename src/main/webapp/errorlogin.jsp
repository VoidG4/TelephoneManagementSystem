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
		response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
		response.setHeader("pragma", "no-cache");
		response.setHeader("expires", "0");
		
		if (session.getAttribute("username") == null) response.sendRedirect("/WebDevelopmentProject2/index.jsp");
		else username = session.getAttribute("username").toString().toUpperCase();
	%>
	
	<div class="error-container">
        <img src="images/wrong.png" alt="Error Icon">
        <h1>Login Failed</h1>
        <p>Your username or password is incorrect!</p>
        <a href="index.html">Try Again</a>
    </div>
    
</body>
</html>