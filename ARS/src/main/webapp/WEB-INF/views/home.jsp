<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	ARS Test Page (home.jsp)
</h1>

<c:forEach items="${pages}" var="page">
	<p>Id: ${page.id}</p>
</c:forEach>

<form:form method="POST" action="save_page" modelAttribute="page">
	<form:label path="url">URL:</form:label>
    <form:input path="url"/>
    
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
