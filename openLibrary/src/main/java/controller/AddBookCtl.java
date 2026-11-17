package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import bean.BookBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.BookModel;
import utility.ServletUtility;

@WebServlet("/AddBookCtl")
@MultipartConfig
public class AddBookCtl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Upload sub-directories inside webapp/uploads/
    private static final String UPLOAD_DIR = "uploads";
    private static final String COVER_DIR = "cover";
    private static final String BOOK_DIR = "books";

    public AddBookCtl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 String bookId = request.getParameter("id");
         
         try {
			// If there's an id, we're editing an existing book
			 if (bookId != null && !bookId.isEmpty()) {
			     // Fetch book details by id
			     BookBean book = BookModel.findById(Integer.parseInt(bookId));
			     
			     if (book != null) {
			         // Set book data in request scope to populate the form
			         request.setAttribute("book", book);
			     } else {
			         // If the book doesn't exist, you can handle it as an error
			         request.setAttribute("errorMessage", "Book not found.");
			     }
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        RequestDispatcher rd = request.getRequestDispatcher(MapUrl.addBookView);
        rd.forward(request, response);
    }

    
    
    
    
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // false prevents new session creation
        Long uploadedBy = (session != null) ? (Long) session.getAttribute("userId") : null;

        if (uploadedBy == null) {
            ServletUtility.setErrorMessage("You must be logged in to upload a book.", request);
            request.getRequestDispatcher(MapUrl.addBookView).forward(request, response);
            return;
        }

        BookBean book = new BookBean();

        try {
            String idStr = request.getParameter("id");
            boolean isEdit = (idStr != null && !idStr.trim().isEmpty());

            if (isEdit) {
                book.setId(Long.parseLong(idStr));
            }

            // Read text fields
            book.setName(getValueFromPart(request.getPart("name")));
            book.setAuthor(getValueFromPart(request.getPart("author")));
            book.setCategory(getValueFromPart(request.getPart("category")));
            book.setDescription(getValueFromPart(request.getPart("description")));
            book.setUploadedBy(uploadedBy != null ? uploadedBy : 0);

            // === Check for new uploads ===
            Part coverImagePart = request.getPart("coverImage");
            Part bookFilePart = request.getPart("bookFile");

            String coverImagePath = null;
            String bookFilePath = null;

            if (coverImagePart != null && coverImagePart.getSize() > 0) {
                coverImagePath = saveFile(coverImagePart, UPLOAD_DIR + "/" + COVER_DIR, request);
            }

            if (bookFilePart != null && bookFilePart.getSize() > 0) {
                bookFilePath = saveFile(bookFilePart, UPLOAD_DIR + "/" + BOOK_DIR, request);
            }

            // If editing and no new files uploaded, retain old paths
            if (isEdit) {
                BookBean existing = BookModel.findById(book.getId());

                if (existing != null) {
                    if (coverImagePath == null) {
                        coverImagePath = existing.getCoverImagePath();
                    }
                    if (bookFilePath == null) {
                        bookFilePath = existing.getFilePath();
                    }
                }
            }

            book.setCoverImagePath(coverImagePath);
            book.setFilePath(bookFilePath);

            int status;
            if (isEdit) {
                status = BookModel.updateBook(book);
            } else {
                status = BookModel.addBook(book);
            }

            if (status > 0) {
                ServletUtility.setSuccessMessage("Book saved successfully!", request);
            } else {
                ServletUtility.setErrorMessage("Failed to save book.", request);
            }

        } catch (Exception e) {
            e.printStackTrace();
            ServletUtility.setErrorMessage("Exception occurred: " + e.getMessage(), request);
        }

        request.getRequestDispatcher(MapUrl.addBookView).forward(request, response);
    }

    
    
    
    
    
    
    
    

    // Utility: Get value from text field part
    private String getValueFromPart(Part part) throws IOException {
        if (part != null) {
            return new String(part.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim();
        }
        return "";
    }

    /**
     * 
     *sava file to folder
     */
    
    // Utility: Save uploaded file and return relative path
    private String saveFile(Part filePart, String relativeUploadDir, HttpServletRequest request) throws IOException {

    	if (filePart == null || filePart.getSize() == 0) {

    	return null;

    	}

    	  

    	// Get original filename

    	String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

    	  

    	// Create a unique file name

    	String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;

    	  

    	// Resolve absolute path inside server context

    	String absolutePath = request.getServletContext().getRealPath("") + File.separator + relativeUploadDir;

    	File uploadDir = new File(absolutePath);

    	if (!uploadDir.exists()) {

    	uploadDir.mkdirs();

    	}

    	  

    	Path destinationPath = Paths.get(absolutePath, uniqueFileName);

    	try (InputStream input = filePart.getInputStream()) {

    	Files.copy(input, destinationPath, StandardCopyOption.REPLACE_EXISTING);

    	}

    	  

    	// Return relative path for database storage

    	return relativeUploadDir + "/" + uniqueFileName;

    	}

}
