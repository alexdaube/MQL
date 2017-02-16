package domain.keywords;

public class DefaultKeywordResolver implements KeywordsResolver {

    private EntityMap entityKeywords;

    @Override
    public void initializeKeywords(EntityMap entityMap) {
        this.entityKeywords = entityMap;
    }

    @Override
    public Keywords resolveAttributes() {
        return new KeywordsSet(this.entityKeywords.getAttributesKeywords());
    }

    @Override
    public Keywords resolveEntities() {
        return new KeywordsSet(this.entityKeywords.getEntityKeywords());
    }

    @Override
    public Keywords resolveEqualOperators() {
        return null;
    }

    @Override
    public Keywords resolveAndJunctions() {
        return null;
    }

    @Override
    public Keywords resolveOrJunctions() {
        return null;
    }

    @Override
    public Keywords resolveGreaterOperators() {
        return null;
    }

    @Override
    public Keywords resolveLessOperators() {
        return null;
    }

    @Override
    public Keywords resolveOtherOperators() {
        return null;
    }

    @Override
    public Keywords resolveBetweenOperators() {
        return null;
    }
}
