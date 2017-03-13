package domain.keywords;

import java.util.HashSet;
import java.util.Set;

public class Keyword {
    private String word;
    private Set<String> synonyms;
    public Type type;

    public Keyword(String word, Type type) {
        this(word, type, new HashSet<>());
    }

    public Keyword(String word, Type type, Set<String> synonyms) {
        this.word = word;
        this.type = type;
        this.synonyms = synonyms;
    }

    // TODO: 09/03/17 Test
    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }

    // TODO: 09/03/17 test
    public boolean isSynonymOf(String word) {
        return this.word.toLowerCase().equals(word.toLowerCase()) ||
                synonyms.stream().anyMatch(s -> s.toLowerCase().equals(word.toLowerCase()));
    }

    public String name() {
        return word;
    }

    public Set<String> getSynonyms() {
        return synonyms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keyword keyword = (Keyword) o;

        if (word != null ? !word.equals(keyword.word) : keyword.word != null) return false;
        if (synonyms != null ? !synonyms.equals(keyword.synonyms) : keyword.synonyms != null) return false;
        return type == keyword.type;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (synonyms != null ? synonyms.hashCode() : 0);
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
