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
	<form name="addService" method="post" action="AdminServlet?uId='addService'">
		Enter Source: 
		<select name="sourceId">
			<c:forEach var="city" items = "${citiesList}">
		
				<option value="${city.cityId}">${city.cityName}</option>
		
			</c:forEach>
		</select> 
		
		<br><br>
		Enter Destination: 
		<select name="destinationId">
			<c:forEach var="city" items = "${citiesList}">
		
				<option value="${city.cityId}">${city.cityName}</option>
		
			</c:forEach>
		</select> 
		<br></br>
		Number of seats: <input type="number" name="noOfSeats">
		Fare: <input type="number" name="fare"/> <br><br>
		Distance(kms) : <input type="number" name = "distance"/> <br><br>
		Journey Date: <input type="date" name = "journeyDate"/><br><br>
		Departure Time: <input type="time" name="departureTime"/><br><br>
		Arrival Time: <input type="time" name="arrivalTime"/> <br><br>
		Status :
			<select name="status">
				<option value="active"> Active </option>
				<option value="inactive"> Inactive </option>
			</select> <br><br>
		<input type="submit" name="submit" value="Add"/>
	</form>
	<p>${message}</p>
</body>
</html>