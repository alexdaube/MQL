package contexts;

import domain.keywords.Keyword;
import domain.keywords.KeywordRepository;
import domain.keywords.KeywordsSet;
import infrastructure.clients.ConfigClient;
import infrastructure.repositories.InMemoryKeywordRepository;

public class KeywordRepositoryCreator {
    public static KeywordRepository create() {
        KeywordsSet keywords = new KeywordsSet();
        ConfigClient configClient = new ConfigClient();
        configClient.findAllEntityKeyword().forEach(e -> {
            KeywordsSet attributes = new KeywordsSet();
            e.attributes.forEach(a -> attributes.add(new Keyword(a.keyword, Keyword.Type.ATTRIBUTE,
                    a.synonyms, new KeywordsSet())));
            keywords.add(new Keyword(e.keyword, Keyword.Type.ENTITY, e.synonyms, attributes));
            attributes.forEach(keywords::add);
        });
        configClient.findAllJunctionKeyword().forEach(j -> keywords.add(new Keyword(j.type,
                Keyword.Type.valueOf(j.type), j.keywords, new KeywordsSet())));
        configClient.findAllOperatorKeyword().forEach(o -> keywords.add(new Keyword(o.type,
                Keyword.Type.valueOf(o.type), o.keywords, new KeywordsSet())));
        return new InMemoryKeywordRepository(keywords);
    }
}
