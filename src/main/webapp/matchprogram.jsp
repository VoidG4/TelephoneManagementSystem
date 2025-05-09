<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CallManage</title>
    <link rel="stylesheet" href="css/menu_admin.css">
    <link rel="icon" href="images/logo_icon.ico" type="image/x-icon">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .table_contents {
			height: 80%;
			width: 80%;
			padding: 10px;
			background-color: #f0f0f0;
		    border-radius: 10px;
		    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.7);
		}
		.usermenu {
			width: 100%;
		    height: 80%;
		    margin: 50px auto;
		    padding: 15px; 
		    justify-content: center;
		    align-items: center;
		    text-align: center;
		    display:flex;
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
	    	<div class="usermenu">
	    		
			    <div class="table_contents">
			    	<p>Fill in the client's number in the program that they want.</p>
				    <table>
				        <thead>
				            <tr style="color:white;">
				                <th style="background-color:black;">Program</th>
				                <th style="background-color:black;">Phone number</th>
				                <th style="background-color:black;">Submit</th>
				            </tr>
				        </thead>
				        <tbody>
				            <c:forEach var="program" items="${programs}">
				                <tr>
				                    <td>${program.nameOfProgram}</td>
				                    <form action="seller" method="post">
				                    	<input type="hidden" name="action" value="addProgramClient">
					                    <input type="hidden" name="program" value="${program.id}">
					                    <td><input type="text" name="number"></td>
					                    <td><input type="submit" value="Add program to number"></td>
				           			</form>
				                </tr>
				            </c:forEach>
				        </tbody>
				    </table>
			    </div>
		    </div>
	    </div>   
	</div>
</body>
</html>