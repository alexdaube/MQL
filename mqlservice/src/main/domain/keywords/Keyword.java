package domain.keywords;

import java.util.Collection;

public class Keyword {

    public final Type type;
    private final String word;
    private final Collection<String> synonyms;
    private final Keywords children;

    public Keyword(String word, Type type, Collection<String> synonyms, Keywords children) {
        this.word = word;
        this.type = type;
        this.synonyms = synonyms;
        this.children = children;
    }

    public boolean isSynonymOf(String word) {
        return this.word.toLowerCase().equals(word.toLowerCase()) ||
                synonyms.stream().anyMatch(s -> s.toLowerCase().equals(word.toLowerCase()));
    }

    public String name() {
        return word;
    }

    public Collection<String> getSynonyms() {
        return synonyms;
    }

    public Keywords getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keyword keyword = (Keyword) o;

        if (word != null ? !word.equals(keyword.word) : keyword.word != null) return false;
        if (synonyms != null ? !synonyms.equals(keyword.synonyms) : keyword.synonyms != null) return false;
        if (children != null ? !children.equals(keyword.children) : keyword.children != null) return false;
        return type == keyword.type;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (synonyms != null ? synonyms.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public enum Type {
        ENTITY,
        ATTRIBUTE,
        EQUAL,
        LESS,
        GREATER,
        BETWEEN,
        OR,
        AND,
        OTHER
    }
}
