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
<form method="post" action="" id="deletePage">
</form>

<script>
	function deletePage(id) {
		var deletePageForm = document.getElementById("deletePage");
		deletePageForm.action = "delete_page/" + id;
		deletePageForm.submit();
	}
</script>


<form method="post" action="inputParameters">
  <fieldset>
	<legend>Base Rate Coefficients</legend>
	<label class="heading" for="name">Min Value</label>
		<input type="text" name="name" id="name" size="10" autofocus /> <br />
	<label class="heading" for="name">Max Value</label>
		<input type="text" name="name" id="name" size="10" /><br />
	<input type="submit" value="Submit"/>
  </fieldset>
</form>

</body>
</html>