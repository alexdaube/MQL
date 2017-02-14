package builders;

import domain.keyword.Keywords;

import java.util.HashSet;
import java.util.Set;

public class KeywordsBuilder {
    private Set<String> keywords;

    public KeywordsBuilder() {
        keywords = new HashSet<>();
    }

    public static KeywordsBuilder create() {
        return new KeywordsBuilder();
    }

    public Keywords build() {
        return new Keywords(keywords);
    }

    public KeywordsBuilder with(String keyword) {
        keywords.add(keyword);
        return this;
    }


}
