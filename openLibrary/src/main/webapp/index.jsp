
<%@page import="model.UserModel"%>
<%@page import="model.BookModel"%>
<%@page import="bean.BookBean"%>
<%@page import="java.util.List"%>




<%@include file="jsp/header.jsp" %>


<div class="container mt-1 " style="flex-grow: 1">
<div class="text-center">
	 <img src="/openLibrary/images/openlib.svg" alt="SVP Logo" class="img-fluid" style="max-width: 300px;">	
</div>
    <h2 class="text-center mb-4">Available Books</h2>

    <div class="row">
        <%
        	String category=(String)request.getAttribute("category");
        	String keyword=(String)request.getAttribute("keyword");
        	
        	List<BookBean> bookList;
        	
        	if (category != null && !category.trim().isEmpty()) {
        	    bookList = BookModel.searchBooksByCategory(category); // You'll implement this in BookModel
        	}else if(keyword != null && !keyword.trim().isEmpty()){
        		bookList = BookModel.searchBooks(keyword);
        	}
        	else {
        	    bookList = BookModel.listAllBooks();
        	}
        
            if (bookList != null && !bookList.isEmpty()) {
                for (int i = 0; i < bookList.size(); i++) {
                    BookBean book = bookList.get(i);
        %>

        <div class="col-md-2 mb-3 ">
            <div class="card h-100 shadow-sm ">
            <a href="<%=MapUrl.BookDetailCtl +"?id="+ book.getId()%>">
				<img src="<%= request.getContextPath() + "/" + book.getCoverImagePath() %>" class="card-img-top" alt="Cover">  
			 </a>	
				    <div class="card-body text-wrap overflow-hidden">
                    <h5 class="card-title  text-truncate"><%= book.getName() %></h5>
                    <p class="card-text text-truncate" style="max-width: 100%;">Author: <strong><%= book.getAuthor() %></strong></p>
                    <p class="card-text mt-auto text-muted small">Uploaded by: <%=UserModel.getUserNameById(book.getUploadedBy()) %></p>
                  
                </div>
            </div>
        </div>

        <% 
                }
            } else { 
        %>
        <div class="col-12">
            <div class="alert alert-info text-center">No books found.</div>
        </div>
        <% } %>
    </div>
</div>
<%@ include file="jsp/footer.jsp"%>