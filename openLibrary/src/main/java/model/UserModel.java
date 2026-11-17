package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.UserBean;
import utility.JDBCUtility;

public class UserModel {
	
	 public static long nextPk() {
		    long pk = 0;
		    Connection conn;
		    try {
		      conn = JDBCUtility.getConnection();
		      PreparedStatement stmt = conn.prepareStatement("select Max(id) from users");
		      ResultSet rs = stmt.executeQuery();
		      while(rs.next()){
		        pk = rs.getLong(1);
		      }
		    } catch (Exception e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
		    return pk+1;
		  }
	 
	 //add user
	 public static long addUser(UserBean  user) throws Exception {
		    int i = 0;
		    Connection conn = null;
		    PreparedStatement checkStmt = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    
		    try {
		       conn = JDBCUtility.getConnection();
		      
		   // Check for existing email
		        checkStmt = conn.prepareStatement("SELECT id FROM users WHERE email = ?");
		        checkStmt.setString(1, user.getEmail());
		        rs = checkStmt.executeQuery();
		        if (rs.next()) {
		            throw new Exception("Email already exists");
		        }
		      
		      
		      
		       stmt = conn.prepareStatement("insert into users values(?,?,?,?)");
		      stmt.setLong(1, nextPk());
		      stmt.setString(2 , user.getUsername() );
		      stmt.setString(3 , user.getEmail() );
		      stmt.setString(4 , user.getPassword() );
		 
		        i =     stmt.executeUpdate();
		      
		    } catch (Exception e) {
		      // TODO Auto-generated catch block
		      throw e;
		    }finally {
		    	// Close resources properly
		        if (rs != null) rs.close();
		        if (checkStmt != null) checkStmt.close();
		        if (stmt != null) stmt.close();
		        if (conn != null) conn.close();
		    }
		    
		    
		    return i;
		  }
	 
	//Login User......
	  public static UserBean  UserLogin(String email,String password) {
	    Connection con;
	    UserBean user = null;
	    try {
	      con = JDBCUtility.getConnection();
	      PreparedStatement stmt = con.prepareStatement("Select * from users where email=? and password =?");
	      stmt.setString(1,email);
	      stmt.setString(2,password);
	      ResultSet rs = stmt.executeQuery();
	      if(rs.next()) {
	        user = new UserBean();
	        System.out.println("ID: "+rs.getLong("id"));
	        user.setId(rs.getLong("id"));
	        user.setUsername(rs.getString("username"));
	        user.setEmail(rs.getString("email"));
	        user.setPassword(rs.getString("password"));
	            
	      }
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    return user;
	  }
	  
	  
	  // get user by id
	  public static String getUserNameById(Long id) {
	        String userName = "Unknown";

	        String sql = "SELECT username FROM users WHERE id = ?";

	        try (Connection con = JDBCUtility.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setLong(1, id);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    userName = rs.getString("username");
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return userName;
	    }
	  

}
