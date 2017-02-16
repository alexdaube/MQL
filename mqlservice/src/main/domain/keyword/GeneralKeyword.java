package domain.keyword;

import java.util.List;

public class GeneralKeyword implements Keyword {

    private String keyword;
    private List<String> synonyms;

    public GeneralKeyword() {
    }

    @Override
    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public List<String> getSynonyms() {
        return this.synonyms;
    }

    @Override
    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }


    @Override
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
