package configuration.keywords;

import java.util.List;
import java.util.Set;

public class EntityKeyword implements KeywordConfig {

    private String keyword;
    private Set<String> synonyms;
    private List<ForeignKey> foreignKeys;
    private Set<GeneralKeyword> attributes;

    public EntityKeyword(String keyword, List<ForeignKey> foreignKeys, Set<String> synonyms) {
        this.keyword = keyword;
        this.foreignKeys = foreignKeys;
        this.synonyms = synonyms;
        this.synonyms.add(this.keyword);
    }

    public void setAttributes(Set<GeneralKeyword> attributes) {
        this.attributes = attributes;
    }

    public List<ForeignKey> getForeignKeys() {
        return this.foreignKeys;
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
