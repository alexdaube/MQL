package contexts;

import domain.keywords.Keyword;
import domain.keywords.KeywordRepository;
import domain.keywords.KeywordsSet;
import infrastructure.clients.ConfigClient;
import infrastructure.clients.HTTPClient;
import infrastructure.repositories.InMemoryKeywordRepository;

import java.io.IOException;

public class KeywordRepositoryCreator {
    public static KeywordRepository createFromConfig() {
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

    public static KeywordRepository create() throws IOException {
        KeywordsSet keywords = new KeywordsSet();

        HTTPClient httpClient = new HTTPClient();

        httpClient.fetchEntities().forEach(e -> {
            KeywordsSet attributes = new KeywordsSet();
            e.attributes.forEach(a -> attributes.add(new Keyword(a.keyword, Keyword.Type.ATTRIBUTE,
                    a.synonyms, new KeywordsSet())));
            keywords.add(new Keyword(e.keyword, Keyword.Type.ENTITY, e.synonyms, attributes));
            attributes.forEach(keywords::add);
        });
        httpClient.fetchJunctions().forEach(j -> keywords.add(new Keyword(j.type,
                Keyword.Type.valueOf(j.type), j.keywords, new KeywordsSet())));
        httpClient.fetchOperators().forEach(o -> keywords.add(new Keyword(o.type,
                Keyword.Type.valueOf(o.type), o.keywords, new KeywordsSet())));

        return new InMemoryKeywordRepository(keywords);
    }
}
