package pro.kaa.search.area.service;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.kaa.search.area.TrelloHttpClientService;
import pro.kaa.search.area.TrelloUrlBuilderService;
import pro.kaa.search.area.utils.trello.TrelloUrlUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component(immediate = true)
@Service(TrelloHttpClientService.class)
public class TrelloHttpClientServiceImpl implements TrelloHttpClientService {

    private static final Logger LOG = LoggerFactory.getLogger(TrelloHttpClientServiceImpl.class);
    private HttpClient client = new DefaultHttpClient();


    @Reference
    private TrelloUrlBuilderService ulBuilder;

    @Override
    public String getBoardByResourcePath(String path) {
        if (path == null) {
            return null;
        }

        return getBoardById(TrelloUrlUtil.getBoardIdFromJcrPath(path));
    }

    @Override
    public String getBoardById(String boardId) {
        if (boardId == null) {
            return null;
        }

        String stringRepresentationOfResponse = sendGetRequest(ulBuilder
                .setBoardId(boardId)
                .build());

        return stringRepresentationOfResponse == null ? null : stringRepresentationOfResponse;
    }

    @Override
    public String getBoarCardsByResourcePath(String path) {
        String boardId = TrelloUrlUtil.getBoardIdFromJcrPath(path);

        String stringRepresentationOfResponse = sendGetRequest(ulBuilder
                .setBoardId(boardId)
                .setCards()
                .build());

        return stringRepresentationOfResponse == null ? null : stringRepresentationOfResponse;
    }

    @Override
    public String getBoarListsByResourcePath(String path) {
        String boardId = TrelloUrlUtil.getBoardIdFromJcrPath(path);

        String stringRepresentationOfResponse = sendGetRequest(ulBuilder
                .setBoardId(boardId)
                .setLists()
                .build());

        return stringRepresentationOfResponse == null ? null : stringRepresentationOfResponse;
    }

    @Override
    public String getBoarChecklistsByResourcePath(String path) {
        String boardId = TrelloUrlUtil.getBoardIdFromJcrPath(path);

        String stringRepresentationOfResponse = sendGetRequest(ulBuilder
                .setBoardId(boardId)
                .setChecklist()
                .build());

        return stringRepresentationOfResponse == null ? null : stringRepresentationOfResponse;
    }

    @Override
    public String sendGetRequest(String url) {
        try {
            HttpResponse execute = client.execute(new HttpGet(url));

            return execute.getStatusLine().getStatusCode() == 200 ? getResponseStringRepresentation(execute) : null;
        } catch (IOException e) {
            LOG.error("Can not send GET request", e);
            return null;
        }
    }

    private String getResponseStringRepresentation(HttpResponse execute) {
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new InputStreamReader(execute.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        } catch (IOException e) {
            LOG.error("Can not get body of response.", e);
            return null;
        }
    }
}
