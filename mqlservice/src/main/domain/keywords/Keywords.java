package domain.keywords;

public interface Keywords extends Iterable<Keyword> {

    boolean contains(String keyword);

    String parentOf(String keyword);

    void add(Keyword keyword);

    Keywords getChildrenOf(String name);
}
