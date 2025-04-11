import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String id = request.getParameter("id");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDB", "root", "password");

      PreparedStatement ps;
      if (id != null && !id.isEmpty()) {
        ps = con.prepareStatement("SELECT * FROM employees WHERE id=?");
        ps.setInt(1, Integer.parseInt(id));
      } else {
        ps = con.prepareStatement("SELECT * FROM employees");
      }

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        out.println("<p>ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + "</p>");
      }
      con.close();
    } catch (Exception e) {
      e.printStackTrace(out);
    }
  }
}
