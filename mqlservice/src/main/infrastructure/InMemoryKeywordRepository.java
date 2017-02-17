package infrastructure;

import domain.keyword.Keyword;
import domain.keywords.KeywordRepository;
import domain.keywords.Keywords;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryKeywordRepository implements KeywordRepository {
    private final Map<Keywords.Type, Keywords> keywordsMap;

    public InMemoryKeywordRepository(Map<Keywords.Type, Keywords> keywordsMap) {
        this.keywordsMap = keywordsMap;
    }

    @Override
    public Keywords findKeywordsByType(Keywords.Type type) {
        return keywordsMap.get(type);
    }
}
