<%@ page import="src.main.java.com.book.model.User"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8"> </c:import>


<html>
<body>
	<form:form method="post"
		action="${pageContext.request.contextPath}/signup"
		modelAttribute="user" cssClass="form-horizontal">

		<label class="control-label" for="userName"><strong>userName</strong></label>
		<div class="controls">
			<form:input path="userName" cssClass="span3" cssErrorClass="error" />

		</div>
		<label class="control-label" for="nume"><strong>Nume</strong></label>
		<div class="controls">
			<form:input path="nume" cssClass="span3" cssErrorClass="error" />

		</div>
		<label class="control-label" for="prenume"><strong>Prenume</strong></label>
		<div class="controls">
			<form:input path="prenume" cssClass="span3" cssErrorClass="error" />

		</div>
		<label class="control-label" for="password"><strong>password</strong></label>
		<div class="controls">
			<form:input path="password" type="password" cssClass="span3"
				cssErrorClass="error" />
		</div>


		<input type="submit" class="btn btn-primary" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/signup"></a>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />


	</form:form>


</body>
</html>