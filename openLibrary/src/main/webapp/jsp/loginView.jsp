
<%@page import="utility.ServletUtility"%>


  <%@ include file="header.jsp"%>
  <main class="login-form">
  <div class="cotainer custom-margin">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">
            Login 
            <h3 style="color:red"><%= ServletUtility.getErrorMessage(request) %></h3>
          <h3 style="color:green"><%= ServletUtility.getSuccessMessage(request) %></h3>
          </div>

          <div class="card-body">
          
          

            <form action="<%=MapUrl.LoginCtl%>" method="post">

              <div class="form-group row mb-2">
              <div class="col-md-3"></div>
                <label for="email_address" 
                  class="col-md-2 col-form-label text-md-right">Email Id<font color="red">*</font></label>
                <div class="col-md-4">
                  <input type="text" id="email"  class="form-control" placeholder="Enter Email Id"
                    name="email" value=""  required>
                    <font  color="red"></font>
                </div>
                <div class="col-md-3"></div>
              </div>

              <div class="form-group row mb-2">
              <div class="col-md-3"></div>
                <label for="password"
                  class="col-md-2 col-form-label text-md-right">Password<font color="red">*</font></label>
                <div class="col-md-4">
                  <input type="password" id="password" class="form-control" placeholder="Enter Password"
                    name="password" value=""  >
                    <font color="red"> </font>
                </div>
                <div class="col-md-3"></div>
              </div>



              <div class="col-md-6 offset-md-6">
                <input type="submit" class="btn btn-primary" name="operation" value="Login">
                <a href="" class="btn btn-link"> Forgot Your Password? </a>
              </div>
            </form>
          </div>

        </div>
      </div>
    </div>
  </div>
  </main>
  
    <%@ include file="footer.jsp"%>
 