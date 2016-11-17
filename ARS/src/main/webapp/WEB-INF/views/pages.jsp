<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>



<div class="container">
	<h1>Pages</h1>
	<table class = "table table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>Url</th>
				<th>Keywords</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pages}" var="page">
				<tr>
					<td>${page.id}</td>
					<td>${page.url}</td>
					<td>
						<c:forEach items="${page.keywords}" var="keyword">
							<span>${keyword.keywordName}</span>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>