
<%@ include file="header.jsp"%>
<main class="login-form">
	<div class="cotainer custom-margin">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header">
						Sign Up
						<h6 style="color: red;"></h6>
						<h6 style="color: green;"></h6>
					</div>

					<div class="card-body">

						<%
						if (request.getAttribute("errorMsg") != null) {
						%>
						<div class="alert alert-danger">
							<%=request.getAttribute("errorMsg")%>
						</div>
						<%
						} else if (request.getAttribute("successMsg") != null) {
						%>
						<div class="alert alert-success">
							<%=request.getAttribute("successMsg")%>
						</div>
						<%
						}
						%>




						<form action="<%=MapUrl.SignUpCtl%>" method="post">

							<input type="hidden" name="id" value="">

							<div class="form-group row mb-2">
								<div class="col-md-3"></div>
								<label for="email_address"
									class="col-md-2 col-form-label text-md-right">username<font
									color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" id="login" class="form-control"
										placeholder="Enter username" name="username" value="" required
										maxlength="50"> <font color="red"></font>
								</div>
								<div class="col-md-3"></div>
							</div>

							<div class="form-group row mb-2">
								<div class="col-md-3"></div>
								<label for="email_address"
									class="col-md-2 col-form-label text-md-right">email<font
									color="red">*</font></label>
								<div class="col-md-4">
									<input type="email" id="login" class="form-control"
										placeholder="Enter email" name="email" value="" required
										maxlength="100"> <font color="red"></font>
								</div>
								<div class="col-md-3"></div>
							</div>

							<div class="form-group row mb-2">
								<div class="col-md-3"></div>
								<label for="password"
									class="col-md-2 col-form-label text-md-right">Password<font
									color="red">*</font></label>
								<div class="col-md-4">
									<input type="password" id="password" class="form-control"
										placeholder="Enter Password" name="password" value="" required
										maxlength="200"> <font color="red"> </font>
								</div>
								<div class="col-md-3"></div>
							</div>



							<div class="col-md-6 offset-md-6">
								<input type="submit" class="btn btn-primary" name="operation"
									value="Sign Up">
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</main>
	<%@ include file="footer.jsp"%>
