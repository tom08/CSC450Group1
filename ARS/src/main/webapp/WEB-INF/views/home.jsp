<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>

<div class="container">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Page URL</th>
				<th>Keywords</th>
				<th>Delete</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${pages}" var="page">
				<tr>
					<td>${page.url}</td>
					<td><c:forEach items="${page.keywords}" var="keyword">
						<span>${keyword.keywordName}</span>
					</c:forEach></td>
					<td><button onclick="deletePage(${page.id})" class="btn btn-default glyphicon glyphicon-remove-circle"></button></td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>

</div>
<form method="post" action="" id="deletePage">
</form>

<script>
	function deletePage(id) {
		var deletePageForm = document.getElementById("deletePage");
		deletePageForm.action = "delete_page/" + id;
		deletePageForm.submit();
	}
</script>

</body>
</html>
