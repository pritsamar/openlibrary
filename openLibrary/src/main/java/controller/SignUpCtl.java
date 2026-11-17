package controller;

import java.io.IOException;
import bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserModel;
import utility.DataUtility;

/**
 * Servlet implementation class SignUpCtl
 */
@WebServlet("/SignUpCtl")
public class SignUpCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=request.getRequestDispatcher(MapUrl.signUpView);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserBean user=new UserBean();
		user.setId(DataUtility.getLong(request.getParameter("id")));
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		
		
		 try {
		         UserModel.addUser(user);
		        request.setAttribute("successMsg", "Signup successful! Please log in.");
		    } catch (Exception e) {
		        if (e.getMessage().contains("Email already exists")) {
		            request.setAttribute("errorMsg", "Email already exists. Please use a different email.");
		        } else {
		            request.setAttribute("errorMsg", "An error occurred during signup. Please try again.");
		            e.printStackTrace();
		        }
		    }
			
		 request.getRequestDispatcher(MapUrl.signUpView).forward(request, response);		
		
	}

}
