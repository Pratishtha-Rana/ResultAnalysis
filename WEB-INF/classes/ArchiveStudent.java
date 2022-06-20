import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

 
public class ArchiveStudent extends HttpServlet{
		// JDBC driver name and database URL
      static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
      static final String DB_URL="jdbc:mysql://localhost:3306/result";

      //  Database credentials
      static final String USER = "root";
      static final String PASS = "arpita";
	  String display = "";
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
           
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
			
			String id=request.getParameter("stu_id");
			Statement smt=conn.createStatement();
			String s="select * from student_login where studentid='"+id+"'";
			ResultSet rs=smt.executeQuery(s);
			Statement smt2=conn.createStatement();
			String s2="select * from student_info where studentid='"+id+"'";
			ResultSet rs2=smt2.executeQuery(s2);
			PreparedStatement st = conn.prepareStatement("insert into archive values(?, ?, ?,?,?,?,?)");
			if(rs.next() && rs2.next())
			{
				st.setString(1, rs.getString("studentid"));
			     st.setString(2,rs.getString("login"));
			    st.setString(3, rs.getString("password"));
				st.setString(4, rs2.getString("name"));
			     st.setString(5,rs2.getString("department"));
			    st.setInt(6, rs2.getInt("year"));
				st.setString(7, rs2.getString("class"));
			    st.executeUpdate();
				s="Delete from student_info where studentid= '"+id+"'";
				s2="Delete from student_login where studentid= '"+id+"'";
				Statement stmt=conn.createStatement();
				Statement stmt2=conn.createStatement();
				stmt.executeUpdate(s);
				stmt2.executeUpdate(s2);
				rs.close();
				rs2.close();
				display = "Student has been archived";
			}
			else{
				display = "Student does not exist";
			}
		
		String page = "<html><body><h3>" + display + "</h3> <br><a href='admin.html'><button class='button1'>Return</button></a></body></html>";
		out.println(page);	
			

		}
		catch (Exception e) {
            e.printStackTrace();
        }


}
}