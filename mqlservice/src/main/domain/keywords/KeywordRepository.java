package domain.keywords;

import domain.keywords.Keywords;

import java.util.Collection;

public interface KeywordRepository {

    Keywords findKeywordsByType(Keywords.Type type);
}
