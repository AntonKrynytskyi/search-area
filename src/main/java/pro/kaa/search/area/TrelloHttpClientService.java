package pro.kaa.search.area;

import java.io.IOException;

public interface TrelloHttpClientService {

    String getBoarListsByResourcePath(String path);

    String getBoarChecklistsByResourcePath(String path);

    String sendGetRequest(String url) throws IOException;

    String getBoardByResourcePath(String path);

    String getBoardById(String boardId);

    String getBoarCardsByResourcePath(String path);
}
