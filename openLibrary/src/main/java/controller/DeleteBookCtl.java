package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BookModel;
import utility.ServletUtility;

/**
 * Servlet implementation class DeleteBookCtl
 */
@WebServlet("/DeleteBookCtl")
public class DeleteBookCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        long bookId = Long.parseLong(request.getParameter("id"));
        String appPath = request.getServletContext().getRealPath("");

        try {
            int status = BookModel.deleteBook(bookId, appPath);

            if (status > 0) {
                ServletUtility.setSuccessMessage("Book deleted successfully.", request);
            } else {
                ServletUtility.setErrorMessage("Failed to delete book.", request);
            }

        } catch (Exception e) {
            e.printStackTrace();
            ServletUtility.setErrorMessage("Error: " + e.getMessage(), request);
        }

        // Redirect to list or homepage
        request.getRequestDispatcher("").forward(request, response);
    }

}
