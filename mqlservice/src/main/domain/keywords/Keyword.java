package domain.keywords;

import java.util.HashSet;
import java.util.Set;

public class Keyword {
    private String word;
    private Set<String> synonyms;
    public Type type;

    public Keyword(String word, Type type) {
        this.word = word;
        this.type = type;
        this.synonyms = new HashSet<>();
    }


    // TODO: 09/03/17 Test
    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }

    // TODO: 09/03/17 test
    public boolean isSynonymOf(String word) {
        return this.word.equals(word) || synonyms.stream().anyMatch(s -> s.equals(word));
    }

    public String name() {
        return word;
    }

    public enum Type {
        ENTITY,
        ATTRIBUTE,
        EQUALS,
        LESS,
        GREATER,
        BETWEEN,
        OR,
        AND,
        OTHER
    }
}
