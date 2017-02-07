package com.multitel.mql.mqlservice.domain.keyword;

public class KeywordFactory {

    public KeywordFactory() {
    }

    public Keyword createEntityKeyword(String keyword) {
        return new EntityKeyword(keyword);
    }

    public Keyword createAttributeKeyword(Keyword parent, String keyword) {
        return new AttributeKeyword(keyword, parent);

    }
}
