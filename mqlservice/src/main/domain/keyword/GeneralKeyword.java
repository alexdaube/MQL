package domain.keyword;

import java.util.Set;

public class GeneralKeyword implements KeywordConfig {

    private String keyword;
    private Set<String> synonyms;

    public GeneralKeyword() {
    }

    @Override
    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public Set<String> getSynonyms() {
        return this.synonyms;
    }

    @Override
    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
