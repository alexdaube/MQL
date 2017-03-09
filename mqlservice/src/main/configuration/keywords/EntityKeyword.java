package configuration.keywords;

import java.util.Set;

public class EntityKeyword implements KeywordConfig {

    private String keyword;
    private Set<String> synonyms;

    private Set<GeneralKeyword> attributes;

    public EntityKeyword() {

    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setAttributes(Set<GeneralKeyword> attributes) {
        this.attributes = attributes;
    }

    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
        this.synonyms.add(this.keyword);
    }

    public Set<GeneralKeyword> getAttributes() {
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
