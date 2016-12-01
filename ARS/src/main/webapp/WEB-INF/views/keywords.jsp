<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<div class="container">
	<fieldset class="surround">
	    <legend>Keywords</legend>
	   	<table id="statsDisplayTable" class="table table-bordered ars-table">
		<thead>
			<tr>
				<th>Rank</th>
				<th>Keyword Name</th>
				<th>Focus Ratio</th>
				<th>Active Ratio</th>
				<th>Expected Dollar Value</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${keywords}" var="keyword">
				<tr>
					<td>${keyword.value}</td>
					<td>${keyword.keywordName}</td>
					<td>${keyword.focusRatio}</td>
					<td>${keyword.activeRatio}</td>
					<td>$${keyword.dollarValue}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  	</fieldset>
	
</div>
</body>

<script>
	$('#statsDisplayTable').DataTable();
</script>
</html>