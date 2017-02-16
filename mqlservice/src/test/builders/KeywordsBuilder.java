package builders;

import domain.keywords.Keyword;
import domain.keywords.Keywords;
import domain.keywords.KeywordsSet;

import java.util.HashSet;
import java.util.Set;

public class KeywordsBuilder {
    private Set<Keyword> keywords;

    public KeywordsBuilder() {
        keywords = new HashSet<>();
    }

    public static KeywordsBuilder create() {
        return new KeywordsBuilder();
    }

    public Keywords build() {
        return new KeywordsSet(keywords);
    }

    public KeywordsBuilder with(String keyword) {
        keywords.add(new Keyword(keyword));
        return this;
    }

    public KeywordsBuilder with(String keyword, String parent) {
        keywords.add(new Keyword(keyword, parent));
        return this;
    }
}
