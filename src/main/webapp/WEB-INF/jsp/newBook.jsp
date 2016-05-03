<%@ page import="src.main.java.com.book.model.Book"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8"> </c:import>


<html>
<body>
	<form:form method="post"
		action="${pageContext.request.contextPath}/bookStore/newBook"
		modelAttribute="books" cssClass="form-horizontal">

		<label class="control-label" for="title"><strong>Title</strong></label>
		<div class="controls">
			<form:input path="title" cssClass="span3" cssErrorClass="error" />

		</div>
		<label class="control-label" for="author"><strong>Author</strong></label>
		<div class="controls">
			<form:input path="author" cssClass="span3" cssErrorClass="error" />

		</div>
		<label class="control-label" for="genre"><strong>Genre</strong></label>
		<div class="controls">
			<form:input path="genre" cssClass="span3" cssErrorClass="error" />

		</div>
		
		<label class="control-label" for="quantity"><strong>Quantity</strong></label>
		<div class="controls">
			<form:input path="quantity" cssClass="span3" cssErrorClass="error" />
		</div>
		
		<label class="control-label" for="price"><strong>Price</strong></label>
		<div class="controls">
			<form:input path="price" cssClass="span3" cssErrorClass="error" />
		</div>


		<input type="submit" class="btn" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/bookStore/newBook"></a>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />



	</form:form>


</body>
</html>