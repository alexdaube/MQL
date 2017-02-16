package domain.keywords;

public interface KeywordsResolver {
    Keywords resolveAttributes();
    Keywords resolveEntities();
    Keywords resolveEqualOperators();
    Keywords resolveAndJunctions();
    Keywords resolveOrJunctions();
    Keywords resolveGreaterOperators();
    Keywords resolveLessOperators();
    Keywords resolveOtherOperators();
    Keywords resolveBetweenOperators();
}
