package com.multitel.mql.mqlservice.infrastructure;

import com.multitel.mql.mqlservice.domain.Keyword;
import com.multitel.mql.mqlservice.domain.KeywordRepository;

import java.util.Collection;
import java.util.HashSet;

public class InMemoryKeywordRepository implements KeywordRepository {
    private Collection<Keyword> keywords = new HashSet<>();

    @Override
    public Collection<Keyword> getAllKeywords() {
        return keywords;
    }

    @Override
    public void create(Keyword keyword) {
        keywords.add(keyword);
    }
}
