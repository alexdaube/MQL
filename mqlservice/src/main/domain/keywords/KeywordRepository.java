package domain.keywords;

public interface KeywordRepository {

    Keywords findKeywordsByType(Keyword.Type type);
}
