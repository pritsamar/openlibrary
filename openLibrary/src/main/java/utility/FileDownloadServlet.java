package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/file")
public class FileDownloadServlet extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String BASE_DIR = "C:/openlibrary"; // your actual path

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        String path = request.getParameter("path"); // e.g., cover/abc.jpg

	        if (path == null || path.contains("..")) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid path.");
	            return;
	        }

	        File file = new File(BASE_DIR, path);
	        if (!file.exists() || file.isDirectory()) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
	            return;
	        }

	        String mimeType = getServletContext().getMimeType(file.getName());
	        if (mimeType == null) {
	            mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setContentLengthLong(file.length());

	        try (FileInputStream in = new FileInputStream(file);
	             OutputStream out = response.getOutputStream()) {
	            byte[] buffer = new byte[4096];
	            int bytes;
	            while ((bytes = in.read(buffer)) != -1) {
	                out.write(buffer, 0, bytes);
	            }
	        }
	    }

}
