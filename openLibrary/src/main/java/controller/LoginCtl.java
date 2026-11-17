package controller;

import java.io.IOException;
import bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;
import utility.ServletUtility;

/**
 * Servlet implementation class LoginCtl
 */
@WebServlet("/LoginCtl")
public class LoginCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		String op=request.getParameter("operation");
		if(op!= null && op.equals("logout")) {
			session.invalidate();
			ServletUtility.setSuccessMessage("LogoutSuccessfully", request);	
		}
	ServletUtility.forward(MapUrl.loginView, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserBean user=new UserBean();
		
		String email=request.getParameter("email");
		String password= request.getParameter("password");
		
		 user = UserModel.UserLogin(email,password);
		 HttpSession session=request.getSession(true);
		 
		 
		 if(user != null) {
			 session.setAttribute("user", user.getUsername());
			 session.setAttribute("userId", user.getId());
			 RequestDispatcher rd=request.getRequestDispatcher("/");
			 rd.forward(request, response);
	 }else {
         ServletUtility.setErrorMessage("Invalid User", request);
         ServletUtility.forward(MapUrl.loginView, request, response);
     }
	}

}
