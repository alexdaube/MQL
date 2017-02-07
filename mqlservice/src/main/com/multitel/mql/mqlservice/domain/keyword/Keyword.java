package com.multitel.mql.mqlservice.domain.keyword;

public interface Keyword {

    String getKeyword();

    boolean isSubsetOf(Keyword keyword);

}
