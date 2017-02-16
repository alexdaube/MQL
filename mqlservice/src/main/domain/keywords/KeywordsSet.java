package domain.keywords;

import java.util.Set;

public class KeywordsSet implements Keywords {
    private final String parent;
    private final Set<String> children;

    public KeywordsSet(String parent, Set<String> children) {
        this.parent = parent;
        this.children = children;
    }

    @Override
    public boolean contains(String keyword) {
        return children.contains(keyword) || keyword.equals(parent);
    }

    // TODO: 16/02/17 A tester
    @Override
    public String parentKeyword() {
        return parent;
    }
}
