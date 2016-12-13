package pro.kaa.search.area;

public interface TrelloUrlBuilderService {

    TrelloUrlBuilderService setBoardId(String boarId);

    String build();
}
