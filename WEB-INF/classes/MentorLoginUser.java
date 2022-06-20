import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

 
public class MentorLoginUser extends HttpServlet{
		// JDBC driver name and database URL
      static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
      static final String DB_URL="jdbc:mysql://localhost:3306/result";

      //  Database credentials
      static final String USER = "root";
      static final String PASS = "arpita";
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
           
		try {
			// Register JDBC driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName(JDBC_DRIVER);
			
			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String user_name=request.getParameter("uname");
			String pass=request.getParameter("password"); 
         
			Statement smt=conn.createStatement();
			String s="select * from mentor where login='"+user_name+"' and  password='"+pass+"'";
			ResultSet rs=smt.executeQuery(s);
			if (rs.next()){
				System.out.println("Success!!");
				RequestDispatcher rd = request.getRequestDispatcher("MP");
				rd.forward(request, response);
			}
			else{
				response.sendRedirect("mentorlogin.html");
			}
			rs.close();
			smt.close();
			conn.close();
		}
		catch (Exception e) {
            e.printStackTrace();
        }
	
	}
}