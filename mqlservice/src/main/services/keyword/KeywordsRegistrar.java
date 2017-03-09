package services.keyword;

import domain.keywords.Keyword;
import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;

import java.util.HashMap;
import java.util.Map;

public class KeywordsRegistrar implements KeywordsTypeRegistrar, KeywordsValueRegistrar {
    private Map<Keyword.Type, Keywords> keywordsMap;
    private Keywords keywords;

    public static KeywordsValueRegistrar create() {
        return new KeywordsRegistrar();
    }

    private KeywordsRegistrar() {
        keywordsMap = new HashMap<>();
    }

    @Override
    public KeywordsTypeRegistrar register(Keywords keywords) {
        this.keywords = keywords;
        return this;
    }

    @Override
    public KeywordsResolver createKeywordsResolver() {
        return new KeywordsMapResolver(keywordsMap);
    }

    @Override
    public KeywordsRegistrar as(Keyword.Type type) {
        // TODO: 17/02/17 Do we throw our own exception?
        keywordsMap.put(type, keywords);
        return this;
    }
}
