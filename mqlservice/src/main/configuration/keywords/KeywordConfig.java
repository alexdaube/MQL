package configuration.keywords;

import java.util.Set;

public interface KeywordConfig {

    String getKeyword();

    Set<String> getSynonyms();

    void setSynonyms(Set<String> synonyms);

    void setKeyword(String keyword);

}
