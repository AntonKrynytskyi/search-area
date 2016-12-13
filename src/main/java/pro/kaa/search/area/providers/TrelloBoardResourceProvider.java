package pro.kaa.search.area.providers;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.spi.resource.provider.ResolveContext;
import org.apache.sling.spi.resource.provider.ResourceContext;
import org.apache.sling.spi.resource.provider.ResourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.kaa.search.area.TrelloHttpClientService;
import pro.kaa.search.area.providers.resource.TrelloBoardResource;

import java.util.Iterator;

@Component
@Service(value = ResourceProvider.class)
@Properties({
        @Property(name = ResourceProvider.PROPERTY_NAME, value = "Trello"),
        @Property(name = ResourceProvider.PROPERTY_ROOT, value = TrelloBoardResourceProvider.ROOT)
})
public class TrelloBoardResourceProvider extends ResourceProvider<TrelloBoardResourceProvider.Empty> {

    private static final Logger LOG = LoggerFactory.getLogger(TrelloBoardResourceProvider.class);
    public static final String ROOT = "/trello/board/";

    @Reference
    private TrelloHttpClientService httpClient;

    @Override
    public Resource getResource(
            ResolveContext<Empty> resolveContext, String path, ResourceContext resourceContext, Resource resource) {
        return ((path != null) && path.startsWith(ROOT)) ? createTrelloBoardResource(resolveContext, path) : null;
    }

    private TrelloBoardResource createTrelloBoardResource(
            ResolveContext<Empty> resolveContext, String path) {
        try {
            String stringJsonBoard = httpClient.getBoardByResourcePath(path);

            return new TrelloBoardResource(stringJsonBoard, path, resolveContext.getResourceResolver());
        } catch (JSONException e) {
            LOG.error("Can not convert string json representation to JsonObject.", e);
            return null;
        }
    }

    @Override
    public Iterator<Resource> listChildren(ResolveContext<Empty> resolveContext, Resource resource) {
        return null;
    }

    public static class Empty {
        public Empty() {
        }
    }
}
