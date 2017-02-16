package domain.keywords;

public class Keyword {
    public String word;
    public String parent;

    public Keyword(String word, String parent) {
        this.word = word;
        this.parent = parent;
    }

    public Keyword(String word) {
        this(word, word);
    }
}
