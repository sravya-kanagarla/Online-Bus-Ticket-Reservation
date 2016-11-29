<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="serviceStation" method="post" action="AdminServlet?uId='serviceStation'">
		Enter Source Station:
		<select name="sourceStation">
			<c:forEach var="sourceStation" items = "${sourceStationList}">
		
				<option value="${sourceStation.stationId}">${sourceStation.stationName}</option>
		
			</c:forEach>
		</select> 
		<br><br>
		Enter Destination Station:
		<select name="destinationStation">
			<c:forEach var="destinationStation" items = "${destinationStationList}">
		
				<option value="${destinationStation.stationId}">${destinationStation.stationName}</option>
		
			</c:forEach>
		</select> 
		<br><br> 
		<input type="submit" name="submit" value="Add"/>
	</form>
	<p>${message}</p>
</body>
</html>