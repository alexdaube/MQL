package domain.keywords;

import java.util.Set;

public class KeywordsSet implements Keywords {

    private final Set<String> keywords;

    public KeywordsSet(Set<String> keywords) {
        this.keywords = keywords;
    }

    public boolean contains(String keyword) {
        return keywords.contains(keyword);
    }

    public Set<String> getKeywords() {
        return keywords;
    }
}
