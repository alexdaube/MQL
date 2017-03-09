package configuration.keywords;

import java.util.Set;

public class EntityKeyword implements KeywordConfig {

    private String keyword;
    private Set<String> synonyms;
    private Set<String> foreign_keys;
    private Set<GeneralKeyword> attributes;

    public EntityKeyword(String keyword, Set<String> foreign_keys, Set<String> synonyms) {
        this.keyword = keyword;
        this.foreign_keys = foreign_keys;
        this.synonyms = synonyms;
        this.synonyms.add(this.keyword);
    }

    public void setAttributes(Set<GeneralKeyword> attributes) {
        this.attributes = attributes;
    }

    public Set<String> getForeignKeys(){
        return this.foreign_keys;
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
