import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

 
public class ClassInchargePage extends HttpServlet{
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
            int class_avg_1 = 0;
            int class_avg_2 = 0;
            int class_avg_3 = 0;
            int class_avg_4 = 0;
            int class_avg_5 = 0;
            int class_avg_6 = 0;

			Statement smt1=conn.createStatement();
			String s1="select class from classes where classincharge = '"+id+"'";
			ResultSet rs1=smt1.executeQuery(s1);

			if (rs1.next())
			{
			
                String cls=rs1.getString("class");

                Statement smt2=conn.createStatement();
                String s2="select * from "+cls+"_marks";
                ResultSet rs2=smt2.executeQuery(s2);
                

                Statement smt3=conn.createStatement();
                String s3="select AVG(CAT1_sub1) as 'Subject1' from "+cls+"_marks";
                ResultSet rs3=smt3.executeQuery(s3);
                if(rs3.next()){
                    class_avg_1=rs3.getInt("Subject1");
                    


                }
                Statement smt5=conn.createStatement();
                String s5="select AVG(CAT1_sub2) as 'Subject1' from "+cls+"_marks";
                ResultSet rs5=smt5.executeQuery(s5);
                if(rs5.next()){
                    class_avg_2=rs5.getInt("Subject1");
                    


                }
                Statement smt6=conn.createStatement();
                String s6="select AVG(CAT1_sub3) as 'Subject1' from "+cls+"_marks";
                ResultSet rs6=smt6.executeQuery(s6);
                if(rs6.next()){
                    class_avg_3=rs6.getInt("Subject1");
                    


                }
                Statement smt7=conn.createStatement();
                String s7="select AVG(CAT1_sub4) as 'Subject1' from "+cls+"_marks";
                ResultSet rs7=smt7.executeQuery(s7);
                if(rs7.next()){
                    class_avg_4=rs7.getInt("Subject1");
                    


                }
                Statement smt8=conn.createStatement();
                String s8="select AVG(CAT1_sub5) as 'Subject1' from "+cls+"_marks";
                ResultSet rs8=smt8.executeQuery(s8);
                if(rs8.next()){
                    class_avg_5=rs8.getInt("Subject1");
                    


                }
                Statement smt9=conn.createStatement();
                String s9="select AVG(CAT1_sub6) as 'Subject1' from "+cls+"_marks";
                ResultSet rs9=smt9.executeQuery(s9);
                if(rs9.next()){
                    class_avg_6=rs9.getInt("Subject1");
                    


                }
			 out.println("<html><body>");
            
                while (rs2.next())
                    
                {
                    id=rs2.getString("studentid");
                    Statement smt=conn.createStatement();
                    String s="select * from student_info where studentid='"+id+"'";
                    ResultSet rs=smt.executeQuery(s);
                    
                    if(rs.next()){                    
                        out.println("<p><b>Name : "+rs.getString("name")+"<br>Year: "+rs.getInt("year")+"<br>Department:"+ rs.getString("department"));
                    out.println("<br>Class: "+rs.getString("class")+"<br>");
                    out.println("<div id='previous'>");
                    
                        out.println("Previous Result:-");
                        String str="<ol>"+"<li>Semester1: "+rs2.getDouble("semester1")+"</li>"+ "<li>Semester2: "+rs2.getDouble("semester2")+"</li>"+"<li>Semester3: "+rs2.getDouble("semester3")+"</li>"+"</ol>";
                        out.println(str);

                        out.println("Current Result:-");
                        String str1="<ol>"+"<li>CAT1-Sub1: "+rs2.getInt("CAT1_sub1")+"</li>"+"<li>CAT1-Sub2: "+rs2.getInt("CAT1_sub2")+"</li>"+"<li>CAT1-Sub3: "+rs2.getInt("CAT1_sub3")+"</li>"+"<li>CAT1-Sub4: "+rs2.getInt("CAT1_sub4")+"</li>"+"<li>CAT1-Sub5: "+rs2.getInt("CAT1_sub5")+"</li>"+"<li>CAT1-Sub6: "+rs2.getInt("CAT1_sub6")+"</li>"+"</ol>";
                        out.println(str1);
                    
                    out.println("</div>");
                    out.println("</b></p>");
                    }
                    rs.close();
                }
			    rs2.close();
                out.println("<br> Class Average for Subject 1: "+class_avg_1 + "<br> ");
                    out.println("Class Average for Subject 2: "+class_avg_2 + "<br> ");
                    out.println("Class Average for Subject 3: "+class_avg_3 + "<br> ");
                    out.println("Class Average for Subject 4: "+class_avg_4 + "<br> ");
                    out.println("Class Average for Subject 5: "+class_avg_5 + "<br> ");
                    out.println("Class Average for Subject 6: "+class_avg_6 + "<br> ");
                    out.println("</body></html>");
			
			}
			
			rs1.close();
			
			
		}
		catch (Exception e) {
            e.printStackTrace();
        }
}
}