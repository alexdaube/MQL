package domain.keywords;

import java.util.HashSet;
import java.util.Set;

public class Keyword {
    public String word;
    public String parent;
    public Set<String> synonyms;
    public Keywords.Type type;

    public Keyword(String word, String parent, Keywords.Type type) {
        this.word = word;
        this.parent = parent;
        this.type = type;
        this.synonyms = new HashSet<>();
    }

    public Keyword(String word) {
        this(word, word, Keywords.Type.ENTITY);
    }

    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }
}
