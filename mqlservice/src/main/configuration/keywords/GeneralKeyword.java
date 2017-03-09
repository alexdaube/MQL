package configuration.keywords;

import java.util.Set;

public class GeneralKeyword implements KeywordConfig {

    private String keyword;
    private Set<String> synonyms;

    public GeneralKeyword(String keyword, Set<String> synonyms) {
        this.keyword = keyword;
        this.synonyms = synonyms;
        this.synonyms.add(this.keyword);
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
