import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

 
public class TeacherPage extends HttpServlet{
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
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String id=request.getParameter("uname").substring(0,8);
            System.out.println(id);
			Statement smt=conn.createStatement();
			String s="select * from teacher_info where teacherid = '"+id+"'";
			ResultSet rs=smt.executeQuery(s);
			Statement smt2=conn.createStatement();
			String s2="select class from classes where teacher_sub1='"+id+"' or teacher_sub2 = '"+id+"' or teacher_sub3 = '"+id+"' or teacher_sub4 = '"+id+"' or teacher_sub5 = '"+id+"' or teacher_sub6 = '"+id+"'" ;
			ResultSet rs2=smt2.executeQuery(s2);
			if (rs.next())
			{
				out.println("<html><head><link href='style.css' rel='stylesheet' type='text/css'/></head><body><p><b>Name: "+rs.getString("teacherid")+"<br>Department: "+ rs.getString("department"));
				out.println("<b><br> classes Handeled</b><ol>");
				while(rs2.next())
				{
					out.println("<li>"+rs2.getString("class")+"</li>");
				}
				out.println("</ol><br>");
				out.println("</b></p></body></html>");
			}
			else{
				response.sendRedirect("teacherlogin.html");
			}
			rs.close();
			rs2.close();
			
		}
		catch (Exception e) {
            e.printStackTrace();
        }
}
}