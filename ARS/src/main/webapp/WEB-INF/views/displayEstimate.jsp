<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>
<h2 class="text-center">Results</h2>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h3 class="text-center">Estimated Ad Value</h3>
            <p class="text-center">$<fmt:formatNumber value="${ad_value}" maxFractionDigits="2"/></p>
            <a href="/ars/estimate">Get another estimate!</a>
        </div>
        <div class="col-md-6">
            <h3 class="text-center">Selected Keywords</h3>
            <c:forEach items="${keywords}" var="keyword">
                <div class="alert alert-info">
                    ${keyword.getKeywordName()}
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
