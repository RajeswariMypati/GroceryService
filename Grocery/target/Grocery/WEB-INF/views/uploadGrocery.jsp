<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Grocery Store Portal</title>
</head>
<body>
<form:form method="post" action="./uploadGrocery" enctype="multipart/form-data">

	<table>
		<tr>
			<td>Browse For Grocery File
			<td><input type="file" name="file">
		</tr>
		<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="grocerystore.uploadGrocery"/>"/>
		</td>
	</tr>
</table>	
</form:form>

</body>
</html>