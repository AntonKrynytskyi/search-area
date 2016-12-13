package pro.kaa.search.area.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import pro.kaa.search.area.TrelloConstants;
import pro.kaa.search.area.TrelloUrlBuilderService;

// TODO: create configuration for this component as trelo server-url.
// TODO: TRELLO_DEFAULT_URL must get from config for this component or global config.

@Component(immediate = true)
@Service(TrelloUrlBuilderService.class)
public class TrelloUrlBuilderServiceImpl implements TrelloUrlBuilderService {

    private static final String TRELLO_DEFAULT_URL = "http://api.trello.com/1/boards/";
    private static final String PARAMETER_APP_KEY = "?key=dde0ecd2b27ee05f9d0489b8419dae04";
    private StringBuilder url;

    @Override
    public TrelloUrlBuilderService setBoardId(String boardId) {
        url = new StringBuilder(TRELLO_DEFAULT_URL);

        if (StringUtils.isBlank(boardId)) {
            throw new IllegalArgumentException("Border id must not be null or empty.");
        }

        url.append(boardId);
        return this;
    }

    @Override
    public TrelloUrlBuilderService setCards() {
        url.append("/" + TrelloConstants.TRELLO_CARDS);
        return this;
    }

    @Override
    public TrelloUrlBuilderService setLists() {
        url.append("/" + TrelloConstants.TRELLO_LISTS);
        return this;
    }

    @Override
    public TrelloUrlBuilderService setChecklist() {
        url.append("/" + TrelloConstants.TRELLO_CHECKLISTS);
        return this;
    }

    @Override
    public String build() {
        url.append(PARAMETER_APP_KEY);
        String stringUrl = url.toString();
        url = new StringBuilder();
        return stringUrl;
    }
}
