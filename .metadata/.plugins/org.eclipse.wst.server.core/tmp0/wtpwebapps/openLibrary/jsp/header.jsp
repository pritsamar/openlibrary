<%@page import="controller.MapUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height:100%">
<head>
<meta charset="UTF-8">
<title>OpenLibrary</title>
<link rel="icon" type="image/png" href="/openLibrary/images/icon.png" />
<link rel="stylesheet" href="/openLibrary/css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>


</head>
<body style="min-height:100%;
	display:flex;
	flex-direction: column;">
	<%
	String userSession = (String) session.getAttribute("user");
	%>



	<div class="container ">
		<header class="p-3 bg-dark text-white">
			<div class="container">
				<div
					class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">





					<ul
						class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
						<li><a href="/openLibrary/index.jsp"
							class="nav-link px-2 text-white">Home</a></li>
						<li><div class="dropdown">
								<a class="nav-link px-2 text-white dropdown-toggle" href="#"
									role="button" id="dropdownMenuLink" data-bs-toggle="dropdown"
									aria-expanded="false"> Categories </a>

								<ul class="dropdown-menu bg-dark"
									aria-labelledby="dropdownMenuLink">
									<li><a class="dropdown-item text-white"
										href="<%=MapUrl.BookSearchCtl%>?category=programming">Programming</a></li>
									<li><a class="dropdown-item text-white"
										href="<%=MapUrl.BookSearchCtl%>?category=technology">Technology</a></li>
									<li><a class="dropdown-item text-white"
										href="<%=MapUrl.BookSearchCtl%>?category=story">Story</a></li>
									<li><a class="dropdown-item text-white"
										href="<%=MapUrl.BookSearchCtl%>?category=softskill">SoftSkill</a></li>
								</ul>
							</div></li>

						<%
						if (userSession != null) {
						%>
						<li><a href="<%=MapUrl.AddBookCtl%>"
							class="nav-link px-2 text-white">Upload</a></li>
						<li><a href="/openLibrary/jsp/about.jsp" class="nav-link px-2 text-white">About</a></li>
						<%
						}
						%>
					</ul>


					<form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3"
						action="<%=MapUrl.BookSearchCtl %>" method="get">
						<input type="search" class="form-control form-control-dark"
							name="keyword" placeholder="Search..." aria-label="Search">
					</form>



					<div class="text-end">
						<%
						if (userSession != null) {
						%>
						<span class="text-white me-2">Welcome, <%=userSession%>!
						</span> <a class="text-success customeLink"
							href="<%=MapUrl.LoginCtl + "?operation=logout"%>">
							<button type="button" class="btn btn-danger">Logout</button>
						</a>
						<%
						} else {
						%>
						<a class="text-success customeLink" href="<%=MapUrl.LoginCtl%>">
							<button type="button" class="btn btn-outline-light me-2">Login</button>
						</a> <a class="text-success customeLink" href="<%=MapUrl.SignUpCtl%>">
							<button type="button" class="btn btn-warning">Sign Up</button>
						</a>
						<%
						}
						%>
					</div>

				</div>
			</div>
		</header>
	</div>
