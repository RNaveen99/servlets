import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionManagement extends HttpServlet {
    public Boolean ifSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            response.sendRedirect("homePage");
            return true;
        }
        return false;
    }
    public Boolean ifNotSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("signin");
            return true;
        }
        return false;
    }
    public void setSession(HttpServletRequest request, ResultSet rs) throws SQLException {
        HttpSession session = request.getSession();
        session.setAttribute("name", rs.getString("name"));
        session.setAttribute("email", rs.getString("email"));
        session.setAttribute("number", rs.getString("number"));
    }
    public void destroySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
