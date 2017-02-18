package domain.keywords;

public interface KeywordRepository {

    Keywords findKeywordsByType(Keywords.Type type);
}
