import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

 
public class ClassInchargeLoginUser extends HttpServlet{
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
            String id= user_name.substring(0,8);
			Statement smt=conn.createStatement();
			String s="select * from classes where classincharge='"+id+"'";
			ResultSet rs=smt.executeQuery(s);
			if (rs.next()){
				Statement smt2=conn.createStatement();
				String s2="select * from teacher_info where login='"+user_name+"' and  password='"+pass+"'";
				ResultSet rs2=smt.executeQuery(s2);
				if(rs2.next()){
				System.out.println("Success!!");
				RequestDispatcher rd = request.getRequestDispatcher("CIP");
				rd.forward(request, response);
				}
				else{
				response.sendRedirect("classinchargelogin.html");
			}
				
			}
			else{
				response.sendRedirect("classinchargelogin.html");
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