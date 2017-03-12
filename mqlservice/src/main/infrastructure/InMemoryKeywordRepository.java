package infrastructure;

import domain.keywords.Keyword;
import domain.keywords.KeywordRepository;
import domain.keywords.Keywords;
import domain.keywords.KeywordsSet;

import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryKeywordRepository implements KeywordRepository {

    private KeywordsSet keywordsSet;

    public InMemoryKeywordRepository(KeywordsSet keywordsSet) {
        this.keywordsSet = keywordsSet;
    }

    @Override
    //TODO Check keywords types with Nicolas,
    public Keywords findAllKeywords() {
        return keywordsSet;
    }
}
