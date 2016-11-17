<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<div class="container">
	<fieldset class="surround">
	    <legend>Settings</legend>
	   	<form action="/ars/save_settings" method="POST">
	   		<label for="activeRatioWeight">Active Ratio Weight</label>
	   		<input id="active" type="text" name="activeRatioWeight"/><br>
	   		<label for="activeRatioWeight">Focus Ratio Weight</label>
	   		<input id="focus" type="text" name="focusRatioWeight"/>
	   		<button type="submit" class="btn btn-primary">Submit</button>
	   	</form>
  	</fieldset>
	
</div>
</body>

<script>
	$('#active').keypress(function() {
		var activeRatio = this.value;
		if(!activeRatio.includes(".")) {
			activeRatio = "." + activeRatio;
			this.value = activeRatio;
		}
		$('#focus').val(String(1 - parseFloat($('#active').val())));
	});
</script>
</html>