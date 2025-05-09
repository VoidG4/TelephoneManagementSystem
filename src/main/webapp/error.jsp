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
		else username = session.getAttribute("username").toString().toUpperCase();
	%>
	
	<div class="error-container">
        <img src="images/wrong.png" alt="Error Icon">
        <h1>Something went wrong!</h1>
        <p>It seems that there was a problem!</p>
        <a href="clientmenu.jsp">Go back to menu</a>
    </div>
    
</body>
