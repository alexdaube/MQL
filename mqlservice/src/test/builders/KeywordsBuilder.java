package builders;

import domain.keywords.Keywords;
import domain.keywords.KeywordsSet;

import java.util.HashSet;
import java.util.Set;

public class KeywordsBuilder {
    private Set<String> children;
    private String parent;

    public KeywordsBuilder() {
        children = new HashSet<>();
    }

    public static KeywordsBuilder create() {
        return new KeywordsBuilder();
    }

    public Keywords build() {
        return new KeywordsSet(parent, children);
    }

    public KeywordsBuilder withParent(String parent) {
        this.parent = parent;
        return this;
    }

    public KeywordsBuilder with(String keyword) {
        children.add(keyword);
        return this;
    }


}
