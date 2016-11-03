<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>


<div class="container">
	<h1>Add Test Keyword</h1>
	<form:form method="POST" action="save_keyword" modelAttribute="keyword">
		<form:label path="keywordName">Keyword Name:<form:input class="form-control" path="keywordName"/></form:label>
	    <input class="btn btn-primary" type="submit" value="Submit"/>
	</form:form>
</div>
</body>
</html>