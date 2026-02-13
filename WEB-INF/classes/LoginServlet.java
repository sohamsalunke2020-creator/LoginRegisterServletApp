import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String uname = request.getParameter("usr");
        String password = request.getParameter("pass");

        Connection con = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;
        RequestDispatcher rd = null;

        try{
            con = DBUtils.establishConnection();
            psSelect = con.prepareStatement("SELECT * FROM users WHERE name=? AND password=?");
            psSelect.setString(1,uname);
            psSelect.setString(2,password);
            rs = psSelect.executeQuery();

            if(rs.next()){
                request.setAttribute("email", rs.getString("email")); //name,value
                request.setAttribute("mobile", rs.getString("mobile"));
                request.setAttribute("addr", rs.getString("addr"));

                rd = request.getRequestDispatcher("WELCOME");
                rd.forward(request, response);
            }

            out.println("<p>Invalid Credentials</p>");
            rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);
            
        }catch(Exception e){
            e.printStackTrace();        }
    }
}