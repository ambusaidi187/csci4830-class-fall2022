
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormInsert")
public class SimpleFormInsertAmbusaidi extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormInsertAmbusaidi() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String FirstName = request.getParameter("FirstName");
      String LastName = request.getParameter("LastName");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      

      Connection connection = null;
      String insertSql = " INSERT INTO MyTable (id, FIRSTNAME, LASTNAME, EMAIL, PHONE) values (default, ?, ?, ?, ?)";

      try {
         DBConnectionAmbusaidi.getDBConnection();
         connection = DBConnectionAmbusaidi.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, FirstName);
         preparedStmt.setString(2, LastName);
         preparedStmt.setString(3, email);
         preparedStmt.setString(4, phone);
         
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>First Name</b>: " + FirstName + "\n" + //
            "  <li><b>Last Name</b>: " + LastName + "\n" + //
            "  <li><b>Email</b>: " + email + "\n" + //
            "  <li><b>Phone</b>: " + phone + "\n" + //
            

            "</ul>\n");

      out.println("<a href=/CustomerRegistrationForm/simpleFormSearch.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
