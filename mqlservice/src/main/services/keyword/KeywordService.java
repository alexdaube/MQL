package services.keyword;

import domain.keywords.Keyword;
import domain.keywords.KeywordRepository;
import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;

public class KeywordService {
    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public KeywordsResolver generateKeywordsResolver() {
        KeywordsValueRegistrar keywordsRegistrar = KeywordsRegistrar.create();
        for (Keyword.Type type:Keyword.Type.values()) {
            keywordsRegistrar.register(keywordRepository.findKeywordsByType(type)).as(type);
        }
        return keywordsRegistrar.createKeywordsResolver();
    }
}
