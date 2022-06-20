import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class AddStudentDetail extends HttpServlet{
		// JDBC driver name and database URL
      static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      static final String DB_URL="jdbc:mysql://localhost:3306/result";

      //  Database credentials
      static final String USER = "root";
      static final String PASS = "arpita";
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   
		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
           
		try {
			// Register JDBC driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName(JDBC_DRIVER);
         
			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query 
			PreparedStatement st = conn.prepareStatement("insert into student_login values(?, ?, ?)");
			st.setString(1, request.getParameter("stu_id"));
			st.setString(2, request.getParameter("stu_id")+"@ssn.edu.in");
			st.setString(3, request.getParameter("password"));
			st.executeUpdate();
			PreparedStatement smt = conn.prepareStatement("insert into student_info values(?, ?, ?,?,?)");
			smt.setString(1, request.getParameter("stu_id"));
			smt.setString(2, request.getParameter("stu_name"));
			smt.setString(3, request.getParameter("stu_dept"));
			smt.setInt(4,1);
			smt.setString(5,request.getParameter("stu_class"));
			smt.executeUpdate();
			out.println("<html><Body><h1>Succesfullyadded</h1></Body></html>");
			response.sendRedirect("admin.html");
		   
  
            // Close all the connections
	    out.close();
            st.close();
            conn.close();
  
            // Get a writer pointer 
            // to display the successful result
            
            //RequestDispatcher rd = request.getRequestDispatcher("ID2");
			//rd.forward(request, response);
	    //response.sendRedirect("login.html");
        }
        catch (SQLIntegrityConstraintViolationException e) {
			out.println("Student already exists");
		}
		
		catch (Exception e) {
            e.printStackTrace();
        }
    }
}