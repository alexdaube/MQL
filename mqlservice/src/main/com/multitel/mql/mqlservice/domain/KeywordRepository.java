package com.multitel.mql.mqlservice.domain;

import java.util.Collection;

public interface KeywordRepository {

    Collection<Keyword> getAllKeywords();

    void create(Keyword keyword);


}
