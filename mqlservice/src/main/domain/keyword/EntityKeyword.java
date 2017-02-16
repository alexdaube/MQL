package domain.keyword;

import java.util.List;

public class EntityKeyword implements Keyword {

    private String keyword;
    private List<String> synonyms;
    private List<Keyword> attributes;

    public EntityKeyword() {

    }

    public void setAttributes(List<Keyword> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public List<String> getSynonyms() {
        return this.synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
