package domain.keyword;

import java.util.Set;

public interface Keyword {

    String getKeyword();

    Set<String> getSynonyms();

    void setSynonyms(Set<String> synonyms);

    void setKeyword(String keyword);

}
