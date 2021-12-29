package servlets;

import accountServer.AccountServerI;
import accounts.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(SignInServlet.class.getName());
    private final AccountServerI accountServer;
    UserService userService = null;

    public SignInServlet(UserService userService, AccountServerI accountServer) {
        this.userService = userService;
        this.accountServer = accountServer;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        UserProfile userProfile = userService.findUserByLogin(login);

      /*  if (remove != null) {// не понимаю зачем это было в примере
            accountServer.removeUser();
            response.getWriter().println("Hasta la vista!");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
*/
        int limit = accountServer.getUsersLimit();
        int count = accountServer.getUsersCount();

        logger.info("Limit: {}. Count {}", limit, count);

        if (limit > count) {
            logger.info("User pass");
            if (userProfile == null || !userProfile.getPass().equals(pass)) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else if (userProfile.getPass().equals(pass)) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Authorized:" + userService.findUserByLogin(login).getLogin());
                accountServer.addNewUser();
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            logger.info("User were rejected");
            response.getWriter().println("Server is closed for maintenance!");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }


    }
}

