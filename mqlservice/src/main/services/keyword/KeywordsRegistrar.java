package services.keyword;

import domain.keywords.Keyword;
import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;
import domain.keywords.KeywordsSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class KeywordsRegistrar {
    private Map<Keyword.Type, Keywords> keywordsMap;

    public static KeywordsRegistrar create() {
        return new KeywordsRegistrar();
    }

    private KeywordsRegistrar() {
        keywordsMap = new HashMap<>();
    }

    public KeywordsRegistrar register(Keyword keyword) {
        if (!keywordsMap.containsKey(keyword.type)) {
            keywordsMap.put(keyword.type, new KeywordsSet(new HashSet<>()));
        }
        keywordsMap.get(keyword.type).add(keyword);
        return this;
    }

    public KeywordsResolver createKeywordsResolver() {
        return new KeywordsMapResolver(keywordsMap);
    }

}
