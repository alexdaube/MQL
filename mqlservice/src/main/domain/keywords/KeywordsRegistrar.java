package domain.keywords;

import java.util.HashMap;
import java.util.Map;

public class KeywordsRegistrar {
    private Map<Keyword.Type, Keywords> keywordsMap;

    private KeywordsRegistrar() {
        keywordsMap = new HashMap<>();
    }

    public static KeywordsRegistrar create() {
        return new KeywordsRegistrar();
    }

    public KeywordsRegistrar register(Keyword keyword) {
        if (!keywordsMap.containsKey(keyword.getType())) {
            keywordsMap.put(keyword.getType(), new KeywordsSet());
        }
        keywordsMap.get(keyword.getType()).add(keyword);
        return this;
    }

    public KeywordsResolver createKeywordsResolver() {
        return new KeywordsMapResolver(keywordsMap);
    }

}
