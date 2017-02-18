package services.keyword;

import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;
import domain.keywords.KeywordsSet;

import java.util.HashSet;
import java.util.Map;

public class KeywordsMapResolver implements KeywordsResolver {
    private final Map<Keywords.Type, Keywords> keywordsMap;

    public KeywordsMapResolver(Map<Keywords.Type, Keywords> map) {
        keywordsMap = map;
    }

    @Override
    public Keywords resolveType(Keywords.Type type) {
        if (keywordsMap.containsKey(type)) {
            return keywordsMap.get(type);
        }
        return new KeywordsSet(new HashSet<>());
    }
}
