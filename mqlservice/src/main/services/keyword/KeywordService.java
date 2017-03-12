package services.keyword;

import domain.keywords.KeywordRepository;
import domain.keywords.KeywordsResolver;

public class KeywordService {
    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public KeywordsResolver generateKeywordsResolver() {
        KeywordsRegistrar keywordsRegistrar = KeywordsRegistrar.create();
        keywordRepository.findAllKeywords().forEach(keywordsRegistrar::register);
        return keywordsRegistrar.createKeywordsResolver();
    }
}
