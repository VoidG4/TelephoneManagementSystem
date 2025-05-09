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
	    	<div class="usermenu">
			    <div class="table_contents">
				    <table>
				        <thead>
				            <tr style="color:white;">
				                <th style="background-color:black;">Month</th>
				                <th style="background-color:black;">Payment</th>
				                <th style="background-color:black;"></th>
				            </tr>
				        </thead>
				        
			            <tbody>
						    <c:set var="totalCost" value="0" />
						    <c:forEach var="bill" items="${bills}">
						        <tr>
						            <td>${bill.month}</td>
						            <td>${bill.payment} &#8364</td>
						            <form method = "post" action = "client">
							            <input type = "hidden" name ="action" value= "deleteBill">
							            <input type = "hidden" name ="id" value= "${bill.id}">
							            <input type = "hidden" name ="action" value= "deleteBill">
							            <td><input type = "submit" value="Pay Bill"></td>
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