package infrastructure;

import domain.keywords.EntityMap;
import domain.keywords.KeywordRepository;
import domain.keywords.Keywords;

public class InMemoryKeywordRepository implements KeywordRepository {

    private EntityMap entityKeywords;

    public InMemoryKeywordRepository(EntityMap entityMap) {
        this.entityKeywords = entityMap;
    }

    @Override
    public Keywords findKeywordsByType(Keywords.Type type) {
        //return this.entityKeywords.getKeywordsFromType(type);
        return null;
    }

}
