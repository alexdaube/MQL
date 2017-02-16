package domain.keyword;

import java.util.Set;

public class EntityKeyword implements Keyword {

    private String keyword;
    private Set<String> synonyms;

    private Set<Keyword> attributes;

    public EntityKeyword() {

    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setAttributes(Set<Keyword> attributes) {
        this.attributes = attributes;
    }

    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }

    public Set<Keyword> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public Set<String> getSynonyms() {
        return this.synonyms;
    }
}
