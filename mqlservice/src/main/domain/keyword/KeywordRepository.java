package domain.keyword;

import java.util.Collection;

public interface KeywordRepository {

    Collection<Keyword> getAllKeywords();

    void create(Keyword keyword) throws KeywordAlreadyExistsException;
}
