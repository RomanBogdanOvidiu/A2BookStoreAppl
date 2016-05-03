<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>
<html>
<body>
	<h1>
		<center>${message}</center>
	</h1>



	<sec:authorize access="hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')">
		<!-- For login user -->
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
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>

	</sec:authorize>

	<sec:authorize
		access="!hasRole('ROLE_USER') && !hasRole('ROLE_ADMIN') ">
		<a href="${pageContext.request.contextPath}/login">Login</a>

	</sec:authorize>
	
	<sec:authorize
		access="hasRole('ROLE_USER') ">
		<center>
			<a href='${pageContext.request.contextPath}/bookStore'
				class="btn btn-primary">Check our books</a>
		</center>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN') ">
		<center>
			<a href='${pageContext.request.contextPath}/bookStore'
				class="btn btn-primary">BookStore</a>
		</center>

		<center>
			<a href="${pageContext.request.contextPath}/clientlist"
				class="btn btn-primary">Reports</a>
		</center>
		<center>
			<a href='${pageContext.request.contextPath}/signup'
				class="btn btn-primary">Sign Up</a>
		</center>

		<center>
			<a href='${pageContext.request.contextPath}/admin'
				class="btn btn-primary">Admin page</a>
		</center>
	</sec:authorize>



</body>
</html>

