package controller;

public interface MapUrl {
	
	public String APP_CONTEXT = "/openLibrary";
	public String PAGE_FOLDER="/jsp";
	
	public String loginView = PAGE_FOLDER+"/loginView.jsp";
	public String signUpView = PAGE_FOLDER+"/signUpView.jsp";
	public String addBookView = PAGE_FOLDER+"/addBookView.jsp";
	public String bookDetailView = PAGE_FOLDER+"/bookDetailView.jsp";
	
	
	public String SignUpCtl=APP_CONTEXT+"/SignUpCtl"; 
	public String LoginCtl=APP_CONTEXT+"/LoginCtl"; 
	public String AddBookCtl=APP_CONTEXT+"/AddBookCtl"; 
	public String BookDetailCtl=APP_CONTEXT+"/BookDetailCtl"; 
	public String DeleteBookCtl=APP_CONTEXT+"/DeleteBookCtl"; 
	public String BookSearchCtl=APP_CONTEXT+"/BookSearchCtl"; 
	

}
