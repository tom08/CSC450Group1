<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>



<div class="container">
	<fieldset class="surround">
	    <legend>Pages</legend>
	   	<table id="statsDisplayTable" class="table table-bordered ars-table">
		<thead>
			<tr>
				<th>Rank</th>
				<th>Page Title</th>
				<th>Focus Ratio</th>
				<th>Active Ratio</th>
				<th>Time Spent</th>
				<th>Expected Average Ad Value</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pages}" var="page">
				<tr>
					<td>${page.rank}</td>
					<td>${page.title}</td>
					<td>${page.focusRatio}</td>
					<td>${page.activeRatio}</td>
					<td>${page.timeSpent} seconds</td>
					<td>$${page.dollarValue}</td>
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