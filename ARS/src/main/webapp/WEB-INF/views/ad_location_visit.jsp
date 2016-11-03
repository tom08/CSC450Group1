<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<div class="container">
	<h1 class="underline">Add Test Ad Location Visit</h1>
	
	<form:form method="POST" action="/ars/save_ad_location_visit" modelAttribute="adLocationVisit">
		<form:label path="pageId">Page Id:<form:input class="form-control" path="pageId"/></form:label><br>
	    <form:label path="pageLocation">Page Location:<form:input class="form-control" path="pageLocation"/></form:label><br>
	    <form:label path="totalSpent">Total Time Spent:<form:input class="form-control" path="totalSpent"/></form:label><br>
	    <form:label path="focusRatio">Focus Ratio:<form:input class="form-control" path="focusRatio"/></form:label><br>
	    <form:label path="activeRatio">Active Ratio:<form:input class="form-control" path="activeRatio"/></form:label><br>
	    <input class="btn btn-primary" type="submit" value="Submit"/>
	</form:form>

</div>

</body>
</html>