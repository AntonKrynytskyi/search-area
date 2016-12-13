package pro.kaa.search.area.providers.resource;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

public abstract class TrelloAbstractObjectResource extends TrelloAbstractResource {

    private JSONObject jsonRepresentation;

    TrelloAbstractObjectResource(ResourceResolver resourceResolver, String path, String resourceType) {
        super(resourceResolver, path, resourceType);
    }

    @Override
    public <AdapterType> AdapterType adaptTo(Class<AdapterType> type) {
        if (type.getClass().equals(JSONObject.class)) {
            return (AdapterType) jsonRepresentation;
        } else if (type.getClass().equals(ValueMap.class)) {
            return (AdapterType) getValueMap();
        }

        return super.adaptTo(type);
    }

    protected JSONObject getJsonRepresentation() {
        return jsonRepresentation;
    }

    protected void setJsonRepresentation(String jsonRepresentation) throws JSONException {
        this.jsonRepresentation = new JSONObject(jsonRepresentation);
    }

    @Override
    public String toString() {
        return jsonRepresentation.toString();
    }
}
