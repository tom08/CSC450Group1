<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>
<c:if test="${needsUpdate}">
    <div class="alert alert-danger">
        <h3>Your data is out of date</h3>
        <form:form method="POST" action="database/update">
            <input type="submit" value="Update"/>
        </form:form>
    </div>
</c:if>

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
</div>

</body>
</html>
