package pro.kaa.search.area.utils.trello;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.kaa.search.area.TrelloConstants;
import pro.kaa.search.area.providers.TrelloBoardResourceProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrelloUrlUtil {

    private TrelloUrlUtil() {
    }

    public static String getBoardIdFromJcrPath(String path) {
        return StringUtils.isBlank(path) ? null : getPathItemByIndex(getPathItems(path), 2);
    }

    public static String getTrelloTypeFromJcrPath(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }

        List<String> pathItems = getPathItems(path);
        String pathItemByIndex = getPathItemByIndex(pathItems, 3);

        if (pathItemByIndex == null && pathItems.size() == 3) {
            return TrelloConstants.TRELLO_BOARD;
        }

        return pathItemByIndex;
    }

    private static String getPathItemByIndex(List<String> pathItems, int index) {
        if (pathItems.size() > index) {
            return pathItems.get(index);
        } else {
            return null;
        }
    }

    private static List<String> getPathItems(String path) {
        if (!path.startsWith(TrelloBoardResourceProvider.ROOT)) {
            return Collections.emptyList();
        }

        return Arrays.stream(path.split("/"))
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());
    }
}
