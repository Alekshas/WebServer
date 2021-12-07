package servlets;

import accounts.UserProfile;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    UserService userService = null;

    public SignInServlet(UserService userService) {
        this.userService = userService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        UserProfile userProfile = userService.findUserByLogin(login);

        if (userProfile == null || !userProfile.getPass().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else if (userProfile.getPass().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Authorized:" + userService.findUserByLogin(login).getLogin());
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}

