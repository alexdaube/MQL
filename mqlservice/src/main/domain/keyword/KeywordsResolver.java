package domain.keyword;

public interface KeywordsResolver {
    Keywords resolveAttributes();
    Keywords resolveEntities();
    Keywords resolveOperators();
    Keywords resolveJunctions();
}
