import accountServer.AccountServer;
import accountServer.AccountServerController;
import accountServer.AccountServerControllerMBean;
import accountServer.AccountServerI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resourceServer.ResourceServerController;
import resourceServer.ResourceServerControllerMBean;
import resourceServer.TestResource;
import services.ResourceService;
import services.UserService;
import servlets.ResourcesServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;


public class Main {

    static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        //System.setProperty("log4j.configurationFile",".cfg/log4j2.xml");
        //Logger log = LogManager.getLogger(LogExample.class.getName());
        if (args.length != 1) {
            logger.error("Use port as the first argument");
            System.exit(1);
        }

        String portString = args[0];
        int port = Integer.valueOf(portString);

        logger.info("Starting at http://127.0.0.1:" + portString);

        UserService userService = new UserService();
        AccountServerI accountServer = new AccountServer(1);

        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ServerManager:type=AccountServerController");
        mbs.registerMBean(serverStatistics, name);

        TestResource testResource = new TestResource("qwe", 27);

        ResourceServerControllerMBean serverController = new ResourceServerController(testResource);
        ObjectName name1 = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(serverController, name1);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new SignInServlet(userService, accountServer)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(userService)), "/signup");
        context.addServlet(new ServletHolder(new ResourcesServlet(new ResourceService(testResource))), "/resources");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");
       /* resource_handler.setDirectoriesListed(true);// ?
        resource_handler.setResourceBase("static");// ?*/

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(port);

        server.setHandler(handlers);

        server.start();
        logger.info("Server started");

        server.join();
    }
}
