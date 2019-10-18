import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/signin"})

public class signin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nav4124", "root", "");
            PreparedStatement stmt = conn.prepareStatement("select * from users where email = ?")) {
            final String email = request.getParameter("email");
            final String password = request.getParameter("password");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            PrintWriter out = response.getWriter();
            RequestDispatcher rd; 
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    new SessionManagement().setSession(request, rs);
                    response.sendRedirect("homePage");
                } else {
                    response.sendRedirect("signin");
                }
            } else {
                response.sendRedirect("signin");
            }
        } catch(Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("signup.jsp"); 
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (new SessionManagement().ifSignIn(request, response)) return;
        RequestDispatcher rd = request.getRequestDispatcher("signin.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (new SessionManagement().ifSignIn(request, response)) return;
        
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(signin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(signin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
