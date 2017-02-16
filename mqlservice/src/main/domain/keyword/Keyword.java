package domain.keyword;

import java.util.List;

public interface Keyword {

    String getKeyword();

    List<String> getSynonyms();

    void setSynonyms(List<String> synonyms);

    void setKeyword(String keyword);

}
