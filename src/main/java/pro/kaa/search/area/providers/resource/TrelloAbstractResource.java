package pro.kaa.search.area.providers.resource;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.SyntheticResource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

public abstract class TrelloAbstractResource extends SyntheticResource {

    public static final String RESOURCE_TYPE = "nt:unstructured";

    private ValueMap valueMap;

    TrelloAbstractResource(ResourceResolver resourceResolver, String path, String resourceType) {
        super(resourceResolver, path, resourceType);
    }

    protected void setValueMap(ValueMap valueMap) {
        this.valueMap = valueMap;
    }

    @Override
    public ValueMap getValueMap() {
        return this.valueMap;
    }

    private JSONObject getJson(String stringJson) throws JSONException {
        return new JSONObject(stringJson);
    }

    private JSONArray getJsonArray(String stringJson) throws JSONException {
        return new JSONArray(stringJson);
    }
}
