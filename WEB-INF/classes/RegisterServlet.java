import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String uname = request.getParameter("usr");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String addr = request.getParameter("addr");
        String pass = request.getParameter("pass");

        Connection con = null;
        PreparedStatement psSelect = null, psInsert = null;
        ResultSet rs = null;

        try{
            con = DBUtils.establishConnection();

            psSelect = con.prepareStatement("SELECT * FROM users WHERE name=?");
            psSelect.setString(1, uname);
            rs = psSelect.executeQuery();

            if(rs.next()){
                out.println("<p>User Name Already Exists.</p>");
                RequestDispatcher rd = request.getRequestDispatcher("register.html");
                rd.include(request, response);
                return;
            }

            psInsert = con.prepareStatement("INSERT INTO users(name,email,mobile,addr,password) VALUES(?,?,?,?,?)");
            psInsert.setString(1,uname);
            psInsert.setString(2,email);
            psInsert.setString(3,mobile);
            psInsert.setString(4,addr);
            psInsert.setString(5,pass);

            psInsert.executeUpdate();
            out.println("<div class='success'>Registration successful</div>");
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);

        }catch(Exception e){
            e.printStackTrace();        
        }
    }
}
