package domain.keyword;

import java.util.Collection;

public interface KeywordRepository {

    Collection<Keyword> getAllKeywords();

    Collection<Keyword> getSubsetKeywords(Keyword parentKeyword);

    void create(Keyword keyword);
}
