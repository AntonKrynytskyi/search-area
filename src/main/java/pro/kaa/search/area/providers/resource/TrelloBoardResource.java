package pro.kaa.search.area.providers.resource;

import org.apache.sling.adapter.annotations.Adaptable;
import org.apache.sling.adapter.annotations.Adapter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.SyntheticResource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//TODO: investigation, what does @Adaptable annotation do?
@Adaptable(adaptableClass = Resource.class, adapters = {
        @Adapter({ValueMap.class})
})
public class TrelloBoardResource extends SyntheticResource {

    private static final Logger LOG = LoggerFactory.getLogger(TrelloBoardResource.class);
    public static final String RESOURCE_TYPE = "nt:unstructured";
    private JSONObject jsonRepresentation;
    private ValueMap valueMap;

    public TrelloBoardResource(String stringJsonBoard, String resourcePath, ResourceResolver resourceResolver) throws JSONException {
        super(resourceResolver, resourcePath, RESOURCE_TYPE);

        this.jsonRepresentation = getJsonBoard(stringJsonBoard);
        this.valueMap = createValueMap(this.jsonRepresentation);
    }

    private JSONObject getJsonBoard(String stringJsonBoard) throws JSONException {
        return new JSONObject(stringJsonBoard);
    }

    private ValueMap createValueMap(JSONObject json) {
        Map<String, Object> map = new HashMap<>();
        Iterator<String> keys = json.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            Object value = getJsonValue(json, key);

            if (value != null) {
                map.put(key, value);
            }
        }

        return new ValueMapDecorator(map);
    }

    private Object getJsonValue(JSONObject json, String key) {
        try {
            Object value = json.get(key);

            return (value instanceof JSONObject) ? createValueMap((JSONObject) value) : value;
        } catch (JSONException e) {
            LOG.error("Can not get value from jsonObject.", e);
            return null;
        }
    }

    @Override
    public <AdapterType> AdapterType adaptTo(Class<AdapterType> type) {
        if (type.getClass().equals(JSONObject.class)) {
            return (AdapterType) jsonRepresentation;
        } else if (type.getClass().equals(ValueMap.class)) {
            return (AdapterType) valueMap;
        }

        return super.adaptTo(type);
    }

    @Override
    public ValueMap getValueMap() {
        return this.valueMap;
    }

    @Override
    public String toString() {
        return jsonRepresentation.toString();
    }
}
