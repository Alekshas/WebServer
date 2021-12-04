
package servlets;

import accounts.UserProfile;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final UserService userService;

    public SignUpServlet(UserService userService) {
        this.userService = userService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (userService.findUserByLogin(login) != null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("User name already exists!");
        } else {
            userService.saveUser(new UserProfile(login, pass));
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("User is created: " + login);
        }
    }
}

