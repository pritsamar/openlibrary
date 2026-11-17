package controller;

import java.io.IOException;
import java.util.List;
import bean.BookBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BookModel;
import utility.ServletUtility;

/**
 * Servlet implementation class BookSearchCtl
 */
@WebServlet("/BookSearchCtl")
public class BookSearchCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("category");
		String keyword = request.getParameter("keyword");
		

	    List<BookBean> bookList;
	    if (category != null && !category.trim().isEmpty()) {
	        bookList = BookModel.searchBooksByCategory(category);
	        request.setAttribute("category", category);
	    }else if(keyword != null && !keyword.trim().isEmpty()){
	    	bookList = BookModel.searchBooks(keyword);
	    	request.setAttribute("keyword", keyword);
	    }
	    else {
	        bookList = BookModel.listAllBooks();
	    }

	    request.setAttribute("bookList", bookList);
	   ServletUtility.forward("/", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
