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

<%-- select and display pages --%>
<form action="submitKeywords" method="post">
	<select multiple name="keywords">
		<c:forEach items="${allkeywords}" var="keyword">
			<option value="${keyword.id}">${keyword.getKeywordName()}</option>
		</c:forEach>
	</select>
	<input type="submit" value="submit">
</form>

<form method="post" action="inputParameters">
  <fieldset>
	<legend>Base Rate Coefficients</legend>
	<label class="heading" for="name">Min Value</label>
		<input type="text" name="name" id="name" size="10" autofocus /> <br />
	<label class="heading" for="name">Max Value</label>
		<input type="text" name="name" id="name" size="10" /><br />
	<input type="submit" value="Submit"/>
  </fieldset>
</form>
</body>
</html>
