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
				<th>Average Focus Ratio</th>
				<th>Average Active Ratio</th>
				<th>Expected Dollar Value</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${keywords}" var="keyword">
				<tr>
					<td><fmt:formatNumber value="${keyword.value * 10}" maxFractionDigits="2"/></td>
					<td>${keyword.keywordName}</td>
					<td><fmt:formatNumber value="${keyword.focusRatio}" maxFractionDigits="2"/></td>
					<td><fmt:formatNumber value="${keyword.activeRatio}" maxFractionDigits="2"/></td>
					<td>$<fmt:formatNumber value="${keyword.dollarValue}" maxFractionDigits="2"/></td>
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