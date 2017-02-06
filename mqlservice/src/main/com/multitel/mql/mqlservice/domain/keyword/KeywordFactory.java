package com.multitel.mql.mqlservice.domain.keyword;

import com.multitel.mql.mqlservice.domain.IdGenerator;

public class KeywordFactory {
    private IdGenerator idGenerator;

    public KeywordFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Keyword createEntityKeyword(String keyword) {
        return new EntityKeyword(keyword);
    }

    public Keyword createAttributeKeyword(Keyword parent, String keyword) {
        Keyword newKeyword = new AttributeKeyword(keyword, parent);

        return newKeyword;
    }
}
