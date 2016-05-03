<%@ page import="src.main.java.com.book.model.User"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8"> </c:import>


<html>
<body>
	<form:form method="post"
		action="${pageContext.request.contextPath}/admin/edituser"
		modelAttribute="user" cssClass="form-horizontal">


		<label class="control-label" for="nume"><strong>First Name</strong></label>
		<div class="controls">
		<form:input path="nume" cssClass="span3" cssErrorClass="error" />
		</div>
		<label class="control-label" for="prenume"><strong>Last Name</strong></label>
		<div class="controls">
		<form:input path="prenume" cssClass="span3" cssErrorClass="error" />
		</div>
		
		<label class="control-label" for="userName"><strong>Username</strong></label>
		<div class="controls">
		<form:input path="userName" cssClass="span3" cssErrorClass="error" />
		
		</div>
		
		

		

		<input type="submit" class="btn" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/admin/edituser"></a>
			
			
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<form:hidden path="password" />

	</form:form>
	
</body>
</html>