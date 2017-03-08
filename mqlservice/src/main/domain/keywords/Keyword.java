package domain.keywords;

import domain.keyword.SynonymAlreadyExistsException;

import java.util.HashSet;
import java.util.Set;

public class Keyword {
    public String word;
    public String parent;
    public Set<String> synonyms;

    public Keyword(String word, String parent) {
        this.word = word;
        this.parent = parent;
        this.synonyms = new HashSet<>();
    }

    public Keyword(String word) {
        this(word, word);
    }

    public void setSynonyms(Set<String> synonyms){
        this.synonyms = synonyms;
    }

    public void addSynonym(String synonym) throws SynonymAlreadyExistsException {
        if (!this.synonyms.contains(synonym)){
            this.synonyms.add(synonym);
        }else{
            throw new SynonymAlreadyExistsException();
        }

    }

}
