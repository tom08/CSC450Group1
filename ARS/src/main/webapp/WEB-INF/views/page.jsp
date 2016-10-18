<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<h1 class="underline">Add Test Page</h1>

<form:form method="POST" action="save_page" modelAttribute="page">
	<form:label path="url">URL:</form:label>
    <form:input path="url"/>
    
    <input type="submit" value="Submit"/>
</form:form>