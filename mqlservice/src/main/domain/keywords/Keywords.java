package domain.keywords;

public interface Keywords {

    boolean contains(String keyword);

    String parentOf(String keyword);

    enum Type {
        ENTITY,
        ATTRIBUTE,
        EQUALS,
        LESS,
        GREATER,
        BETWEEN,
        OR,
        OTHER, AND
    }
}
