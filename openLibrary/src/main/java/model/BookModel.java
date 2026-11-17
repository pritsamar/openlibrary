package model;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import utility.JDBCUtility;

public class BookModel {
	 // Generate next primary key
    public static long nextPk() {
        long pk = 0;
        try {
            Connection conn = JDBCUtility.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT MAX(id) FROM books");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pk = rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pk + 1;
    }

    // Add a new book
    public static int addBook(BookBean book) {
        int result = 0;
        try {
            Connection conn = JDBCUtility.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO books (id, name, author, category, description, uploadedBy, coverImagePath, filePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            stmt.setLong(1, nextPk());
            stmt.setString(2, book.getName());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getCategory());
            stmt.setString(5, book.getDescription());
            stmt.setLong(6, book.getUploadedBy());
            stmt.setString(7, book.getCoverImagePath());
            stmt.setString(8, book.getFilePath());

            result = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static List<BookBean> listAllBooks() {
        List<BookBean> books = new ArrayList<>();

        String sql = "SELECT id, name, author, category, description, coverImagePath, filePath, uploadedBy FROM books ORDER BY id DESC";

        try (Connection con = JDBCUtility.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BookBean book = new BookBean();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setDescription(rs.getString("description"));
                book.setCoverImagePath(rs.getString("coverImagePath"));
                book.setFilePath(rs.getString("filePath"));
                book.setUploadedBy(rs.getLong("uploadedBy"));

                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
    
    public static BookBean findById(long id) throws Exception {
        BookBean book = null;
        Connection conn = JDBCUtility.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE id=?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            book = new BookBean();
            book.setId(rs.getLong("id"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("category"));
            book.setDescription(rs.getString("description"));
            book.setCoverImagePath(rs.getString("coverImagePath"));
            book.setFilePath(rs.getString("filePath"));
            book.setUploadedBy(rs.getLong("uploadedBy"));
        }
        return book;
    }

    // Method to update book details in the database
    public static int updateBook(BookBean book) {
        int status = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Get database connection (this assumes you have a method to get a connection)
            conn = JDBCUtility.getConnection();
            
            // SQL UPDATE query
            String sql = "UPDATE books SET name = ?, author = ?, category = ?, description = ?, coverImagePath = ?, filePath = ? WHERE id = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setString(4, book.getDescription());
            pstmt.setString(5, book.getCoverImagePath());
            pstmt.setString(6, book.getFilePath());
            pstmt.setLong(7, book.getId()); // assuming `id` is the primary key

            // Execute the update query
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return status;
    }
    
    
    public static int deleteBook(long id, String realPath) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        BookBean book = findById(id); // Load to get file paths

        try {
            conn = JDBCUtility.getConnection();

            // 1. Delete the database record
            String sql = "DELETE FROM books WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0 && book != null) {
                // 2. Delete cover image file
                if (book.getCoverImagePath() != null) {
                    File coverFile = new File(realPath + File.separator + book.getCoverImagePath());
                    if (coverFile.exists()) {
                        coverFile.delete();
                    }
                }

                // 3. Delete book file
                if (book.getFilePath() != null) {
                    File bookFile = new File(realPath + File.separator + book.getFilePath());
                    if (bookFile.exists()) {
                        bookFile.delete();
                    }
                }
            }

            return rowsAffected;
        } finally {
            JDBCUtility.closeConnection(conn);
            if (ps != null) ps.close();
        }
    }

    // =========================serach by category======================
   
        public static List<BookBean> searchBooksByCategory(String category) {
            List<BookBean> books = new ArrayList<>();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            try {
                con = JDBCUtility.getConnection();
                String query = "SELECT * FROM books WHERE category = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, category);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    BookBean book = new BookBean();
                    book.setId(rs.getLong("id"));
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    book.setCategory(rs.getString("category"));
                    book.setDescription(rs.getString("description"));
                    book.setCoverImagePath(rs.getString("coverImagePath"));
                    book.setFilePath(rs.getString("filePath"));
                    book.setUploadedBy(rs.getLong("uploadedBy"));
                    books.add(book);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JDBCUtility.closeConnection(con);
                try {
					rs.close();
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            return books;
            
        }
    //=====================Search book using keyword======================
        public static List<BookBean> searchBooks(String keyword) {
            List<BookBean> list = new ArrayList<>();
            try (Connection conn = JDBCUtility.getConnection()) {
                String sql = "SELECT * FROM books WHERE name LIKE ? OR author LIKE ? OR category LIKE ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                String query = "%" + keyword + "%";
                ps.setString(1, query);
                ps.setString(2, query);
                ps.setString(3, query);
                
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                	BookBean book = new BookBean();
                    book.setId(rs.getLong("id"));
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    book.setCategory(rs.getString("category"));
                    book.setDescription(rs.getString("description"));
                    book.setCoverImagePath(rs.getString("coverImagePath"));
                    book.setFilePath(rs.getString("filePath"));
                    book.setUploadedBy(rs.getLong("uploadedBy"));
                    list.add(book);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }



}




