<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
    <c:if test="${needs_update}">
        <div class="alert alert-danger">
            Your data is more than 2 Hours old.
            <form:form method="POST" action="database/update">
                <input type="submit" value="Update"/>
            </form:form>
        </div>
    </c:if>
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
