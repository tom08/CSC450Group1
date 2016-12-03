<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<div class="container">
	<fieldset class="surround">
	    <legend>Settings</legend>
	    <c:if test="${errorMessage ne null}"><p class="alert alert-danger">${errorMessage}</p></c:if>
	   	<form id="settingsForm" action="/ars/save_settings" method="POST">
	   		<div class="pull-left">
	   			<label for="min">Min Value</label> 
					<input type="text" name="min" id="minimum" value="${minValue}" size="10" autofocus/> <br>
				<label for="name">Max Value</label>
					<input type="text" name="max" id="maximum" value="${maxValue}" size="10" /><br>
			</div>
	   		<label for="activeRatioWeight">Active Ratio Weight</label>
	   		<input id="active" type="text" value="${active}" name="activeRatioWeight" /><br>
	   		<label for="activeRatioWeight">Focus Ratio Weight</label>
	   		<input id="focus" type="text" value="${focus}" name="focusRatioWeight"/> <br>
	   		
	   		<button type="submit" class="btn btn-primary">Submit</button>
	   	</form>
  	</fieldset>
	
</div>

<br />


</body>
</html>

