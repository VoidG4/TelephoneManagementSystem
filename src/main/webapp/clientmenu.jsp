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
			<form action = "client" method = "post">
				<input type="hidden" name="action" value="displayBill">
				<input type="hidden" name="username" value="<%= username %>">
				<input type="submit" class="button" value="Display Bill"/>
			</form>
			
			<form action = "client" method = "post">
				<input type="hidden" name="action" value="callHistory">
				<input type="hidden" name="username" value="<%= username %>">
				<input type="submit" class="button" value="Call History"/>
			</form>
			
			<form action = "client" method = "post">
				<input type="hidden" name="action" value="payBills">
				<input type="hidden" name="username" value="<%= username %>">
				<input type="submit" class="button" value="Pay Bill"/>
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