package infrastructure.repositories;

import domain.keywords.Keyword;
import domain.keywords.KeywordRepository;
import domain.keywords.Keywords;
import domain.keywords.KeywordsSet;
import infrastructure.clients.KeywordClient;

public class CmsKeywordRepository implements KeywordRepository {

    private final KeywordClient keywordClient;

    public CmsKeywordRepository(KeywordClient keywordClient) {
        this.keywordClient = keywordClient;
    }

    @Override
    public Keywords findAllKeywords() {
        KeywordsSet keywords = new KeywordsSet();
        keywordClient.fetchEntities().forEach(e -> {
            KeywordsSet attributes = new KeywordsSet();
            e.attributes.forEach(a -> attributes.add(new Keyword(a.keyword, Keyword.Type.ATTRIBUTE,
                    a.synonyms, new KeywordsSet())));
            keywords.add(new Keyword(e.keyword, Keyword.Type.ENTITY, e.synonyms, attributes));
            attributes.forEach(keywords::add);
        });
        keywordClient.fetchJunctions().forEach(j -> keywords.add(new Keyword(j.type,
                Keyword.Type.valueOf(j.type), j.keywords, new KeywordsSet())));
        keywordClient.fetchOperators().forEach(o -> keywords.add(new Keyword(o.type,
                Keyword.Type.valueOf(o.type), o.keywords, new KeywordsSet())));
        return keywords;
    }
}
