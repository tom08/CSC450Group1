<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>
<c:if test="${needs_update}">
    <div class="alert alert-danger">
        Your data is more than 2 Hours old.
        <form:form method="POST" action="database/update">
            <input type="submit" value="Update"/>
        </form:form>
    </div>
</c:if>

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
