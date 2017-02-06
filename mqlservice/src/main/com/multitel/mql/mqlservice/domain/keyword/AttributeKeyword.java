package com.multitel.mql.mqlservice.domain.keyword;

public class AttributeKeyword extends Keyword {

    private Keyword parentKeyword;

    public AttributeKeyword(String keyword, Keyword parentKeyword) {
        super(keyword);
        this.parentKeyword = parentKeyword;
    }
}
