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

<c:forEach items="${adSpaces}" var="ad">
	<p>Id: ${ad.id}</p>
</c:forEach>

<form:form method="POST" action="save_ad_space" modelAttribute="adSpace">
	<form:label path="containingPage">Containing Page:</form:label>
    <form:input path="containingPage"/>
    
    <form:label path="timeSpent">Time Spent:</form:label>
    <form:input path="timeSpent"/>
    
    <form:label path="focusRatio">Focus Ratio:</form:label>
    <form:input path="focusRatio"/>
    
    <form:label path="activeRatio">Active Ratio:</form:label>
    <form:input path="activeRatio"/>
    
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
