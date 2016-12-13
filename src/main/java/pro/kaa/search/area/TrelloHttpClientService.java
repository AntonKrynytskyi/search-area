package pro.kaa.search.area;

import java.io.IOException;

public interface TrelloHttpClientService {

    String sendGetRequest(String url) throws IOException;

    String getBoardByResourcePath(String path);

    String getBoardById(String boardId);
}
