package com.multitel.mql.mqlservice.domain;

public class Keyword {

    private String id;
    private Keyword parentKeyword;
    private String keyword;

    public Keyword(String id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    private String getId() {
        return this.id;
    }

    public void setParentKeyword(Keyword parentKeyword){
        this.parentKeyword = parentKeyword;
    }
}
