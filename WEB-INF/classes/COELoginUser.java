import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

 
public class COELoginUser extends HttpServlet{
		// JDBC driver name and database URL
      static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
      static final String DB_URL="jdbc:mysql://localhost:3306/result";

      //  Database credentials
      static final String USER = "root";
      static final String PASS = "ssn@123";
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
           
		try {
			// Register JDBC driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName(JDBC_DRIVER);
			
			// Open a connection
			//Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String user_name=request.getParameter("uname");
			String pass=request.getParameter("password"); 
         
			
			if (user_name.equals("COE") && pass.equals("COE")){
				System.out.println("Success!!");
				response.sendRedirect("coe.html");
			}
			else{
				response.sendRedirect("coelogin.html");
			}
			
		}
		catch (Exception e) {
            e.printStackTrace();
        }
	
	}
}