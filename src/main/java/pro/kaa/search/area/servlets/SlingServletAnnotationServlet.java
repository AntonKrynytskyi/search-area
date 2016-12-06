package pro.kaa.search.area.servlets;


import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;


@SlingServlet(
        paths = "/sling/annotation/slingServlet",
        extensions = "html",
        methods = "GET"
)
public class SlingServletAnnotationServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("Example of @SlingServlet annotation. MrGr3n");
    }
}
