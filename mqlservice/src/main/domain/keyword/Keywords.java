package domain.keyword;

import java.util.Set;

public class Keywords {
    private final Set<String> keywords;

    public Keywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public boolean contains(String keyword) {
        return keywords.contains(keyword);
    }
}
