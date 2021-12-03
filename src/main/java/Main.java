import accounts.AccountService;
import accounts.UserProfile;
import dao.DAO;
import dao.UserProfileDAO;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import servlets.SignInServlet;
import servlets.SignUpServlet;


public class Main {
    public static void main(String[] args) throws Exception {

        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            DAO<UserProfile, String> userProfileStringDAO = new UserProfileDAO(sessionFactory);
            final UserProfile testUser = new UserProfile("alex", "123");
            userProfileStringDAO.create(testUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin", "admin"));
        accountService.addNewUser(new UserProfile("test","test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();*/
    }
}
