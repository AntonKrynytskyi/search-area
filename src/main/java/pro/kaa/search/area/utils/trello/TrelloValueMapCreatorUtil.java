package pro.kaa.search.area.utils.trello;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TrelloValueMapCreatorUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TrelloValueMapCreatorUtil.class);

    private TrelloValueMapCreatorUtil() {
    }

    public static ValueMap createValueMap(JSONObject json) throws JSONException {
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

    public static ValueMap createValueMap(JSONArray jsonArray) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject listObject = jsonArray.getJSONObject(i);
            String id = listObject.getString("id");

            if (id != null) {
                map.put(id, createValueMap(listObject));
            }
        }

        return new ValueMapDecorator(map);
    }

    private static Object getJsonValue(JSONObject json, String key) throws JSONException {
        Object value = json.get(key);

        return (value instanceof JSONObject) ? createValueMap((JSONObject) value) : value;
    }
}
