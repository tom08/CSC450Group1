<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>



<div class="container">
	<h1>Add Test Page</h1>
	<form:form method="POST" action="save_page" modelAttribute="page">
		<form:label path="url">URL:<form:input class="form-control" path="url"/></form:label>
	    <form:label path="url">Keywords:<form:select multiple="mulitple" class="form-control" path="keywords">
	    	<c:forEach items="${availableKeywords}" var="keyword">
	    		<form:option value="${keyword}">${keyword.keywordName}</form:option>
	    	</c:forEach>
	    </form:select>
	    </form:label>
	    
	    <input class="btn btn-primary" type="submit" value="Submit"/>
	</form:form>
</div>
</body>
</html>