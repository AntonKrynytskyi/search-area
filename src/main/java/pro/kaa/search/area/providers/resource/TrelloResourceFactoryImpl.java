package pro.kaa.search.area.providers.resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONException;
import pro.kaa.search.area.TrelloConstants;
import pro.kaa.search.area.TrelloHttpClientService;
import pro.kaa.search.area.TrelloResourceFactory;

import java.util.Objects;

@Component(immediate = true)
@Service(TrelloResourceFactory.class)
public class TrelloResourceFactoryImpl implements TrelloResourceFactory {

    @Reference
    private TrelloHttpClientService httpClient;

    @Override
    public Resource getResource(String resourceType, String resourcePath, ResourceResolver resourceResolver) throws JSONException {
        Validate.isTrue(Objects.nonNull(resourceResolver), "Resource resolver must not be null.", 1);
        Validate.isTrue(!StringUtils.isBlank(resourceType), "Resource type  must not be null or empty.", 2);
        Validate.isTrue(!StringUtils.isBlank(resourcePath), "Resource path  must not be null or empty.", 2);

        if (resourceType.equals(TrelloConstants.TRELLO_BOARD)) {
            String stringJsonBoard = httpClient.getBoardByResourcePath(resourcePath);

            return new TrelloResource(stringJsonBoard, resourcePath, resourceResolver);
        }

        if (resourceType.equals(TrelloConstants.TRELLO_CARDS)) {
            String jsonArrayCards = httpClient.getBoarCardsByResourcePath(resourcePath);

            return new TrelloArrayResource(jsonArrayCards, resourcePath, resourceResolver);
        }

        if (resourceType.equals(TrelloConstants.TRELLO_CHECKLISTS)) {
            String jsonArrayChecklist = httpClient.getBoarChecklistsByResourcePath(resourcePath);

            return new TrelloArrayResource(jsonArrayChecklist, resourcePath, resourceResolver);
        }

        if (resourceType.equals(TrelloConstants.TRELLO_LISTS)) {
            String jsonArrayLists = httpClient.getBoarListsByResourcePath(resourcePath);

            return new TrelloArrayResource(jsonArrayLists, resourcePath, resourceResolver);
        }

        return null;
    }
}
