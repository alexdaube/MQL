package com.multitel.mql.mqlservice.domain;

public class KeywordFactory {
    private IdGenerator idGenerator;

    public KeywordFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Keyword createParentKeyword(String keyword) {
        return new Keyword(idGenerator.generate(), keyword);
    }

    public Keyword createSubsetKeyword(Keyword parent, String keyword) {
        Keyword newKeyword = new Keyword(idGenerator.generate(), keyword);
        newKeyword.setParentKeyword(parent);

        return newKeyword;
    }
}
