package infrastructure;

import domain.keywords.KeywordRepository;
import domain.keywords.Keywords;
import domain.keywords.KeywordsSet;

public class InMemoryKeywordRepository implements KeywordRepository {

    private KeywordsSet keywordsSet;

    public InMemoryKeywordRepository(KeywordsSet keywordsSet) {
        this.keywordsSet = keywordsSet;
    }

    @Override
    //TODO Check keyword types with Nicolas,
    public Keywords findKeywordsByType(Keywords.Type type) {
        return null;
    }

}
