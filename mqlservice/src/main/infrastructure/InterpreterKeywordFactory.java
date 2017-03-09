package infrastructure;


import configuration.keywords.EntityKeyword;
import configuration.keywords.EntityMap;
import configuration.keywords.KeywordConfig;
import domain.keywords.Keyword;
import domain.keywords.Keywords;
import domain.keywords.KeywordsSet;

import java.util.HashSet;
import java.util.Set;

public class InterpreterKeywordFactory {


    public InterpreterKeywordFactory() {

    }

    public KeywordsSet createKeywordsFromEntityMap(EntityMap entityMap) {
        Set<EntityKeyword> entities = entityMap.getEntityKeywords();
        Set<Keyword> allKeywords = new HashSet<>();
        entities.stream().forEach(entityKeyword -> allKeywords.addAll(createAttributesKeywords(entityKeyword)));

        return new KeywordsSet(allKeywords);
    }

    private Set<Keyword> createAttributesKeywords(EntityKeyword entity) {
        Set<Keyword> keywords = new HashSet<>();
        String parentName = entity.getKeyword();
        Keyword mainKeyword = new Keyword(parentName);
        mainKeyword.setSynonyms(entity.getSynonyms());
        keywords.add(mainKeyword);

        for (KeywordConfig keyword : entity.getAttributes()) {
            Keyword newKeyword = new Keyword(keyword.getKeyword(), parentName, Keywords.Type.ATTRIBUTE);
            newKeyword.setSynonyms(keyword.getSynonyms());
            keywords.add(newKeyword);
        }

        return keywords;
    }
}
