<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<div class="container">
	<fieldset class="surround">
	    <legend>Settings</legend>
	   	<form id="settingsForm" action="/ars/save_settings" method="POST">
	   		<div class="pull-left">
	   			<label for="min">Min Value</label> 
					<input type="text" name="min" id="minimum" size="10" autofocus/> <br>
				<label for="name">Max Value</label>
					<input type="text" name="max" id="maximum" size="10" /><br>
			</div>
	   		<label for="activeRatioWeight">Active Ratio Weight</label>
	   		<input id="active" type="text" name="activeRatioWeight" /><br>
	   		<label for="activeRatioWeight">Focus Ratio Weight</label>
	   		<input id="focus" type="text" name="focusRatioWeight"/> <br>
	   		
	   		<button type="submit" class="btn btn-primary">Submit</button>
	   	</form>
  	</fieldset>
	
</div>

<br />


</body>
</html>

