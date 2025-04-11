import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String studentId = request.getParameter("studentId");
    String date = request.getParameter("date");
    String status = request.getParameter("status") != null ? "Present" : "Absent";

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDB", "root", "password");

      PreparedStatement ps = con.prepareStatement("INSERT INTO attendance (student_id, date, status) VALUES (?, ?, ?)");
      ps.setString(1, studentId);
      ps.setString(2, date);
      ps.setString(3, status);
      ps.executeUpdate();

      out.println("<h3>Attendance recorded for Student ID: " + studentId + "</h3>");
      con.close();
    } catch (Exception e) {
      e.printStackTrace(out);
    }
  }
}
