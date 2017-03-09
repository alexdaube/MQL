package domain.keywords;

import java.util.HashSet;
import java.util.Set;

public class Keyword {
    public String word;
    public Set<String> synonyms;
    public Keywords.Type type;

    public Keyword(String word, Keywords.Type type) {
        this.word = word;
        this.type = type;
        this.synonyms = new HashSet<>();
    }

    public Keyword(String word) {
        this(word, Keywords.Type.ENTITY);
    }

    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }
}
