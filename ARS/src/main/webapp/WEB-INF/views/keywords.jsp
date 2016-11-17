<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<div class="container">
	<fieldset class="surround">
	    <legend>Keywords</legend>
	   	<table id="statsDisplayTable" class="table table-bordered ars-table">
		<thead>
			<tr>
				<th>Rank</th>
				<th>Page Title</th>
				<th>Focus Ratio</th>
				<th>Active Ratio</th>
				<th>Time Spent</th>
				<th>Expected Average Value</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${keywords}" var="keyword">
				<tr>
					<td>${keyword.rank}</td>
					<td>${keyword.title}</td>
					<td>${keyword.focusRatio}</td>
					<td>${keyword.activeRatio}</td>
					<td>${keyword.timeSpent} seconds</td>
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