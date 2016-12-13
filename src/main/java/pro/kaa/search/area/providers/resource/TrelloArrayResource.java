package pro.kaa.search.area.providers.resource;

import org.apache.sling.adapter.annotations.Adaptable;
import org.apache.sling.adapter.annotations.Adapter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONException;
import pro.kaa.search.area.utils.trello.TrelloValueMapCreatorUtil;

@Adaptable(adaptableClass = Resource.class, adapters = {
        @Adapter({ValueMap.class})
})
public class TrelloArrayResource extends TrelloAbstractArrayResource {

    public TrelloArrayResource(
            String stringJsonArray, String path, ResourceResolver resourceResolver) throws JSONException {
        super(resourceResolver, path, RESOURCE_TYPE);

        setJsonArrayRepresentation(stringJsonArray);
        setValueMap(TrelloValueMapCreatorUtil.createValueMap(getJsonArrayRepresentation()));
    }
}
