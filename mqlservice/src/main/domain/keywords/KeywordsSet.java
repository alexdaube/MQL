package domain.keywords;

import domain.InvalidQueryException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class KeywordsSet implements Keywords {

    private final Set<Keyword> keywords;

    public KeywordsSet() {
        this.keywords = new HashSet<>();
    }

    @Override
    public boolean contains(String keyword) {
        return keywords.stream().anyMatch(kw -> kw.isSynonymOf(keyword));
    }

    @Override
    public String parentOf(String keyword) {
        Keyword k = keywords.stream().filter(kw -> kw.isSynonymOf(keyword)).findFirst()
                .orElseThrow(() -> new InvalidQueryException("No keywords found..."));
        return k.name();
    }

    @Override
    public void add(Keyword keyword) {
        keywords.add(keyword);
    }

    @Override
    public Keywords getChildrenOf(String name) {
        return keywords.stream().filter(kw -> kw.name().equals(name)).findFirst()
                .orElseThrow(() -> new InvalidQueryException("No keyword with the name specified...")).getChildren();
    }

    @Override
    public Iterator<Keyword> iterator() {
        return keywords.iterator();
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }
}
