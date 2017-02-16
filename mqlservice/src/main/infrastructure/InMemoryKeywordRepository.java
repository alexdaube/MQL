package infrastructure;

import domain.keyword.Keyword;
import domain.keyword.KeywordAlreadyExistsException;
import domain.keyword.KeywordRepository;

import java.util.Collection;
import java.util.HashSet;

public class InMemoryKeywordRepository implements KeywordRepository {
    private Collection<Keyword> keywords = new HashSet<>();

    @Override
    public Collection<Keyword> getAllKeywords() {
        return keywords;
    }

    @Override
    public void create(Keyword keyword) throws KeywordAlreadyExistsException {
        if (keywords.contains(keyword)) {
            throw new KeywordAlreadyExistsException();
        }
        keywords.add(keyword);
    }
}
