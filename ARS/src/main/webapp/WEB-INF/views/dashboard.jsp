<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<div class="container">
	<fieldset class="surround">
	    <legend>Status</legend>
	   	<span>Last Updated Ad Visit Date: ${lastUpdatedDate}</span>
  	</fieldset>
  	
  	<fieldset class="surround">
	    <legend>Data</legend>
	   	<p>Pages on Record: ${numPages}</p>
	   	<p>Ads on Record: ${numAds}</p>
	   	<p>Ad Visits Tracked: ${numAdsTracked}</p>
  	</fieldset>
	<h1>Add Test Keyword</h1>
	<form:form method="POST" action="save_keyword" modelAttribute="keyword">
		<form:label path="keywordName">Keyword Name:<form:input class="form-control" path="keywordName"/></form:label>
	    <input class="btn btn-primary" type="submit" value="Submit"/>
	</form:form>
	
	<h1>Add Test Page</h1>
	<form:form method="POST" action="save_page" modelAttribute="page">
		<form:label path="url">URL:<form:input class="form-control" path="url"/></form:label>
	    <input class="btn btn-primary" type="submit" value="Submit"/>
	</form:form>
</div>

</body>
</html>
