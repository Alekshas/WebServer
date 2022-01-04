package servlets;

import resourceServer.TestResource;
import services.ResourceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.*;
import java.io.IOException;

public class ResourcesServlet extends HttpServlet {
    private final ResourceService resourceService;

    public ResourcesServlet(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String pathToSources = request.getParameter("path");
        resourceService.setTestResource(new TestResource(pathToSources, 200));
        response.getWriter().println("change resources");
    }
}
