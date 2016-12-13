package pro.kaa.search.area.providers.resource;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;

public abstract class TrelloAbstractArrayResource extends TrelloAbstractResource {

    private JSONArray jsonArrayRepresentation;

    TrelloAbstractArrayResource(ResourceResolver resourceResolver, String path, String resourceType) {
        super(resourceResolver, path, resourceType);
    }

    @Override
    public <AdapterType> AdapterType adaptTo(Class<AdapterType> type) {
        if (type.getClass().equals(JSONArray.class)) {
            return (AdapterType) jsonArrayRepresentation;
        } else if (type.getClass().equals(ValueMap.class)) {
            return (AdapterType) getValueMap();
        }

        return super.adaptTo(type);
    }

    protected JSONArray getJsonArrayRepresentation() {
        return jsonArrayRepresentation;
    }

    protected void setJsonArrayRepresentation(String jsonArrayRepresentation) throws JSONException {
        this.jsonArrayRepresentation = new JSONArray(jsonArrayRepresentation);
    }

    @Override
    public String toString() {
        return jsonArrayRepresentation.toString();
    }
}
