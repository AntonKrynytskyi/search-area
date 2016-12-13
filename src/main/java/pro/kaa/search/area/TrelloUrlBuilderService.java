package pro.kaa.search.area;

public interface TrelloUrlBuilderService {

    TrelloUrlBuilderService setBoardId(String boarId);

    TrelloUrlBuilderService setCards();

    TrelloUrlBuilderService setLists();

    TrelloUrlBuilderService setChecklist();

    String build();
}
