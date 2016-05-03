<%@page session="true"%><%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8"> </c:import>

<html>
<body>


		<table class="table table-striped table-bordered table-condensed">
			<tr>
				<th>UserName</th>

				<th>FirstName</th>

				<th>LastName</th>

				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${(user.userName)}</td>
					<td>${(user.nume)}</td>
					<td>${(user.prenume)}</td>

					<td><a
						href='${pageContext.request.contextPath}/admin/edituser/${user.userName}'
						class="btn btn-primary">edit</a> <a
						href='${pageContext.request.contextPath}/admin/delete/${user.userName}'
						class="btn">delete</a>
				</tr>
			</c:forEach>
		</table>





	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>

</body>
</html>


