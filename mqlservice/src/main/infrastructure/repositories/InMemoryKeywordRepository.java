package infrastructure.repositories;

import domain.keywords.KeywordRepository;
import domain.keywords.Keywords;
import domain.keywords.KeywordsSet;

public class InMemoryKeywordRepository implements KeywordRepository {

    private KeywordsSet keywordsSet;

    public InMemoryKeywordRepository(KeywordsSet keywordsSet) {
        this.keywordsSet = keywordsSet;
    }

    @Override
    public Keywords findAllKeywords() {
        return keywordsSet;
    }
}
