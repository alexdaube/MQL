package com.multitel.mql.mqlservice.domain.keyword;

public abstract class Keyword {

    private String keyword;

    public Keyword(String keyword){
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

}
