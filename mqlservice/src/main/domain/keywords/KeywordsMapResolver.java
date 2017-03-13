package domain.keywords;

import java.util.Map;

public class KeywordsMapResolver implements KeywordsResolver {

    private final Map<Keyword.Type, Keywords> keywordsMap;

    public KeywordsMapResolver(Map<Keyword.Type, Keywords> map) {
        keywordsMap = map;
    }

    @Override
    public Keywords resolveType(Keyword.Type type) {
        if (keywordsMap.containsKey(type)) {
            return keywordsMap.get(type);
        }
        return new KeywordsSet();
    }

    @Override
    public Keywords resolveAttributesOf(String tableName) {
        Keywords keywords = this.resolveType(Keyword.Type.ENTITY);
        if (keywords.contains(tableName)) {
            return keywords.getChildrenOf(tableName);
        }
        return new KeywordsSet();
    }
}
