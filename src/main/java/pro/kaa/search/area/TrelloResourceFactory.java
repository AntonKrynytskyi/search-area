package pro.kaa.search.area;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONException;

public interface TrelloResourceFactory {

    Resource getResource(String resourceType, String resourcePath, ResourceResolver resourceResolver) throws JSONException;
}
