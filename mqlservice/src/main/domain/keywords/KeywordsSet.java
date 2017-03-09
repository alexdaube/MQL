package domain.keywords;

import domain.InvalidQueryException;

import java.util.Set;

public class KeywordsSet implements Keywords {

    private final Set<Keyword> keywords;

    public KeywordsSet(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean contains(String keyword) {
        return keywords.stream().anyMatch(kw -> kw.isSynonymOf(keyword));
    }

    public String parentOf(String keyword) {
        Keyword k = keywords.stream().filter(kw -> kw.isSynonymOf(keyword)).findFirst()
                .orElseThrow(() -> new InvalidQueryException("No keywords found..."));
        return k.name();
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }
}
