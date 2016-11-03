<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>



<div class="container">
	<h1>Add Test Page</h1>
	<form:form method="POST" action="/ars/save_page" modelAttribute="page">
		<form:label path="url">URL:<form:input class="form-control" path="url"/></form:label>
	    
	    <input class="btn btn-primary" type="submit" value="Submit"/>
	</form:form>
</div>
</body>
</html>