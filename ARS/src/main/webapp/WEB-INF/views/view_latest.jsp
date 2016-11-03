<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>



<div class="container">
	<h1>View Latest Ad Location Visit</h1>
	<table class="table">
		<thead>
		<tr>
			<th>ID</th>
			<th>Created At</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>${adLV.id}</td>
			<td>${adLV.createdAt}</td>
		</tr>
		</tbody>
	</table>
</div>
</body>
</html>