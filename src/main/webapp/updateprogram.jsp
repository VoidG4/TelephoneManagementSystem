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
       			<p>Fill in the requirements to update a program.</p>
		        <form id="myForm" name="myForm" action="program" method="post">
		            <div class="input-container">
		                <img class="icon" src="images/name.png" alt="Name Icon">
		                <input type="text" name="name" placeholder="Name" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/speech.png" alt="speechTime Icon">
		                <input type="number" name="speechTime" min = "0" placeholder="Speech Time" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/sms.png" alt="smsNumber Icon">
		                <input type="number" id="email" name="smsNumber" min = "0" placeholder="SMS" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/data.png" alt="dataNumber Icon">
		                <input type="number" name="dataNumber" min = "0" placeholder="Data" required>
		            </div>
		
		            <div class="input-container">
		                <img class="icon" src="images/cost.png" alt="cost Icon">
		                <input type="number" id="cost" name="cost" min = "0" step="0.01" placeholder="Cost" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/duration.png" alt="duration Icon">
		                <input type="number" id="duration" name="duration" placeholder="Duration" required>
		            </div>
		            
		            <div class="input-container">
		                <img class="icon" src="images/description.png" alt="description Icon">
		                <input type="text" id="description" name="description" placeholder="Description" required>
		            </div>
		            
		            <input type="hidden" name="adminUsername" value="<%= username %>">
		            <input type="hidden" name="action" value="updateProgram">
		            <input type="submit" value="Update">
		        </form>
		    </div>
	    </div>
	</div>
</div>
</body>
</html>