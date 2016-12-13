package pro.kaa.search.area.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;

@SlingServlet(
        paths = "/test/servlet",
        extensions = "html",
        methods = "GET")
public class TrelloClientServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        Resource board = request.getResourceResolver().getResource("/trello/board/47zdgoUK");
        Resource cards = request.getResourceResolver().getResource("/trello/board/47zdgoUK/cards");

        response.getWriter().write( cards.toString());
    }
}
