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

</body>
</html>
