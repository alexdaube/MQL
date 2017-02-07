package com.multitel.mql.mqlservice.infrastructure;

import com.multitel.mql.mqlservice.domain.keyword.Keyword;
import com.multitel.mql.mqlservice.domain.keyword.KeywordRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

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

    public Collection<Keyword> getSubsetKeywords(Keyword parentKeyword) {
        return keywords.stream()
                .filter(keyword -> keyword.isSubsetOf(parentKeyword))
                .collect(Collectors.toList());
    }
}
