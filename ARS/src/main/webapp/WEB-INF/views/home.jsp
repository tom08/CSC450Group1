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
					<td><button onclick="updatePage(${page.id})" class="btn btn-default glyphicon glyphicon-remove-circle"></button></td>
					<td><a href="test_page/${page.id}" class="btn btn-default glyphicon glyphicon-remove-circle"></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%-- delete pags --%>
<form method="post" action="" id="deletePage">
</form>

<%-- select and display pages --%>
<form>
	<select name="test">
		<c:forEach items="${allkeywords}" var="keyword">

			<option value="${keyword.id}">${keyword.getKeywordName()}</option>

		</c:forEach>
	</select>
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
