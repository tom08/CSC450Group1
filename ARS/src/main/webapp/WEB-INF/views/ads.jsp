<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<div class="container">
	<fieldset class="surround">
	    <legend>Ads</legend>
	   	<table id="statsDisplayTable" class="table table-bordered ars-table">
		<thead>
			<tr>
				<th>Rank</th>
				<th>Page Title</th>
				<th>Ad Location</th>
				<th>Focus Ratio</th>
				<th>Active Ratio</th>
				<th>Time Spent</th>
				<th>Expected Average Ad Value</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ads}" var="ad">
				<tr>
					<td>${ad.rank}</td>
					<td>${ad.title}</td>
					<td>${ad.adLocation}</td>
					<td>${ad.focusRatio}</td>
					<td>${ad.activeRatio}</td>
					<td>${ad.timeSpent} seconds</td>
					<td>$${ad.dollarValue}</td>
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