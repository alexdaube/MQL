package domain.keywords;

public interface KeywordsResolver {

    Keywords resolveType(Keyword.Type type);

    Keywords resolveAttributesOf(String tableName);
}
