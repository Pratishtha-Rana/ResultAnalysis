import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

 
public class HODPage extends HttpServlet{
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
			String id=request.getParameter("dept").substring(0,3);
			System.out.println(id);
			
			Statement smt1=conn.createStatement();
			String s1="select class from classes where class like '"+id+"%'";
			ResultSet rs1=smt1.executeQuery(s1);
			
			while (rs1.next())
			{
			
				String cls=rs1.getString("class");
				Statement smt2=conn.createStatement();
				String s2="select * from "+cls+"_marks";
				ResultSet rs2=smt2.executeQuery(s2);
			while (rs2.next())
				
			{
				id=rs2.getString("studentid");
				Statement smt=conn.createStatement();
				String s="select * from student_info where studentid='"+id+"'";
				ResultSet rs=smt.executeQuery(s);
				if(rs.next()){
					out.println("<html><body><p><b>Name : "+rs.getString("name")+"<br>Year: "+rs.getInt("year")+"<br>Department:"+ rs.getString("department"));
					out.println("<br>Class: "+rs.getString("class")+"<br>");
					out.println("<div id='previous'>");
					
						out.println("Previous Result:-");
						String str="<ol>"+"<li>Semester1: "+rs2.getDouble("semester1")+"</li>"+ "<li>Semester2: "+rs2.getDouble("semester2")+"</li>"+"<li>Semester3: "+rs2.getDouble("semester3")+"</li>"+"</ol>";
						out.println(str);

						out.println("Current Result:-");
						String str1="<ol>"+"<li>CAT1-Sub1: "+rs2.getInt("CAT1_sub1")+"</li>"+"<li>CAT1-Sub2: "+rs2.getInt("CAT1_sub2")+"</li>"+"<li>CAT1-Sub3: "+rs2.getInt("CAT1_sub3")+"</li>"+"<li>CAT1-Sub4: "+rs2.getInt("CAT1_sub4")+"</li>"+"<li>CAT1-Sub5: "+rs2.getInt("CAT1_sub5")+"</li>"+"<li>CAT1-Sub6: "+rs2.getInt("CAT1_sub6")+"</li>"+"</ol>";
						out.println(str1);
					
					out.println("</div>");
				out.println("</b></p></body></html>");
				}
                rs.close();
			}
			rs2.close();
			
			}
			
			rs1.close();
			
			
		}
		catch (Exception e) {
            e.printStackTrace();
        }
}
}