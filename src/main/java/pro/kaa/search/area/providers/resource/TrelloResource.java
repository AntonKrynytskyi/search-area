package pro.kaa.search.area.providers.resource;

import org.apache.sling.adapter.annotations.Adaptable;
import org.apache.sling.adapter.annotations.Adapter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONException;
import pro.kaa.search.area.utils.trello.TrelloValueMapCreatorUtil;

//TODO: investigation, what does @Adaptable annotation do?
@Adaptable(adaptableClass = Resource.class, adapters = {
        @Adapter({ValueMap.class})
})
public class TrelloResource extends TrelloAbstractObjectResource {

    public TrelloResource(String stringJsonBoard, String resourcePath, ResourceResolver resourceResolver) throws JSONException {
        super(resourceResolver, resourcePath, RESOURCE_TYPE);

        setJsonRepresentation(stringJsonBoard);
        setValueMap(TrelloValueMapCreatorUtil.createValueMap(getJsonRepresentation()));
    }
}
