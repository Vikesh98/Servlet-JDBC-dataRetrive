import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class Search extends HttpServlet {  
  
public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
String name=request.getParameter("ename");  
//int roll=Integer.valueOf(rollno);  
          
try{  
Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","system","1998");  
              
PreparedStatement ps=con.prepareStatement("select * from vikesh where empname=?");  
ps.setString(1,name);  
              
out.print("<table width=50% border=1 >");  
out.print("<caption>Result:</caption>");  
  
ResultSet rs=ps.executeQuery();  
              

ResultSetMetaData rsmd=rs.getMetaData();  
int total=rsmd.getColumnCount();  
out.print("<tr>");  
for(int i=1;i<=total;i++)  
{  
out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
}  
  
out.print("</tr>");  
              
 
  
while(rs.next())  
{  
out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td></tr>");  
                  
}  
  
out.print("</table>");  
              
}catch (Exception e2) 
{
	e2.printStackTrace();
	
}  
          
finally{out.close();}  
  
}  
}