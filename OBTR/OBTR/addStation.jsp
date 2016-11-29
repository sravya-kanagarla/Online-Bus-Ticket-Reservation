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
	<form name="addStation" method="post" action="AdminServlet?uId='addStation'">
		Enter Station: <input type="text" name="stationName"/> <br> <br>
		
		Enter City: 
		<select name="cityId">
			<c:forEach var="city" items = "${citiesList}">
		
				<option value="${city.cityId}">${city.cityName}</option>
		
			</c:forEach>
		</select> <br> <br>
	<input type="submit" name="submit" value="Add"/>
	</form>
	
	
	<p>${message}</p>
</body>
</html>