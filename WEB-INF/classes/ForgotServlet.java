import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/forgot")
public class ForgotServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String usr = request.getParameter("usr");
        String email = request.getParameter("email");

        Connection con = null;
        PreparedStatement psSelect = null, psInsert = null;
        ResultSet rs = null;

        try{
            con = DBUtils.establishConnection();
            psSelect = con.prepareStatement("SELECT password FROM users WHERE name=? AND email=?");
            psSelect.setString(1, usr);
            psSelect.setString(2, email);

            rs = psSelect.executeQuery();

            if(rs.next()){
              out.println("<div class='password-msg'>YOUR PASSWORD: " + rs.getString(1) + "</div>");
            }
            
            out.println("<div class='invalid-user'>INVALID USER </div>");
            RequestDispatcher rd = request.getRequestDispatcher("forgot.html");
            rd.include(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
