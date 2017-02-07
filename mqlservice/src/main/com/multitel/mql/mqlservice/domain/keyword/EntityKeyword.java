package com.multitel.mql.mqlservice.domain.keyword;

public class EntityKeyword implements Keyword {

    private String keyword;

    public EntityKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getKeyword() {
        return null;
    }

    @Override
    public boolean isSubsetOf(Keyword keyword) {
        return false;
    }
}
