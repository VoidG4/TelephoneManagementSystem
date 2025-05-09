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
			<input type="button" class="button" value="Add Seller" onclick="javascript:location.href='/WebDevelopmentProject2/addseller.jsp';"/>
			<input type="button" class="button" value="Add Program" onclick="javascript:location.href='/WebDevelopmentProject2/addprogram.jsp';"/>
			<input type="button" class="button" value="Delete Seller" onclick="javascript:location.href='/WebDevelopmentProject2/deleteseller.jsp';"/>
			<input type="button" class="button" value="Delete Client" onclick="javascript:location.href='/WebDevelopmentProject2/deleteclient.jsp';"/>
			<input type="button" class="button" value="Update Program" onclick="javascript:location.href='/WebDevelopmentProject2/updateprogram.jsp';"/>
			<input type="button" class="button" value="Delete Program" onclick="javascript:location.href='/WebDevelopmentProject2/deleteprogram.jsp';"/>
	
			<img src="images/logo_app.png" width="100%">
		</div>
		
		<div class="content">
           <button class="logout-button" onclick="javascript:location.href='/WebDevelopmentProject2/logout.jsp';">Logout</button>
          	<div class="signup">
          		<div class="container_adduser">
		          	<p>Type the username of the seller that you want to delete.</p>
		          	<form id="myForm" name="myForm" action="seller" method="post">
			            <div class="input-container">
			                <img class="icon" src="images/user.png" alt="Username Icon">
			                <input type="text" name="username" placeholder="Username of seller" required>
			            </div>
			            
			            <input type="hidden" name="adminUsername" value="<%= username %>">
			            <input type="hidden" name="action" value="deleteSeller">
			            <input type="submit" value="Delete seller">
				    </form>
			   </div>
          	</div>
        </div>
	</div>
</body>
</html>