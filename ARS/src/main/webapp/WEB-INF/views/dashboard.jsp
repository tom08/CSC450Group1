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

</body>
</html>
