import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.PreparedStatement;

@WebServlet("/WELCOME")
public class WelcomeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html"); 

        String email = (String) request.getAttribute("email");
        String mobile = (String) request.getAttribute("mobile");
        String addr = (String) request.getAttribute("addr");

        out.println("<html><body>");
        out.println("<h1>Welcome!</h1>");
        out.println("<p>Your Email : " + email + "</p>");
        out.println("<p>Your Mobile Number : " + mobile + "</p>");
        out.println("<p>Your Address : " + addr + "</p>");
        out.println("<a href='login.html'>Logout</a>");
        out.println("</body></html>");
    }
}
