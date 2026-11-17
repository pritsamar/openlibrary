
<%@ page import="bean.BookBean"%>
<%
BookBean book = (BookBean) request.getAttribute("book");
String uploaderName = (String) request.getAttribute("uploaderName");
Long loggedInUserId = (Long) session.getAttribute("userId");
%>
<%@ include file="header.jsp"%>
<div class="container ">






	<div class="card mb-4 ">

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-2 mt-2">
				<img
					src="uploads/cover/<%=book.getCoverImagePath().substring(book.getCoverImagePath().lastIndexOf("/") + 1)%>"
					class="card-img-top" alt="Cover Image">

			</div>

			<div class="col-md-6 mx-1">
				<div class="card-body">
					<h3 class="card-title"><%=book.getName()%></h3>
					<h5 class="card-subtitle mb-2 text-muted">
						By
						<%=book.getAuthor()%></h5>
					<p class="card-text">
						<strong>Category:</strong>
						<%=book.getCategory()%></p>

					<%
					if (loggedInUserId != null && loggedInUserId.equals(book.getUploadedBy())) {
					%>
					<div class="mt-3">
						<a href="<%=MapUrl.AddBookCtl + "?id=" + book.getId()%>"
							class="btn btn-warning mx-1 mb-1">Edit</a>
							
							 <form action="<%=MapUrl.DeleteBookCtl %>" method="post" onsubmit="return confirm('Are you sure you want to delete this book?');">
    						<input type="hidden" name="id" value="<%=book.getId()%>" />
   							 <button type="submit" class="btn btn-danger">Delete</button>
							</form>

					</div>
					<%
					}
					%>

				</div>
				<div class="col-md-2"></div>
			</div>

			<div class="card-body">
				<p class="card-text mx-5 "><%=book.getDescription()%></p>
				<p class="card-text mx-5">
					<strong>Uploaded by:</strong>
					<%=uploaderName%>
				</p>
				<a
					href="uploads/books/<%=book.getFilePath().substring(book.getFilePath().lastIndexOf("/") + 1)%>"
					download="<%=book.getName()%>.pdf" class="btn btn-primary"
					 onclick="return confirm('Do you want to download this book?');"
					>Download Book</a> <a
					href="<%=MapUrl.APP_CONTEXT%>" class="btn btn-secondary">Back
					to Library</a>
			</div>
		</div>

	</div>
</div>
<%@ include file="footer.jsp"%>

