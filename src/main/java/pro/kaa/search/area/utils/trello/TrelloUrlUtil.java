package pro.kaa.search.area.utils.trello;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TrelloUrlUtil {

    private TrelloUrlUtil() {
    }

    public static String geBoardIdFromJcrPath(String path) {
        List<String> collect = Arrays.stream(path.split("/"))
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());
        if (collect.size() > 2) {
            return collect.get(2);
        } else {
            return null;
        }
    }
}
