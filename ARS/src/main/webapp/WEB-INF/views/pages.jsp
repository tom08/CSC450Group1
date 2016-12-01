<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>



<div class="container">
	<fieldset class="surround">
	    <legend>Pages</legend>
	   	<table id="statsDisplayTable" class="table table-bordered ars-table">
		<thead>
			<tr>
				<th>ID</th>
				<th>URL</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pages}" var="page">
				<tr>
					<td>${page.id}</td>
					<td>${page.url}</td>
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