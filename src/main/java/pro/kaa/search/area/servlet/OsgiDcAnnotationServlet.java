package pro.kaa.search.area.servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.apache.sling.servlets.resolver.internal.ServletResolverConstants.SLING_SERVLET_EXTENSIONS;
import static org.apache.sling.servlets.resolver.internal.ServletResolverConstants.SLING_SERVLET_METHODS;
import static org.apache.sling.servlets.resolver.internal.ServletResolverConstants.SLING_SERVLET_PATHS;


@Component(
        service = {Servlet.class},
        property = {
                SLING_SERVLET_PATHS + "=/osgi/dc/annotation/component",
                SLING_SERVLET_METHODS + "=GET",
                SLING_SERVLET_EXTENSIONS + "=html",
        }
)
public class OsgiDcAnnotationServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("Use @Component annotation.");
    }
}
