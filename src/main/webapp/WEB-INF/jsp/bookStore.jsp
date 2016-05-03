<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8"> 
    <c:param name="title" value="Book List" />
    <c:param name="body">
        <p>
        
             <a href='${pageContext.request.contextPath}/bookStore/newBook' class="btn btn-info">Add a new book</a>
        </p>
        <table
            class="table table-striped table-bordered table-condensed">
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
                 <th>Quantity</th>
                <th>Price</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${(book.title)}</td>
                    <td>${(book.author)}</td>
                    <td> ${(book.genre)}</td>
            	 <td>${(book.quantity)}</td>
                    <td>${(book.price)}</td>
                    
                    <td><a
                        href='${pageContext.request.contextPath}/bookStore/editBook/${book.title}'
                        class="btn btn-primary">Update</a> <a
                        href='${pageContext.request.contextPath}/admin/bookStore/sell/${book.title}'
                        class="btn">Sell Book</a> <a
                        href='${pageContext.request.contextPath}/admin/bookStore/delete/${book.title}'
                        class="btn">Remove</a> 
                        </td>
                        
                      
                </tr>
            </c:forEach>
        </table>

      
        
    </c:param>
</c:import>

