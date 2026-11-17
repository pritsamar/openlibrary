<%@page import="utility.DataUtility"%>
<%@page import="bean.BookBean"%>
<%@page import="utility.ServletUtility"%>


<%@include file="header.jsp" %>

<div class="card-body">
          
          <h3 style="color:red"><%= ServletUtility.getErrorMessage(request) %></h3>
          <h3 style="color:green"><%= ServletUtility.getSuccessMessage(request) %></h3>
         
          
			<%
		    BookBean book = (BookBean) request.getAttribute("book");
		    boolean isEdit = (book != null);
			%>
			
            <input type="hidden" name="uploadedBy" value="<%= session.getAttribute("userId") %>">
            
    		
			
            <form action="<%=MapUrl.AddBookCtl%>" method="post" enctype="multipart/form-data">
           
           <% if (request.getAttribute("book") != null) {
    		 book = (bean.BookBean) request.getAttribute("book");
    		 
    		 if (book.getId() > 0) { %>
        	<input type="hidden" name="id" value="<%= book.getId() %>" />
			<%   }
				} %>

              <div class="form-group row mb-2">
              <div class="col-md-3"></div>
                <label for="" 
                  class="col-md-2 col-form-label text-md-right">Book Name<font color="red">*</font></label>
                <div class="col-md-4">
                  <input type="text"  name="name" class="form-control"  required maxlength="100" placeholder="Enter book name"
                     value="<%= isEdit ? book.getName() : ""%> " >
                    <font  color="red"></font>
                </div>
                <div class="col-md-3"></div>
              </div>
              <!-- ========================================= -->

              <div class="form-group row mb-2">
              <div class="col-md-3"></div>
                <label for="author"
                  class="col-md-2 col-form-label text-md-right">Author<font color="red">*</font></label>
                <div class="col-md-4">
                  <input type="text" id="author" class="form-control"  required maxlength="100" placeholder="Enter author(s) name"
                    name="author" value="<%= isEdit ? book.getAuthor() : ""  %>" >
                    <font color="red"> </font>
                </div>
                <div class="col-md-3"></div>
              </div>
              
              <!-- ========================================= -->
              
			 <div class="form-group row mb-2">
              <div class="col-md-3"></div>
                <label for="" 
                  class="col-md-2 col-form-label text-md-right">Categories<font color="red">*</font></label>
								    <%
				    String selectedCategory = (book != null) ? book.getCategory() : null;
				%>
				<select name="category" id="category" class="form-select-sm col-md-4" aria-label="Default select example">
				    <option disabled <%= (selectedCategory == null || selectedCategory.isEmpty()) ? "selected" : "" %>>-- Select an option --</option>
				    <option value="programming" <%= "programming".equals(selectedCategory) ? "selected" : "" %>>Programming</option>
				    <option value="technology" <%= "technology".equals(selectedCategory) ? "selected" : "" %>>Technology</option>
				    <option value="story" <%= "story".equals(selectedCategory) ? "selected" : "" %>>Story</option>
				    <option value="softskill" <%= "softskill".equals(selectedCategory) ? "selected" : "" %>>SoftSkills</option>
				</select>

                <div class="col-md-3"></div>
              </div>
              
              <!-- ========================================= -->
              <div class="form-group row mb-2">
              <div class="col-md-3"></div>
                <label for="" 
                  class="col-md-2 col-form-label text-md-right">Upload Cover image<font color="red">*</font></label>
                <div class="col-md-4">
                <% if (isEdit && book.getCoverImagePath() != null) { %>
        			<img src="<%= book.getCoverImagePath() %>" alt="Cover Image" style="max-height:100px;"><br>
   				 <% } %>
                  <input type="file" name="coverImage" class="form-control" accept="image/*" <%=(isEdit)?"":"required" %>>
                    <font  color="red"></font>
                </div>
                <div class="col-md-3"></div>
              </div>
              <!-- ========================================= -->
              <div class="form-group row mb-2">
              <div class="col-md-3"></div>
                <label for="" 
                  class="col-md-2 col-form-label text-md-right">Book file (PDF)<font color="red">*</font></label>
                <div class="col-md-4">
                 <% if (isEdit && book.getFilePath() != null) { %>
        			<p><a href="<%= book.getFilePath() %>">View/Download</a></p>
   				 <% } %>
                  <input type="file" name="bookFile" class="form-control" accept="application/pdf" <%=(isEdit)?"":"required"%> >
                    <font  color="red"></font>
                </div>
                <div class="col-md-3"></div>
              </div>
              
               
              <!-- ========================================= -->
              <div class="form-group row mb-2">
              <div class="col-md-3"></div>
                <label for="" 
                  class="col-md-2 col-form-label text-md-right">Description<font color="red">*</font></label>
                <div class="col-md-4">
                 <textarea rows="10" cols="62" name="description"  required maxlength="1000" placeholder="Short description about the book..."><%= isEdit ? book.getDescription() : "" %></textarea>
                    <font  color="red"></font>
                </div>
                <div class="col-md-3"></div>
              </div>
              
              
              <!-- ========================================= -->           
         

              <div class="col-md-6 offset-md-6">
                <input type="submit" class="btn btn-primary"  value="<%= isEdit ? "Update Book" : "Add Book" %>">
              </div>
            </form>
          </div>


<%@include file="footer.jsp" %>>
