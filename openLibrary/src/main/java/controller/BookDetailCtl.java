package controller;

import java.io.IOException;
import bean.BookBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BookModel;
import model.UserModel;
import utility.ServletUtility;

/**
 * Servlet implementation class BookDetailCtl
 */
@WebServlet("/BookDetailCtl")
public class BookDetailCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            long bookId = Long.parseLong(request.getParameter("id"));
            BookBean book = BookModel.findById(bookId);
            String uploaderName = UserModel.getUserNameById(book.getUploadedBy());

            request.setAttribute("book", book);
            request.setAttribute("uploaderName", uploaderName);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to load book details.");
        }

       ServletUtility.forward(MapUrl.bookDetailView, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
